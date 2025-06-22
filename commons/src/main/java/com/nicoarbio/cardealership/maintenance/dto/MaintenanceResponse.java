package com.nicoarbio.cardealership.maintenance.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record MaintenanceResponse(
        UUID id,
        LocalDate entryDate,
        Integer kilometrage,
        Boolean isUnderWarranty,
        String serviceType,
        UUID vehicleUnitId,
        UUID customerId,
        UUID employeeId,
        UUID branchId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}
