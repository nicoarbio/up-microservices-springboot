package com.nicoarbio.cardealership.sales.integration.vehicleUnit.connector;

import com.nicoarbio.cardealership.sales.integration.vehicleUnit.dto.VehicleUnitFull;
import com.nicoarbio.cardealership.sales.integration.vehicleUnit.dto.VehicleUnitSoldBranchRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "vehicle-unit-service", fallbackFactory = VehicleUnitClientFallbackFactory.class)
public interface VehicleUnitClient {

    @GetMapping("/api/v1/vehicle-units/{id}")
    VehicleUnitFull getVehicleUnitById(@PathVariable String id);

    @PatchMapping("/api/v1/vehicle-units/{id}/sold")
    VehicleUnitFull updateVehicleUnitSold(@PathVariable String id, @RequestBody VehicleUnitSoldBranchRequest vehicleUnitSoldBranchRequest);

}
