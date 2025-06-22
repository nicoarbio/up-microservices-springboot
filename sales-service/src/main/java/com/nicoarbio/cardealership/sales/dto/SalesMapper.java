package com.nicoarbio.cardealership.sales.dto;

import com.nicoarbio.cardealership.sales.entity.Sale;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SalesMapper {

    public SalesResponse toResponse(Sale sale) {
        return new SalesResponse(
                sale.getId(),
                sale.getAmount(),
                sale.getSaleDate(),
                sale.getDeliveryDate(),
                sale.getEmployeeId(),
                sale.getCustomerId(),
                sale.getVehicleUnitId(),
                sale.getBranchId(),
                sale.getCreatedAt(),
                sale.getUpdatedAt()
        );
    }

    public List<SalesResponse> toResponseList(List<Sale> sales) {
        return sales.stream().map(this::toResponse).collect(Collectors.toList());
    }

}
