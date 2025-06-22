package com.nicoarbio.cardealership.branch.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record BranchRequest(
        @NotBlank @Size(min = 1, max = 100) String name,
        @NotBlank @Size(min = 1, max = 50) String country,
        @NotBlank @Size(min = 1, max = 50) String province,
        @NotBlank @Size(min = 1, max = 50) String city,
        @NotBlank @Size(min = 1, max = 255) String address,
        @NotNull @PastOrPresent LocalDate openingDate,
        @NotNull @Min(1) @Max(14) Integer localDeliveryTimeDays,
        @NotNull @Min(1) @Max(30) Integer centralDeliveryTimeDays
) {}
