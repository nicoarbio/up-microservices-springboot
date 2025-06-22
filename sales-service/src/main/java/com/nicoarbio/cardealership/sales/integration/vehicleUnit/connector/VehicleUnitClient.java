package com.nicoarbio.cardealership.sales.integration.vehicleUnit.connector;

import com.nicoarbio.cardealership.vehicleunit.dto.VehicleUnitFullResponse;
import com.nicoarbio.cardealership.vehicleunit.dto.VehicleUnitSoldRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@FeignClient("vehicle-unit-service")
public interface VehicleUnitClient {

    @GetMapping("/api/v1/vehicle-units/{id}")
    VehicleUnitFullResponse getVehicleUnitById(@PathVariable UUID id);

    @PatchMapping("/api/v1/vehicle-units/{id}/sold")
    VehicleUnitFullResponse updateVehicleUnitSold(@PathVariable UUID id, @RequestBody VehicleUnitSoldRequest vehicleUnitSoldBranchRequest);

}
