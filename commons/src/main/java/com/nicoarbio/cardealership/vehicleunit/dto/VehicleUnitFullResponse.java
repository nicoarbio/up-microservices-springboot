package com.nicoarbio.cardealership.vehicleunit.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Includes information from {@link com.nicoarbio.cardealership.vehiclemodel.dto.VehicleModelResponse}
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
