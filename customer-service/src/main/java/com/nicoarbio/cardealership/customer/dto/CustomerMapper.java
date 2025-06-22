package com.nicoarbio.cardealership.customer.dto;

import com.nicoarbio.cardealership.customer.entity.Customer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerMapper {

    public Customer toEntity(CustomerRequest req) {
        return toEntity(req, null);
    }

    public Customer toEntity(CustomerRequest req, Customer existing) {
        if (existing == null) {
            existing = new Customer();
        }
        existing.setFirstName(req.firstName());
        existing.setLastName(req.lastName());
        existing.setDni(req.dni());
        existing.setEmail(req.email());
        existing.setPhone(req.phone());
        existing.setAddress(req.address());
        return existing;
    }

    public CustomerResponse toResponse(Customer customer) {
        return new CustomerResponse(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getDni(),
                customer.getEmail(),
                customer.getPhone(),
                customer.getAddress(),
                customer.getCreatedAt(),
                customer.getUpdatedAt()
        );
    }

    public List<CustomerResponse> toResponseList(List<Customer> customers) {
        return customers.stream().map(this::toResponse).collect(Collectors.toList());
    }

}
