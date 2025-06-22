package com.nicoarbio.cardealership.maintenance.service;

import com.nicoarbio.cardealership.maintenance.dto.MaintenanceRequest;
import com.nicoarbio.cardealership.maintenance.dto.MaintenanceResponse;

import java.util.List;
import java.util.UUID;

public interface MaintenanceService {

    List<MaintenanceResponse> getAll();

    MaintenanceResponse getById(UUID id);

    MaintenanceResponse registerMaintenanceService(MaintenanceRequest request);

}
