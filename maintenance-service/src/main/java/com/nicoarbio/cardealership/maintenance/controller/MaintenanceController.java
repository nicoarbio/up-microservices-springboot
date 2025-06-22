package com.nicoarbio.cardealership.maintenance.controller;

import com.nicoarbio.cardealership.maintenance.dto.MaintenanceRequest;
import com.nicoarbio.cardealership.maintenance.dto.MaintenanceResponse;
import com.nicoarbio.cardealership.maintenance.service.MaintenanceService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/maintenances")
public class MaintenanceController {

    private final MaintenanceService service;

    public MaintenanceController(MaintenanceService service) {
        this.service = service;
    }

    @GetMapping
    public List<MaintenanceResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MaintenanceResponse> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<MaintenanceResponse> registerMaintenanceService(@Valid @RequestBody MaintenanceRequest maintenanceRequest) {
        return ResponseEntity.ok(service.registerMaintenanceService(maintenanceRequest));
    }

}
