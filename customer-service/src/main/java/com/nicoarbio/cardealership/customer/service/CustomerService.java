package com.nicoarbio.cardealership.customer.service;

import com.nicoarbio.cardealership.customer.dto.CustomerRequest;
import com.nicoarbio.cardealership.customer.dto.CustomerResponse;
import com.nicoarbio.cardealership.customer.dto.mapper.CustomerMapper;
import com.nicoarbio.cardealership.customer.entity.Customer;
import com.nicoarbio.cardealership.customer.repository.CustomerRepository;
import com.nicoarbio.cardealership.exception.types.EntityAlreadyExistsException;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.lang.StringUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class CustomerService {

    private final CustomerRepository repository;
    private final CustomerMapper mapper;

    public CustomerService(CustomerRepository repository, CustomerMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public List<CustomerResponse> getAll() {
        return mapper.toResponseList(repository.findAll());
    }

    @Transactional(readOnly = true)
    public CustomerResponse getById(UUID id) {
        return repository.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new NoSuchElementException("Customer " + id + " not found"));
    }

    @Transactional(readOnly = true)
    public CustomerResponse search(String dni, String email, String phone) {
        if (Stream.of(dni, email, phone).allMatch(StringUtils::isBlank)) {
            throw new NoSuchElementException("No customer found with provided identifiers");
        }
        Customer probe = new Customer();
        probe.setEmail(email);
        probe.setDni(dni);
        probe.setPhone(phone);
        Example<Customer> example = Example.of(probe);
        return repository.findOne(example)
                .map(mapper::toResponse)
                .orElseThrow(() -> new NoSuchElementException("No customer found with provided identifiers"));
    }

    @Transactional
    public CustomerResponse create(CustomerRequest request) {
        Customer entity = mapper.toEntity(request);
        try {
            repository.saveAndFlush(entity);
        } catch (DataIntegrityViolationException e) {
            throw new EntityAlreadyExistsException("Customer `" + entity.getCustomerIdentification() + "` already exists", e);
        }
        return mapper.toResponse(entity);
    }

    @Transactional
    public CustomerResponse update(UUID id, CustomerRequest request) {
        final Customer existing = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Customer " + id + " not found"));
        mapper.toEntity(request, existing);
        try {
            repository.saveAndFlush(existing);
            return mapper.toResponse(existing);
        } catch (DataIntegrityViolationException e) {
            throw new EntityAlreadyExistsException("Customer `" + existing.getCustomerIdentification() + "` has an already used unique attribute", e);
        }
    }

    @Transactional()
    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new NoSuchElementException("Customer " + id + " not found");
        }
        repository.deleteById(id);
    }

}
