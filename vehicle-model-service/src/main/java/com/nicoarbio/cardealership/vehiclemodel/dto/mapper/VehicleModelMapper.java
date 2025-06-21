package com.nicoarbio.cardealership.vehiclemodel.dto.mapper;

import com.nicoarbio.cardealership.vehiclemodel.dto.VehicleModelRequest;
import com.nicoarbio.cardealership.vehiclemodel.dto.VehicleModelResponse;
import com.nicoarbio.cardealership.vehiclemodel.entity.VehicleModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class VehicleModelMapper {

    public VehicleModel toEntity(VehicleModelRequest req) {
        return toEntity(req, null);
    }

    public VehicleModel toEntity(VehicleModelRequest req, VehicleModel existing) {
        if (existing == null) {
            existing = new VehicleModel();
        }
        existing.setBrand(req.brand());
        existing.setModel(req.model());
        existing.setManufactureYear(req.manufactureYear());
        existing.setType(req.type());
        existing.setPrice(req.price());
        existing.setWarrantyYears(req.warrantyYears());
        return existing;
    }

    public VehicleModelResponse toResponse(VehicleModel vehicleModel) {
        return new VehicleModelResponse(
                vehicleModel.getId(),
                vehicleModel.getBrand(),
                vehicleModel.getModel(),
                vehicleModel.getManufactureYear(),
                vehicleModel.getType(),
                vehicleModel.getPrice(),
                vehicleModel.getWarrantyYears(),
                vehicleModel.getCreatedAt(),
                vehicleModel.getUpdatedAt()
        );
    }

    public List<VehicleModelResponse> toResponseList(List<VehicleModel> vehicleModels) {
        return vehicleModels.stream().map(this::toResponse).collect(Collectors.toList());
    }

}
