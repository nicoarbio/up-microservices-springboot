package com.nicoarbio.cardealership.vehicleunit.dto;

import com.nicoarbio.cardealership.vehicleunit.entity.LocationType;
import com.nicoarbio.cardealership.vehicleunit.entity.VehicleUnitStatus;

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
