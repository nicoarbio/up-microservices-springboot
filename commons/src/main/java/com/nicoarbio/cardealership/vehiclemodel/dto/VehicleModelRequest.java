package com.nicoarbio.cardealership.vehiclemodel.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record VehicleModelRequest(
        @NotBlank @Size(min = 1, max = 30) String brand,
        @NotBlank @Size(min = 1, max = 30) String model,
        @NotNull @Min(1970) Integer manufactureYear,
        @NotBlank @Size(min = 1, max = 20) String type,
        @NotNull @Positive BigDecimal price,
        @NotNull @Positive @Max(10) Integer warrantyYears
) {}
