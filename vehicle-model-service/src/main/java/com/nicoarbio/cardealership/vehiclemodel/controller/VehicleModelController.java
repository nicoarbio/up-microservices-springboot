package com.nicoarbio.cardealership.vehiclemodel.controller;

import com.nicoarbio.cardealership.vehiclemodel.dto.VehicleModelRequest;
import com.nicoarbio.cardealership.vehiclemodel.dto.VehicleModelResponse;
import com.nicoarbio.cardealership.vehiclemodel.service.VehicleModelService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/vehicle-models")
public class VehicleModelController {

    private final VehicleModelService service;

    public VehicleModelController(VehicleModelService service) {
        this.service = service;
    }

    @GetMapping
    public List<VehicleModelResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleModelResponse> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<VehicleModelResponse> create(@Valid @RequestBody VehicleModelRequest newVehicle) {
        return ResponseEntity.ok(service.create(newVehicle));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VehicleModelResponse> update(@PathVariable UUID id, @Valid @RequestBody VehicleModelRequest updatedVehicle) {
        return ResponseEntity.ok(service.update(id, updatedVehicle));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
