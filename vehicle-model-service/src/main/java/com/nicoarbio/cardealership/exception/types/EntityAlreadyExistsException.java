package com.nicoarbio.cardealership.exception.types;

public class EntityAlreadyExistsException extends RuntimeException {

    public EntityAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

}
