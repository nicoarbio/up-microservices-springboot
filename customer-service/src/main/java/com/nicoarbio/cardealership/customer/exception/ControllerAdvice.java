package com.nicoarbio.cardealership.customer.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.nicoarbio.cardealership.exception.types.EntityAlreadyExistsException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class ControllerAdvice {

    public static final String TIMESTAMP = "timestamp";
    public static final String STATUS = "status";
    public static final String ERROR = "error";
    public static final String MESSAGE = "message";
    public static final String MESSAGES = "messages";

    /**
     * Handles validation errors for method arguments in controller.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, Object> body = Map.of(
                TIMESTAMP, LocalDateTime.now(),
                STATUS, HttpStatus.BAD_REQUEST.value(),
                ERROR, "Validation Failed",
                MESSAGES, ex.getBindingResult().getFieldErrors().stream()
                        .map(err -> Map.of("field", err.getField(), "message", err.getDefaultMessage()))
                        .toList()
        );
        return ResponseEntity.badRequest().body(body);
    }

    /**
     * Handles validation errors for method arguments in controller.
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Map<String, Object>> handleDateTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        Map<String, Object> body = Map.of(
                TIMESTAMP, LocalDateTime.now(),
                STATUS, HttpStatus.BAD_REQUEST.value(),
                ERROR, "Bad Request",
                MESSAGE, ex.getMessage()
        );
        return ResponseEntity.badRequest().body(body);
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

        Map<String, Object> body = Map.of(
                TIMESTAMP, LocalDateTime.now(),
                STATUS, HttpStatus.BAD_REQUEST.value(),
                ERROR, "Bad Request",
                MESSAGE, message
        );
        return ResponseEntity.badRequest().body(body);
    }

    /**
     * Handles specific already existing Entity exception.
     */
    @ExceptionHandler(EntityAlreadyExistsException.class)
    public ResponseEntity<Map<String, Object>> handleEntityAlreadyExistsException(EntityAlreadyExistsException ex) {
        Map<String, Object> body = Map.of(
                TIMESTAMP, LocalDateTime.now(),
                STATUS, HttpStatus.CONFLICT.value(),
                ERROR, "Conflict",
                MESSAGE, ex.getMessage()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
    }

    /**
     * Handles generic data integrity violations
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, Object>> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        Map<String, Object> body = Map.of(
                TIMESTAMP, LocalDateTime.now(),
                STATUS, HttpStatus.CONFLICT.value(),
                ERROR, "Data Integrity Violation",
                MESSAGE, ex.getMessage()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
    }

    /**
     * Handles specific exceptions for not found resources.
     */
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Map<String, Object>> handleNotFound(NoSuchElementException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                TIMESTAMP, LocalDateTime.now(),
                STATUS, HttpStatus.NOT_FOUND.value(),
                ERROR, "Not Found",
                MESSAGE, ex.getMessage()
        ));
    }

    /**
     * Handles generic exceptions that are not caught by other handlers.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneric(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                TIMESTAMP, LocalDateTime.now(),
                STATUS, HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ERROR, "Internal Server Error",
                MESSAGE, ex.getMessage()
        ));
    }

}

