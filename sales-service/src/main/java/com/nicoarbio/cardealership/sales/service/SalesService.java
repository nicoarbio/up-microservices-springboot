package com.nicoarbio.cardealership.sales.service;

import com.nicoarbio.cardealership.sales.dto.SaleSpecificVehicleUnitRequest;
import com.nicoarbio.cardealership.sales.dto.SalesResponse;
import com.nicoarbio.cardealership.sales.dto.mapper.SalesMapper;
import com.nicoarbio.cardealership.sales.entity.Sale;
import com.nicoarbio.cardealership.sales.exception.types.VehicleUnitNotAvailableException;
import com.nicoarbio.cardealership.sales.integration.branch.connector.BranchClient;
import com.nicoarbio.cardealership.sales.integration.branch.dto.Branch;
import com.nicoarbio.cardealership.sales.integration.customer.connector.CustomerClient;
import com.nicoarbio.cardealership.sales.integration.customer.dto.Customer;
import com.nicoarbio.cardealership.sales.integration.vehicleUnit.connector.VehicleUnitClient;
import com.nicoarbio.cardealership.sales.integration.vehicleUnit.dto.LocationType;
import com.nicoarbio.cardealership.sales.integration.vehicleUnit.dto.VehicleUnitFull;
import com.nicoarbio.cardealership.sales.integration.vehicleUnit.dto.VehicleUnitSoldBranchRequest;
import com.nicoarbio.cardealership.sales.integration.vehicleUnit.dto.VehicleUnitStatus;
import com.nicoarbio.cardealership.sales.repository.SalesRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class SalesService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SalesService.class);

    private final SalesRepository repository;
    private final SalesMapper mapper;
    private final VehicleUnitClient vehicleUnitClient;
    private final CustomerClient customerClient;
    private final BranchClient branchClient;

    public SalesService(SalesRepository repository, VehicleUnitClient vehicleUnitClient,
                        CustomerClient customerClient, BranchClient branchClient,
                        SalesMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
        this.vehicleUnitClient = vehicleUnitClient;
        this.customerClient = customerClient;
        this.branchClient = branchClient;

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
        VehicleUnitFull vehicleUnit = vehicleUnitClient.getVehicleUnitById(request.vehicleUnitId().toString());
        Branch branch = branchClient.getBranchById(request.branchId().toString());
        if (vehicleUnit.branchId() != null && !vehicleUnit.branchId().equals(branch.id())) {
            throw new VehicleUnitNotAvailableException("Vehicle Unit " + vehicleUnit.id() + " must be in CENTRAL or specified BRANCH. Cannot be sold from branch ID " + branch.id());
        }
        if (vehicleUnit.status().equals(VehicleUnitStatus.SOLD)) {
            throw new VehicleUnitNotAvailableException("Vehicle Unit " + vehicleUnit.id() + " is already SOLD. Cannot be sold again.");
        }
        Customer customer = customerClient.getCustomerById(request.customerId().toString());

        Sale sale = new Sale();
        sale.setAmount(vehicleUnit.price());
        sale.setSaleDate(LocalDate.now());
        sale.setDeliveryDate(LocalDate.now().plusDays(resolveDeliveryDays(vehicleUnit, branch)));
        sale.setCustomerId(customer.id());
        sale.setVehicleUnitId(vehicleUnit.id());
        sale.setBranchId(branch.id());

        vehicleUnitClient.updateVehicleUnitSold(vehicleUnit.id().toString(), new VehicleUnitSoldBranchRequest(branch.id().toString()));
        LOGGER.info("VehicleUnit (" + sale.getVehicleUnitId() + ") requested to be marked as SOLD in Branch ID: : " + branch.id());

        try {
            repository.saveAndFlush(sale);
            LOGGER.info("Sale registered: " + sale.toString());
        } catch (Exception e) {
            throw new RuntimeException("Error saving sale: " + e.getMessage(), e);
        }

        return mapper.toResponse(sale);
    }

    public int resolveDeliveryDays(VehicleUnitFull vehicleUnit, Branch branch) {
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
