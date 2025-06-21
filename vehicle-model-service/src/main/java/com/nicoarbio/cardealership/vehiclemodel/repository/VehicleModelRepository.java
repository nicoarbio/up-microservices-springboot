package com.nicoarbio.cardealership.vehiclemodel.repository;

import com.nicoarbio.cardealership.vehiclemodel.entity.VehicleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VehicleModelRepository extends JpaRepository<VehicleModel, UUID> {
}
