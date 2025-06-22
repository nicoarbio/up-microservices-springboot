package com.nicoarbio.cardealership.sales.integration.vehicleUnit;

import com.nicoarbio.cardealership.vehicleunit.dto.VehicleUnitFullResponse;
import com.nicoarbio.cardealership.vehicleunit.dto.VehicleUnitSoldRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@FeignClient(name = "vehicle-unit-service", path = "/api/v1/vehicle-units")
public interface VehicleUnitClient {

    @GetMapping("/{id}")
    VehicleUnitFullResponse getVehicleUnitById(@PathVariable UUID id);

    @PatchMapping("/{id}/sold")
    VehicleUnitFullResponse updateVehicleUnitSold(@PathVariable UUID id, @RequestBody VehicleUnitSoldRequest vehicleUnitSoldBranchRequest);

}
