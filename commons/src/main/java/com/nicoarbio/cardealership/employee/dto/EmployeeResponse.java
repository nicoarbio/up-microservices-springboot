package com.nicoarbio.cardealership.employee.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record EmployeeResponse(
        UUID id,
        String firstName,
        String lastName,
        String dni,
        String email,
        String phone,
        UUID branchId,
        String role,
        LocalDate hireDate,
        BigDecimal salary,
        Boolean isActive,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}
