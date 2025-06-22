package com.nicoarbio.cardealership.maintenance.service;

import com.nicoarbio.cardealership.branch.dto.BranchResponse;
import com.nicoarbio.cardealership.employee.dto.EmployeeResponse;
import com.nicoarbio.cardealership.maintenance.dto.MaintenanceMapper;
import com.nicoarbio.cardealership.maintenance.dto.MaintenanceRequest;
import com.nicoarbio.cardealership.maintenance.dto.MaintenanceResponse;
import com.nicoarbio.cardealership.maintenance.entity.Maintenance;
import com.nicoarbio.cardealership.maintenance.integration.branch.BranchClient;
import com.nicoarbio.cardealership.maintenance.integration.customer.CustomerClient;
import com.nicoarbio.cardealership.maintenance.integration.employee.EmployeeClient;
import com.nicoarbio.cardealership.maintenance.integration.sales.SalesClient;
import com.nicoarbio.cardealership.maintenance.integration.vehicleUnit.VehicleUnitClient;
import com.nicoarbio.cardealership.maintenance.repository.MaintenanceRepository;
import com.nicoarbio.cardealership.sales.dto.SalesResponse;
import com.nicoarbio.cardealership.vehicleunit.dto.LocationType;
import com.nicoarbio.cardealership.vehicleunit.dto.VehicleUnitFullResponse;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class MaintenanceServiceImpl implements MaintenanceService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MaintenanceServiceImpl.class);

    private final MaintenanceRepository repository;
    private final MaintenanceMapper mapper;
    private final EmployeeClient employeeClient;
    private final VehicleUnitClient vehicleUnitClient;
    private final CustomerClient customerClient;
    private final SalesClient salesClient;
    private final BranchClient branchClient;

    public MaintenanceServiceImpl(MaintenanceRepository repository, VehicleUnitClient vehicleUnitClient,
                                  CustomerClient customerClient, EmployeeClient employeeClient,
                                  SalesClient salesClient, BranchClient branchClient,
                                  MaintenanceMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
        this.vehicleUnitClient = vehicleUnitClient;
        this.customerClient = customerClient;
        this.employeeClient = employeeClient;
        this.salesClient = salesClient;
        this.branchClient = branchClient;
    }

    public List<MaintenanceResponse> getAll() {
        return mapper.toResponseList(repository.findAll());
    }

    public MaintenanceResponse getById(UUID id) {
        return repository.findById(id)
            .map(mapper::toResponse)
            .orElseThrow(() -> new NoSuchElementException("Maintenance " + id + " not found"));

    }

    @Transactional
    public MaintenanceResponse registerMaintenanceService(MaintenanceRequest request) {
        final VehicleUnitFullResponse vehicleUnit = vehicleUnitClient.getVehicleUnitById(request.vehicleUnitId());

        customerClient.getCustomerById(request.customerId());

        final EmployeeResponse employee = employeeClient.getEmployeeById(request.employeeId());
        if (!employee.isActive()) {
            throw new IllegalStateException("Employee " + employee.id() + " is not active. Cannot register a maintenance.");
        }

        final BranchResponse branch = branchClient.getBranchById(request.branchId());
        if (!employee.branchId().equals(branch.id())) {
            throw new IllegalStateException("Employee " + employee.id() + " must be in the same branch as the maintenance. Cannot register maintenance from branch ID " + branch.id());
        }

        Maintenance maintenance = mapper.toEntity(request);
        maintenance.setUnderWarranty(this.isVehicleUnitUnderWarranty(vehicleUnit));
        maintenance.setEntryDate(LocalDate.now());

        try {
            repository.saveAndFlush(maintenance);
        } catch (Exception e) {
            LOGGER.info("Error saving new Maintenance " + maintenance, e);
            throw new RuntimeException("Error saving maintenance: " + e.getMessage(), e); // Should not happen, but just in case
        }
        LOGGER.info("Maintenance registered: " + maintenance);

        return mapper.toResponse(maintenance);
    }

    public boolean isVehicleUnitUnderWarranty(VehicleUnitFullResponse vehicleUnit) {
        if (!LocationType.EXTERNAL.equals(vehicleUnit.locationType())) {
            SalesResponse sale = salesClient.getSaleByVehicleUnitId(vehicleUnit.id());
            LocalDate warrantyDateFinish = sale.deliveryDate().plusYears(vehicleUnit.warrantyYears());
            return warrantyDateFinish.isAfter(LocalDate.now());
        }
        return false;
    }

}
