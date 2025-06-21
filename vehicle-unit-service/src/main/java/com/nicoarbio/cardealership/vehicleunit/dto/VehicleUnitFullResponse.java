package com.nicoarbio.cardealership.vehicleunit.dto;

import com.nicoarbio.cardealership.vehicleunit.entity.LocationType;
import com.nicoarbio.cardealership.vehicleunit.entity.VehicleUnitStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Includes information from {@link com.nicoarbio.cardealership.vehicleunit.integration.dto.VehicleModel}
 */
public record VehicleUnitFullResponse(
        UUID id,
        String licencePlate,
        String color,
        VehicleUnitStatus status,
        LocationType locationType,
        UUID branchId,
        String brand,
        String model,
        Integer manufactureYear,
        String type,
        BigDecimal price,
        Integer warrantyYears,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}
