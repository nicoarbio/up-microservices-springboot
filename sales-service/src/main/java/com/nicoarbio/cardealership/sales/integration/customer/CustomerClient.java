package com.nicoarbio.cardealership.sales.integration.customer;

import com.nicoarbio.cardealership.customer.dto.CustomerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "customer-service", path = "/api/v1/customers")
public interface CustomerClient {

    @GetMapping("/{id}")
    CustomerResponse getCustomerById(@PathVariable UUID id);

}
