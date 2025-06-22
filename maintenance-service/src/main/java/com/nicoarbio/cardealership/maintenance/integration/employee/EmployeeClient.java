package com.nicoarbio.cardealership.maintenance.integration.employee;

import com.nicoarbio.cardealership.employee.dto.EmployeeResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@FeignClient("employee-service")
@RequestMapping("/api/v1/employees")
public interface EmployeeClient {

    @GetMapping("/{id}")
    EmployeeResponse getEmployeeById(@PathVariable UUID id);

}
