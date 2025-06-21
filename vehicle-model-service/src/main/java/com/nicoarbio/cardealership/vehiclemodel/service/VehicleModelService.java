package com.nicoarbio.cardealership.vehiclemodel.service;

import com.nicoarbio.cardealership.exception.types.EntityAlreadyExistsException;
import com.nicoarbio.cardealership.vehiclemodel.dto.VehicleModelRequest;
import com.nicoarbio.cardealership.vehiclemodel.dto.VehicleModelResponse;
import com.nicoarbio.cardealership.vehiclemodel.dto.mapper.VehicleModelMapper;
import com.nicoarbio.cardealership.vehiclemodel.entity.VehicleModel;
import com.nicoarbio.cardealership.vehiclemodel.repository.VehicleModelRepository;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class VehicleModelService {

    private final VehicleModelRepository repository;
    private final VehicleModelMapper mapper;

    public VehicleModelService(VehicleModelRepository repository, VehicleModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<VehicleModelResponse> getAll() {
        return mapper.toResponseList(repository.findAll());
    }

    public VehicleModelResponse getById(UUID id) {
        return repository.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new NoSuchElementException("Vehicle " + id + " not found"));
    }

    @Transactional
    public VehicleModelResponse create(VehicleModelRequest request) {
        VehicleModel entity = mapper.toEntity(request);
        try {
            repository.saveAndFlush(entity);
        } catch (Exception e) {
            throw new EntityAlreadyExistsException("Vehicle `" + entity.getVehicleModel() + "` already exists", e);
        }
        return mapper.toResponse(entity);
    }

    @Transactional
    public VehicleModelResponse update(UUID id, VehicleModelRequest request) {
        final VehicleModel existing = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Vehicle " + id + " not found"));
        mapper.toEntity(request, existing);
        try {
            repository.saveAndFlush(existing);
            return mapper.toResponse(existing);
        } catch (DataIntegrityViolationException e) {
            throw new EntityAlreadyExistsException("Vehicle `" + existing.getVehicleModel() + "` already exists", e);
        }
    }

    @Transactional
    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new NoSuchElementException("Vehicle " + id + " not found");
        }
        repository.deleteById(id);
    }

}
