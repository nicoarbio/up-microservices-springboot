package com.nicoarbio.cardealership.vehicleunit.integration.connector;

import com.nicoarbio.cardealership.vehicleunit.integration.dto.VehicleModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "vehicle-service", fallbackFactory = VehicleModelClientFallbackFactory.class)
public interface VehicleModelClient {

    @GetMapping("/api/v1/vehicle-models/{id}")
    VehicleModel getVehicleModelById(@PathVariable String id);

}
