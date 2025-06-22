package com.nicoarbio.cardealership.vehicleunit.service;

import com.nicoarbio.cardealership.vehicleunit.dto.VehicleUnitRequest;
import com.nicoarbio.cardealership.vehicleunit.dto.VehicleUnitFullResponse;
import com.nicoarbio.cardealership.vehicleunit.dto.VehicleUnitResponse;
import com.nicoarbio.cardealership.vehicleunit.dto.VehicleUnitSoldRequest;

import java.util.List;
import java.util.UUID;

public interface VehicleUnitService {

    List<VehicleUnitResponse> getAll();

    VehicleUnitFullResponse getById(UUID id);

    VehicleUnitFullResponse create(VehicleUnitRequest request);

    VehicleUnitFullResponse update(UUID id, VehicleUnitRequest request);

    void delete(UUID id);

    VehicleUnitFullResponse updateVehicleUnitSold(UUID id, VehicleUnitSoldRequest vehicleUnitSoldRequest);

}
