package com.nicoarbio.cardealership.sales.integration.customer.connector;

import com.nicoarbio.cardealership.sales.integration.customer.dto.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customer-service", fallbackFactory = CustomerClientFallbackFactory.class)
public interface CustomerClient {

    @GetMapping("/api/v1/customers/{id}")
    Customer getCustomerById(@PathVariable String id);

}
