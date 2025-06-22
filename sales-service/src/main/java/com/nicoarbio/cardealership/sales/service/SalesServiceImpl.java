package com.nicoarbio.cardealership.sales.service;

import com.nicoarbio.cardealership.branch.dto.BranchResponse;
import com.nicoarbio.cardealership.customer.dto.CustomerResponse;
import com.nicoarbio.cardealership.employee.dto.EmployeeResponse;
import com.nicoarbio.cardealership.sales.dto.SaleSpecificVehicleUnitRequest;
import com.nicoarbio.cardealership.sales.dto.SalesMapper;
import com.nicoarbio.cardealership.sales.dto.SalesResponse;
import com.nicoarbio.cardealership.sales.entity.Sale;
import com.nicoarbio.cardealership.sales.exception.types.VehicleUnitNotAvailableException;
import com.nicoarbio.cardealership.sales.integration.branch.BranchClient;
import com.nicoarbio.cardealership.sales.integration.customer.CustomerClient;
import com.nicoarbio.cardealership.sales.integration.employee.EmployeeClient;
import com.nicoarbio.cardealership.sales.integration.vehicleUnit.VehicleUnitClient;
import com.nicoarbio.cardealership.sales.repository.SalesRepository;
import com.nicoarbio.cardealership.vehicleunit.dto.LocationType;
import com.nicoarbio.cardealership.vehicleunit.dto.VehicleUnitFullResponse;
import com.nicoarbio.cardealership.vehicleunit.dto.VehicleUnitSoldRequest;
import com.nicoarbio.cardealership.vehicleunit.dto.VehicleUnitStatus;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class SalesServiceImpl implements SalesService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SalesServiceImpl.class);

    private final SalesRepository repository;
    private final SalesMapper mapper;
    private final EmployeeClient employeeClient;
    private final VehicleUnitClient vehicleUnitClient;
    private final CustomerClient customerClient;
    private final BranchClient branchClient;

    public SalesServiceImpl(SalesRepository repository, VehicleUnitClient vehicleUnitClient,
                            CustomerClient customerClient, BranchClient branchClient,
                            EmployeeClient employeeClient, SalesMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
        this.vehicleUnitClient = vehicleUnitClient;
        this.customerClient = customerClient;
        this.branchClient = branchClient;
        this.employeeClient = employeeClient;
    }

    public List<SalesResponse> getAll() {
        return mapper.toResponseList(repository.findAll());
    }

    public SalesResponse getById(UUID id) {
        return repository.findById(id)
            .map(mapper::toResponse)
            .orElseThrow(() -> new NoSuchElementException("Sale " + id + " not found"));

    }

    @Transactional
    public SalesResponse registerSellOfSpecificVehicleUnit(SaleSpecificVehicleUnitRequest request) {
        EmployeeResponse employee = employeeClient.getEmployeeById(request.employeeId());
        if (!employee.isActive()) {
            throw new IllegalStateException("Employee " + employee.id() + " is not active. Cannot register sale.");
        }

        BranchResponse branch = branchClient.getBranchById(request.branchId());
        if (!employee.branchId().equals(branch.id())) {
            throw new IllegalStateException("Employee " + employee.id() + " must be in the same branch as the sale. Cannot register sale from branch ID " + branch.id());
        }

        VehicleUnitFullResponse vehicleUnit = vehicleUnitClient.getVehicleUnitById(request.vehicleUnitId());

        if (vehicleUnit.status().equals(VehicleUnitStatus.SOLD)) {
            throw new VehicleUnitNotAvailableException("Vehicle Unit " + vehicleUnit.id() + " is already SOLD. Cannot be sold again.");
        }
        if (!List.of(LocationType.CENTRAL, LocationType.BRANCH).contains(vehicleUnit.locationType())) {
            throw new VehicleUnitNotAvailableException("Vehicle Unit " + vehicleUnit.id() + " is registered but not available for sale.");
        }
        if (vehicleUnit.branchId() != null && !vehicleUnit.branchId().equals(branch.id())) {
            throw new VehicleUnitNotAvailableException("Vehicle Unit " + vehicleUnit.id() + " must be in CENTRAL or current BRANCH. Cannot be sold from branch ID " + branch.id());
        }

        CustomerResponse customer = customerClient.getCustomerById(request.customerId());

        Sale sale = new Sale();
        sale.setAmount(vehicleUnit.price());
        sale.setSaleDate(LocalDate.now());
        sale.setDeliveryDate(LocalDate.now().plusDays(resolveDeliveryDays(vehicleUnit, branch)));
        sale.setEmployeeId(employee.id());
        sale.setCustomerId(customer.id());
        sale.setVehicleUnitId(vehicleUnit.id());
        sale.setBranchId(branch.id());

        try {
            repository.saveAndFlush(sale);
        } catch (Exception e) {
            LOGGER.info("Error saving new Sale " + sale, e);
            throw new RuntimeException("Error saving sale: " + e.getMessage(), e); // Should not happen, but just in case
        }
        LOGGER.info("Sale registered: " + sale);
        vehicleUnitClient.updateVehicleUnitSold(vehicleUnit.id(), new VehicleUnitSoldRequest(branch.id()));
        LOGGER.info("VehicleUnit (" + sale.getVehicleUnitId() + ") requested to be marked as SOLD in Branch ID: : " + branch.id());

        return mapper.toResponse(sale);
    }

    public int resolveDeliveryDays(VehicleUnitFullResponse vehicleUnit, BranchResponse branch) {
        if (vehicleUnit.locationType().equals(LocationType.BRANCH)) {
            int deliveryDays = branch.localDeliveryTimeDays();
            LOGGER.info("DeliveryDays from BRANCH: " + deliveryDays);
            return deliveryDays;
        } else {
            int deliveryDays = branch.localDeliveryTimeDays() + branch.centralDeliveryTimeDays();
            LOGGER.info("DeliveryDays from CENTRAL then BRANCH: " + deliveryDays);
            return deliveryDays;

        }
    }

}
