package com.nicoarbio.cardealership.employee.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record EmployeeRequest(
        @NotBlank @Size(min = 1, max = 30) String firstName,
        @NotBlank @Size(min = 1, max = 30) String lastName,
        @NotBlank @Size(min = 1, max = 10) String dni,
        @NotBlank @Size(min = 1, max = 35) String email,
        @NotBlank @Size(min = 1, max = 20) String phone,
        @NotNull UUID branchId,
        @NotBlank @Size(min = 1) String role,
        @NotNull @PastOrPresent LocalDate hireDate,
        @NotNull @Positive BigDecimal salary,
        @NotNull Boolean isActive
) {}
