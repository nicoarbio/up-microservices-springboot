package com.nicoarbio.cardealership.sales.service;

import com.nicoarbio.cardealership.sales.dto.SaleSpecificVehicleUnitRequest;
import com.nicoarbio.cardealership.sales.dto.SalesResponse;

import java.util.List;
import java.util.UUID;

public interface SalesService {

    List<SalesResponse> getAll();

    SalesResponse getById(UUID id);

    SalesResponse registerSellOfSpecificVehicleUnit(SaleSpecificVehicleUnitRequest request);

    SalesResponse getByVehicleUnitId(UUID id);

}
