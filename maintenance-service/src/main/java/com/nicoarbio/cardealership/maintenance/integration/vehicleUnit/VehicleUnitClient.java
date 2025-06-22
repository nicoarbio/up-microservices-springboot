package com.nicoarbio.cardealership.maintenance.integration.vehicleUnit;

import com.nicoarbio.cardealership.vehicleunit.dto.VehicleUnitFullResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@FeignClient(name = "vehicle-unit-service", path = "/api/v1/vehicle-units")
public interface VehicleUnitClient {

    @GetMapping("/{id}")
    VehicleUnitFullResponse getVehicleUnitById(@PathVariable UUID id);


}
