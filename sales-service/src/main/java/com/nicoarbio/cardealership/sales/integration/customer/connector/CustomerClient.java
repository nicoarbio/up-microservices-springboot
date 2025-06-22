package com.nicoarbio.cardealership.sales.integration.customer.connector;

import com.nicoarbio.cardealership.customer.dto.CustomerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient("customer-service")
public interface CustomerClient {

    @GetMapping("/api/v1/customers/{id}")
    CustomerResponse getCustomerById(@PathVariable UUID id);

}
