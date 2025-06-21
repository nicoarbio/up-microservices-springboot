package com.nicoarbio.cardealership.customer.controller;

import com.nicoarbio.cardealership.customer.dto.CustomerRequest;
import com.nicoarbio.cardealership.customer.dto.CustomerResponse;
import com.nicoarbio.cardealership.customer.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @GetMapping
    public List<CustomerResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<CustomerResponse> search(@RequestParam(required = false) String dni,
                                         @RequestParam(required = false) String email,
                                         @RequestParam(required = false) String phone) {
        return ResponseEntity.ok(service.search(dni, email, phone));
    }

    @PostMapping
    public ResponseEntity<CustomerResponse> create(@Valid @RequestBody CustomerRequest newCustomer) {
        return ResponseEntity.ok(service.create(newCustomer));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponse> update(@PathVariable UUID id, @Valid @RequestBody CustomerRequest updatedCustomer) {
        return ResponseEntity.ok(service.update(id, updatedCustomer));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
