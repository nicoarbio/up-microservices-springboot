package com.nicoarbio.cardealership.maintenance.dto;

import com.nicoarbio.cardealership.maintenance.entity.Maintenance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MaintenanceMapper {

    public Maintenance toEntity(MaintenanceRequest req) {
        return toEntity(req, null);
    }

    public Maintenance toEntity(MaintenanceRequest req, Maintenance existing) {
        if (existing == null) {
            existing = new Maintenance();
        }
        existing.setKilometrage(req.kilometrage());
        existing.setServiceType(req.serviceType());
        existing.setVehicleUnitId(req.vehicleUnitId());
        existing.setCustomerId(req.customerId());
        existing.setEmployeeId(req.employeeId());
        existing.setBranchId(req.branchId());
        return existing;
    }

    public MaintenanceResponse toResponse(Maintenance maintenance) {
        return new MaintenanceResponse(
                maintenance.getId(),
                maintenance.getEntryDate(),
                maintenance.getKilometrage(),
                maintenance.getUnderWarranty(),
                maintenance.getServiceType(),
                maintenance.getVehicleUnitId(),
                maintenance.getCustomerId(),
                maintenance.getEmployeeId(),
                maintenance.getBranchId(),
                maintenance.getCreatedAt(),
                maintenance.getUpdatedAt()
        );
    }

    public List<MaintenanceResponse> toResponseList(List<Maintenance> maintenances) {
        return maintenances.stream().map(this::toResponse).collect(Collectors.toList());
    }

}
