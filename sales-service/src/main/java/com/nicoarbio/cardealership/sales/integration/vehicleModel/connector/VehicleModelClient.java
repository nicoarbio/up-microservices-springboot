package com.nicoarbio.cardealership.sales.integration.vehicleModel.connector;

import com.nicoarbio.cardealership.vehiclemodel.dto.VehicleModelResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("vehicle-service")
public interface VehicleModelClient {

    @GetMapping("/api/v1/vehicle-models/{id}")
    VehicleModelResponse getVehicleModelById(@PathVariable String id);

}
