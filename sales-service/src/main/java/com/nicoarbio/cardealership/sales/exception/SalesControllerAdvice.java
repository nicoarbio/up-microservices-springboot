package com.nicoarbio.cardealership.sales.exception;

import com.nicoarbio.cardealership.exception.CarDealershipControllerAdvice;
import com.nicoarbio.cardealership.sales.exception.types.VehicleUnitNotAvailableException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class SalesControllerAdvice extends CarDealershipControllerAdvice {

    /**
     * Handles specific already existing Entity exception
     */
    @ExceptionHandler(VehicleUnitNotAvailableException.class)
    public ResponseEntity<Map<String, Object>> handleVehicleUnitNotAvailableException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(createErrorResponse(
                HttpStatus.CONFLICT,
                "Conflict",
                ex.getMessage()
        ));
    }

}

