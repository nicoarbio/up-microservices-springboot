package com.nicoarbio.cardealership.vehicleunit.service;

import com.nicoarbio.cardealership.exception.types.EntityAlreadyExistsException;
import com.nicoarbio.cardealership.vehicleunit.dto.VehicleUnitRequest;
import com.nicoarbio.cardealership.vehicleunit.dto.VehicleUnitFullResponse;
import com.nicoarbio.cardealership.vehicleunit.dto.VehicleUnitResponse;
import com.nicoarbio.cardealership.vehicleunit.dto.VehicleUnitSoldRequest;
import com.nicoarbio.cardealership.vehicleunit.dto.mapper.VehicleUnitMapper;
import com.nicoarbio.cardealership.vehicleunit.entity.LocationType;
import com.nicoarbio.cardealership.vehicleunit.entity.VehicleUnit;
import com.nicoarbio.cardealership.vehicleunit.entity.VehicleUnitStatus;
import com.nicoarbio.cardealership.vehicleunit.integration.connector.VehicleModelClient;
import com.nicoarbio.cardealership.vehicleunit.integration.dto.VehicleModel;
import com.nicoarbio.cardealership.vehicleunit.repository.VehicleUnitRepository;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class VehicleUnitService {

    private final VehicleUnitRepository repository;
    private final VehicleModelClient vehicleModelClient;
    private final VehicleUnitMapper mapper;

    public VehicleUnitService(VehicleUnitRepository repository, VehicleModelClient vehicleModelClient,
                              VehicleUnitMapper mapper) {
        this.repository = repository;
        this.vehicleModelClient = vehicleModelClient;
        this.mapper = mapper;
    }

    public List<VehicleUnitResponse> getAll() {
        return mapper.toResponseList(repository.findAll());
    }

    public VehicleUnitFullResponse getById(UUID id) {
        VehicleUnit vehicleUnit = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Vehicle " + id + " not found"));
        VehicleModel vehicleModel = vehicleModelClient.getVehicleModelById(vehicleUnit.getVehicleModelId().toString());

        return this.mapper.toFullResponse(vehicleUnit, vehicleModel);
    }

    @Transactional
    public VehicleUnitFullResponse create(VehicleUnitRequest request) {
        VehicleModel vehicleModel = vehicleModelClient.getVehicleModelById(request.vehicleModelId().toString());
        VehicleUnit entity = mapper.toEntity(request);
        try {
            repository.saveAndFlush(entity);
        } catch (Exception e) {
            throw new EntityAlreadyExistsException("Vehicle `" + entity.getLicencePlate() + "` already exists", e);
        }
        return mapper.toFullResponse(entity, vehicleModel);
    }

    @Transactional
    public VehicleUnitFullResponse update(UUID id, VehicleUnitRequest request) {
        final VehicleUnit existing = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Vehicle " + id + " not found"));
        mapper.toEntity(request, existing);
        VehicleModel vehicleModel = vehicleModelClient.getVehicleModelById(request.vehicleModelId().toString());
        try {
            repository.saveAndFlush(existing);
            return mapper.toFullResponse(existing, vehicleModel);
        } catch (DataIntegrityViolationException e) {
            throw new EntityAlreadyExistsException("Vehicle `" + existing.getLicencePlate() + "` already exists", e);
        }
    }

    @Transactional
    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new NoSuchElementException("Vehicle " + id + " not found");
        }
        repository.deleteById(id);
    }

    @Transactional
    public VehicleUnitFullResponse updateVehicleUnitSold(UUID id, VehicleUnitSoldRequest vehicleUnitSoldRequest) {
        VehicleUnit vehicleUnit = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Vehicle Unit " + id + " not found"));

        if (vehicleUnit.getStatus().equals(VehicleUnitStatus.SOLD)) {
            throw new IllegalStateException("Vehicle Unit " + id + " is already sold");
        }

        vehicleUnit.setStatus(VehicleUnitStatus.SOLD);
        vehicleUnit.setLocation(LocationType.BRANCH);
        vehicleUnit.setBranchId(vehicleUnitSoldRequest.branchId());

        repository.saveAndFlush(vehicleUnit);

        VehicleModel vehicleModel = vehicleModelClient.getVehicleModelById(vehicleUnit.getVehicleModelId().toString());

        return mapper.toFullResponse(vehicleUnit, vehicleModel);
    }
}
