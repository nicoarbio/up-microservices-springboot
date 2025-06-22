package com.nicoarbio.cardealership.sales.integration.employee;

import com.nicoarbio.cardealership.employee.dto.EmployeeResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient("employee-service")
public interface EmployeeClient {

    @GetMapping("/api/v1/employees/{id}")
    EmployeeResponse getEmployeeById(@PathVariable UUID id);

}
