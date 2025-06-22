package com.nicoarbio.cardealership.sales.controller;

import com.nicoarbio.cardealership.sales.dto.SaleSpecificVehicleUnitRequest;
import com.nicoarbio.cardealership.sales.dto.SalesResponse;
import com.nicoarbio.cardealership.sales.service.SalesService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/sales")
public class SalesController {

    private final SalesService service;

    public SalesController(SalesService service) {
        this.service = service;
    }

    @GetMapping
    public List<SalesResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SalesResponse> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("/vehicle-unit/{vehicleUnitId}")
    public ResponseEntity<SalesResponse> getByVehicleUnitId(@PathVariable UUID vehicleUnitId) {
        return ResponseEntity.ok(service.getByVehicleUnitId(vehicleUnitId));
    }

    @PostMapping
    public ResponseEntity<SalesResponse> registerSellOfSpecificVehicleUnit(@Valid @RequestBody SaleSpecificVehicleUnitRequest specificVehicleUnitRequest) {
        return ResponseEntity.ok(service.registerSellOfSpecificVehicleUnit(specificVehicleUnitRequest));
    }

}
