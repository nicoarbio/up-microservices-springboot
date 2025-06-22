package com.nicoarbio.cardealership.employee.dto;

import com.nicoarbio.cardealership.employee.entity.Employee;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmployeeMapper {

    public Employee toEntity(EmployeeRequest req) {
        return toEntity(req, null);
    }

    public Employee toEntity(EmployeeRequest req, Employee existing) {
        if (existing == null) {
            existing = new Employee();
        }
        existing.setFirstName(req.firstName());
        existing.setLastName(req.lastName());
        existing.setDni(req.dni());
        existing.setEmail(req.email());
        existing.setPhone(req.phone());
        existing.setBranchId(req.branchId());
        existing.setRole(req.role());
        existing.setHireDate(req.hireDate());
        existing.setSalary(req.salary());
        existing.setActive(req.isActive());
        return existing;
    }

    public EmployeeResponse toResponse(Employee employee) {
        return new EmployeeResponse(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getDni(),
                employee.getEmail(),
                employee.getPhone(),
                employee.getBranchId(),
                employee.getRole(),
                employee.getHireDate(),
                employee.getSalary(),
                employee.getActive(),
                employee.getCreatedAt(),
                employee.getUpdatedAt()
        );
    }

    public List<EmployeeResponse> toResponseList(List<Employee> employees) {
        return employees.stream().map(this::toResponse).collect(Collectors.toList());
    }

}
