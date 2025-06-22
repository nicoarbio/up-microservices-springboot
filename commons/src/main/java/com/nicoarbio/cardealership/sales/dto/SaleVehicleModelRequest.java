package com.nicoarbio.cardealership.sales.dto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record SaleVehicleModelRequest(
    @NotNull UUID vehicleModelId,
    @NotNull UUID customerId,
    @NotNull UUID branchId
) {}
