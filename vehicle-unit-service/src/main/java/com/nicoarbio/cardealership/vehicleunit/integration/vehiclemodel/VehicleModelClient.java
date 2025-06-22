package com.nicoarbio.cardealership.vehicleunit.integration.vehiclemodel;

import com.nicoarbio.cardealership.vehiclemodel.dto.VehicleModelResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient("vehicle-model-service")
public interface VehicleModelClient {

    @GetMapping("/api/v1/vehicle-models/{id}")
    VehicleModelResponse getVehicleModelById(@PathVariable UUID id);

}
