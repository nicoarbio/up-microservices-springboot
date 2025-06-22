package com.nicoarbio.cardealership.employee.service;

import com.nicoarbio.cardealership.employee.dto.EmployeeRequest;
import com.nicoarbio.cardealership.employee.dto.EmployeeResponse;

import java.util.List;
import java.util.UUID;

public interface EmployeeService {

    List<EmployeeResponse> getAll();

    EmployeeResponse getById(UUID id);

    EmployeeResponse create(EmployeeRequest request);

    EmployeeResponse update(UUID id, EmployeeRequest request);

    void delete(UUID id);

}
