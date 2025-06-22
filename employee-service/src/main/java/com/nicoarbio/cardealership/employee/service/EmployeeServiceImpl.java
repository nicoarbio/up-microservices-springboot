package com.nicoarbio.cardealership.employee.service;

import com.nicoarbio.cardealership.employee.dto.EmployeeMapper;
import com.nicoarbio.cardealership.employee.dto.EmployeeRequest;
import com.nicoarbio.cardealership.employee.dto.EmployeeResponse;
import com.nicoarbio.cardealership.employee.entity.Employee;
import com.nicoarbio.cardealership.employee.repository.EmployeeRepository;
import com.nicoarbio.cardealership.exception.types.EntityAlreadyExistsException;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;
    private final EmployeeMapper mapper;

    public EmployeeServiceImpl(EmployeeRepository repository, EmployeeMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<EmployeeResponse> getAll() {
        return mapper.toResponseList(repository.findAll());
    }

    public EmployeeResponse getById(UUID id) {
        return repository.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new NoSuchElementException("Employee " + id + " not found"));
    }

    @Transactional
    public EmployeeResponse create(EmployeeRequest request) {
        Employee entity = mapper.toEntity(request);
        try {
            repository.saveAndFlush(entity);
        } catch (DataIntegrityViolationException e) {
            throw new EntityAlreadyExistsException("Employee `" + entity.getEmployeeIdentification() + "` already exists", e);
        }
        return mapper.toResponse(entity);
    }

    @Transactional
    public EmployeeResponse update(UUID id, EmployeeRequest request) {
        final Employee existing = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Employee " + id + " not found"));
        mapper.toEntity(request, existing);
        try {
            repository.saveAndFlush(existing);
            return mapper.toResponse(existing);
        } catch (DataIntegrityViolationException e) {
            throw new EntityAlreadyExistsException("Employee `" + existing.getEmployeeIdentification() + "` has an already used unique attribute", e);
        }
    }

    @Transactional
    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new NoSuchElementException("Employee " + id + " not found");
        }
        repository.deleteById(id);
    }

}
