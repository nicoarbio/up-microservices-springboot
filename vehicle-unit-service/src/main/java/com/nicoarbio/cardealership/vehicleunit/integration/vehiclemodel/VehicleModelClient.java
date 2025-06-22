package com.nicoarbio.cardealership.vehicleunit.integration.vehiclemodel;

import com.nicoarbio.cardealership.vehiclemodel.dto.VehicleModelResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "vehicle-model-service", path = "/api/v1/vehicle-models")
public interface VehicleModelClient {

    @GetMapping("/{id}")
    VehicleModelResponse getVehicleModelById(@PathVariable UUID id);

}
