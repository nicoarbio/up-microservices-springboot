package com.nicoarbio.cardealership.sales.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record SalesResponse(
        UUID id,
        BigDecimal amount,
        LocalDate saleDate,
        LocalDate deliveryDate,
        UUID employeeId,
        UUID customerId,
        UUID vehicleUnitId,
        UUID branchId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}
