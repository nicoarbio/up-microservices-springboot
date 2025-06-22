package com.nicoarbio.cardealership.vehicleunit.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record VehicleUnitResponse(
        UUID id,
        String licencePlate,
        String color,
        VehicleUnitStatus status,
        LocationType locationType,
        UUID branchId,
        UUID vehicleModelId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}
