package com.nicoarbio.cardealership.sales.integration.vehicleUnit.connector;

import com.nicoarbio.cardealership.sales.integration.vehicleUnit.dto.VehicleUnitFull;
import com.nicoarbio.cardealership.sales.integration.vehicleUnit.dto.VehicleUnitSoldBranchRequest;
import feign.FeignException;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
public class VehicleUnitClientFallbackFactory implements FallbackFactory<VehicleUnitClient> {
    @Override
    public VehicleUnitClient create(Throwable cause) {
        return new VehicleUnitClient() {
            @Override
            public VehicleUnitFull getVehicleUnitById(String id) {
                if (cause instanceof FeignException.NotFound) {
                    throw new NoSuchElementException("Vehicle Unit not found for ID: " + id);
                }
                throw new RuntimeException("Error connecting to VehicleUnit Service", cause);
            }

            @Override
            public VehicleUnitFull updateVehicleUnitSold(String id, VehicleUnitSoldBranchRequest vehicleUnitSoldBranchRequest) {
                if (cause instanceof FeignException.Conflict) {
                    throw new NoSuchElementException("Vehicle Unit cannot be updated");
                }
                throw new RuntimeException("Error connecting to VehicleUnit Service", cause);
            }
        };
    }
}
