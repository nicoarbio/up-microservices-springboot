package com.nicoarbio.cardealership.vehicleunit.controller;

import com.nicoarbio.cardealership.vehicleunit.dto.VehicleUnitRequest;
import com.nicoarbio.cardealership.vehicleunit.dto.VehicleUnitFullResponse;
import com.nicoarbio.cardealership.vehicleunit.dto.VehicleUnitResponse;
import com.nicoarbio.cardealership.vehicleunit.dto.VehicleUnitSoldRequest;
import com.nicoarbio.cardealership.vehicleunit.service.VehicleUnitService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/vehicle-units")
public class VehicleUnitController {

    private final VehicleUnitService service;

    public VehicleUnitController(VehicleUnitService service) {
        this.service = service;
    }

    @GetMapping
    public List<VehicleUnitResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleUnitFullResponse> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<VehicleUnitFullResponse> create(@Valid @RequestBody VehicleUnitRequest newStock) {
        return ResponseEntity.ok(service.create(newStock));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VehicleUnitFullResponse> update(@PathVariable UUID id, @Valid @RequestBody VehicleUnitRequest updatedStock) {
        return ResponseEntity.ok(service.update(id, updatedStock));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/sold")
    public ResponseEntity<VehicleUnitFullResponse> updateVehicleUnitSold(@PathVariable UUID id, @Valid @RequestBody VehicleUnitSoldRequest vehicleUnitSoldRequest) {
        return ResponseEntity.ok(service.updateVehicleUnitSold(id, vehicleUnitSoldRequest));
    }

}
