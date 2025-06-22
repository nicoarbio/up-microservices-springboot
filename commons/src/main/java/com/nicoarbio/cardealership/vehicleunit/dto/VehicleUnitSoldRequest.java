package com.nicoarbio.cardealership.vehicleunit.dto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record VehicleUnitSoldRequest(
        @NotNull UUID branchId
) {}
