package com.dauphine.blogger.exception;

public class PostIntegrityViolationException extends RuntimeException {
    public PostIntegrityViolationException(String message) {
        super(message);
    }

    public PostIntegrityViolationException(String message, Throwable cause) {
        super(message, cause);
    }
}
