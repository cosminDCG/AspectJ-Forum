package com.company.exception;

public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException(String errorMessage) {
        super(errorMessage);
    }
}
