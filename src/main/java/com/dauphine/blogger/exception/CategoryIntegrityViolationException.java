package com.dauphine.blogger.exception;

public class CategoryIntegrityViolationException extends RuntimeException {
    public CategoryIntegrityViolationException(String message) {
        super(message);
    }

    public CategoryIntegrityViolationException(String message, Throwable cause) {
        super(message, cause);
    }
}

