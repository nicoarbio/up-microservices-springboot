package com.nicoarbio.cardealership.customer.service;

import com.nicoarbio.cardealership.customer.dto.CustomerRequest;
import com.nicoarbio.cardealership.customer.dto.CustomerResponse;

import java.util.List;
import java.util.UUID;

public interface CustomerService {

    List<CustomerResponse> getAll();

    CustomerResponse getById(UUID id);

    CustomerResponse search(String dni, String email, String phone);

    CustomerResponse create(CustomerRequest request);

    CustomerResponse update(UUID id, CustomerRequest request);

    void delete(UUID id);

}
