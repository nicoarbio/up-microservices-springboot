package com.nicoarbio.cardealership.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.nicoarbio.cardealership.exception.types.EntityAlreadyExistsException;
import com.nicoarbio.cardealership.exception.types.VehicleUnitNotAvailableException;
import feign.FeignException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class CarDealershipControllerAdvice {

    public static final String TIMESTAMP = "timestamp";
    public static final String STATUS = "status";
    public static final String ERROR = "error";
    public static final String MESSAGE = "message";
    public static final String MESSAGES = "messages";

    public static Map<String, Object> createErrorResponse(HttpStatus status, String error, String message) {
        return Map.of(
                TIMESTAMP, LocalDateTime.now(),
                STATUS, status.value(),
                ERROR, error,
                MESSAGE, message
        );
    }

    public static Map<String, Object> createErrorResponse(HttpStatus status, String error, List<Map<String, String>> messages) {
        return Map.of(
                TIMESTAMP, LocalDateTime.now(),
                STATUS, status.value(),
                ERROR, error,
                MESSAGES, messages
        );
    }

    /**
     * Handles validation errors for method arguments in controller.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidation(MethodArgumentNotValidException ex) {
        return ResponseEntity.badRequest().body(createErrorResponse(
                HttpStatus.BAD_REQUEST,
                "Validation Failed",
                ex.getBindingResult().getFieldErrors().stream()
                .map(err -> Map.of("field", err.getField(), "message", err.getDefaultMessage()))
                .toList()));
    }

    /**
     * Handles validation errors for method arguments in controller.
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Map<String, Object>> handleDateTypeMismatchException(RuntimeException ex) {
        return ResponseEntity.badRequest().body(createErrorResponse(
                HttpStatus.BAD_REQUEST,
                "Bad Request",
                ex.getMessage()
        ));
    }

    /**
     * Handles validation errors for method arguments in controller.
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, Object>> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        String message = ex.getMessage();
        if (ex.getCause() instanceof InvalidFormatException e) {
            message = e.getPath().stream().findFirst()
                    .map(err -> "Field " + err.getFieldName() + ". Message: " + ex.getMessage())
                    .orElse(message);
        }
        return ResponseEntity.badRequest().body(createErrorResponse(
                HttpStatus.BAD_REQUEST,
                "Bad Request",
                message
        ));
    }

    /**
     * Handles specific already existing Entity exception
     */
    @ExceptionHandler({
            EntityAlreadyExistsException.class,
            IllegalStateException.class,
            VehicleUnitNotAvailableException.class
    })
    public ResponseEntity<Map<String, Object>> handleEntityAlreadyExistsException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(createErrorResponse(
                HttpStatus.CONFLICT,
                "Conflict",
                ex.getMessage()
        ));
    }

    /**
     * Handles generic data integrity violations
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, Object>> handleDataIntegrityViolation(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(createErrorResponse(
                HttpStatus.CONFLICT,
                "Data Integrity Violation",
                ex.getMessage()
        ));
    }

    /**
     * Handles specific exceptions for not found resources.
     */
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Map<String, Object>> handleNotFound(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(createErrorResponse(
                HttpStatus.NOT_FOUND,
                "Not Found",
                ex.getMessage()
        ));
    }

    /**
     * Handles generic exceptions that are not caught by other handlers.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneric(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(createErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Internal Server Error",
                ex.getMessage()
        ));
    }

    /**
     * Handles FeignExceptions
     */
    @ExceptionHandler(FeignException.class)
    public ResponseEntity<Map<String, Object>> handleFeignException(FeignException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(createErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Feign Error",
                ex.getMessage()
        ));
    }

}

