package com.placetrack.backend.exception;

public class DuplicateCompanyException extends RuntimeException {

    public DuplicateCompanyException(String message) {
        super(message);
    }
}