package com.nicoarbio.cardealership.maintenance.repository;

import com.nicoarbio.cardealership.maintenance.entity.Maintenance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MaintenanceRepository extends JpaRepository<Maintenance, UUID> {
}
