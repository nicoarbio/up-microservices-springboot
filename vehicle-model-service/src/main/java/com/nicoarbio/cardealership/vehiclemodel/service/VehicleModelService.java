package com.nicoarbio.cardealership.vehiclemodel.service;

import com.nicoarbio.cardealership.vehiclemodel.dto.VehicleModelRequest;
import com.nicoarbio.cardealership.vehiclemodel.dto.VehicleModelResponse;

import java.util.List;
import java.util.UUID;

public interface VehicleModelService {

    List<VehicleModelResponse> getAll();

    VehicleModelResponse getById(UUID id);

    VehicleModelResponse create(VehicleModelRequest request);

    VehicleModelResponse update(UUID id, VehicleModelRequest request);

    void delete(UUID id);

}
