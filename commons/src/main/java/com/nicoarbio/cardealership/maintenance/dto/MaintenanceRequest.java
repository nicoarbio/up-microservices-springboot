package com.nicoarbio.cardealership.maintenance.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record MaintenanceRequest(
        @NotNull @PositiveOrZero Integer kilometrage,
        @NotBlank @Size(min = 1, max = 50) String serviceType,
        @NotNull UUID vehicleUnitId,
        @NotNull UUID customerId,
        @NotNull UUID employeeId,
        @NotNull UUID branchId
) {}
