package com.dauphine.blogger.exception;

public class PostTransactionException extends RuntimeException {
    public PostTransactionException(String message) {
        super(message);
    }

    public PostTransactionException(String message, Throwable cause) {
        super(message, cause);
    }
}
