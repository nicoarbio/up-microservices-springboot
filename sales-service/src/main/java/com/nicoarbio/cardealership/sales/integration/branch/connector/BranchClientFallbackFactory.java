package com.nicoarbio.cardealership.sales.integration.branch.connector;

import com.nicoarbio.cardealership.sales.integration.branch.dto.Branch;
import feign.FeignException;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
public class BranchClientFallbackFactory implements FallbackFactory<BranchClient> {
    @Override
    public BranchClient create(Throwable cause) {
        return new BranchClient() {
            @Override
            public Branch getBranchById(String id) {
                if (cause instanceof FeignException.NotFound) {
                    throw new NoSuchElementException("Vehicle Unit not found for ID: " + id);
                }
                throw new RuntimeException("Error connecting to VehicleUnit Service", cause);
            }
        };
    }
}
