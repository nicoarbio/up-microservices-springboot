package com.nicoarbio.cardealership.sales.repository;

import com.nicoarbio.cardealership.sales.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SalesRepository extends JpaRepository<Sale, UUID> {

    Optional<Sale> findByVehicleUnitId(UUID vehicleUnitId);
}
