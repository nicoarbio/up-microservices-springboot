package com.nicoarbio.cardealership.maintenance.integration.sales;

import com.nicoarbio.cardealership.sales.dto.SalesResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@FeignClient("sales-service")
@RequestMapping("/api/v1/sales")
public interface SalesClient {

    @GetMapping("/vehicle-unit/{id}")
    SalesResponse getSaleByVehicleUnitId(@PathVariable UUID id);

}
