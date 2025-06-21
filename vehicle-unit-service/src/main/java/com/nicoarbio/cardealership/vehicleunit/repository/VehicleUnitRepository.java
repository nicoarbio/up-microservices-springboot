package com.nicoarbio.cardealership.vehicleunit.repository;

import com.nicoarbio.cardealership.vehicleunit.entity.VehicleUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VehicleUnitRepository extends JpaRepository<VehicleUnit, UUID> {
}
