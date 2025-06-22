package com.nicoarbio.cardealership.sales.service;

import com.nicoarbio.cardealership.branch.dto.BranchResponse;
import com.nicoarbio.cardealership.sales.dto.SaleSpecificVehicleUnitRequest;
import com.nicoarbio.cardealership.sales.dto.SalesResponse;
import com.nicoarbio.cardealership.vehicleunit.dto.VehicleUnitFullResponse;

import java.util.List;
import java.util.UUID;

public interface SalesService {

    List<SalesResponse> getAll();

    SalesResponse getById(UUID id);

    SalesResponse registerSellOfSpecificVehicleUnit(SaleSpecificVehicleUnitRequest request);

    int resolveDeliveryDays(VehicleUnitFullResponse vehicleUnit, BranchResponse branch);

}
