package com.nicoarbio.cardealership.maintenance.integration.customer;

import com.nicoarbio.cardealership.customer.dto.CustomerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@FeignClient("customer-service")
@RequestMapping("/api/v1/customers")
public interface CustomerClient {

    @GetMapping("/{id}")
    CustomerResponse getCustomerById(@PathVariable UUID id);

}
