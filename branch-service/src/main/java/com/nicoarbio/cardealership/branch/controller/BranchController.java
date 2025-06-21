package com.nicoarbio.cardealership.branch.controller;

import com.nicoarbio.cardealership.branch.dto.BranchRequest;
import com.nicoarbio.cardealership.branch.dto.BranchResponse;
import com.nicoarbio.cardealership.branch.service.BranchService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/branches")
public class BranchController {

    private final BranchService service;

    public BranchController(BranchService service) {
        this.service = service;
    }

    @GetMapping
    public List<BranchResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BranchResponse> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<BranchResponse> create(@Valid @RequestBody BranchRequest newBranch) {
        return ResponseEntity.ok(service.create(newBranch));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BranchResponse> update(@PathVariable UUID id, @Valid @RequestBody BranchRequest updatedBranch) {
        return ResponseEntity.ok(service.update(id, updatedBranch));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
