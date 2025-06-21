package com.nicoarbio.cardealership.sales.integration.customer.connector;

import com.nicoarbio.cardealership.sales.integration.customer.dto.Customer;
import feign.FeignException;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
public class CustomerClientFallbackFactory implements FallbackFactory<CustomerClient> {
    @Override
    public CustomerClient create(Throwable cause) {
        return new CustomerClient() {
            @Override
            public Customer getCustomerById(String id) {
                if (cause instanceof FeignException.NotFound) {
                    throw new NoSuchElementException("Customer not found for ID: " + id);
                }
                throw new RuntimeException("Error connecting to Customer Service", cause);
            }
        };
    }
}
