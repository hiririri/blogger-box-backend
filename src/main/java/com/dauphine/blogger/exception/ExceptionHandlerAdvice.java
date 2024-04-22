package com.dauphine.blogger.exception;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.TransactionException;
import org.springframework.dao.TransientDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionHandlerAdvice {
    public static final String HANDLE_EXCEPTION_MSG = "Handle Exception ";

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ApiError> handleCategoryNotFoundException(CategoryNotFoundException e) {
        log.error(HANDLE_EXCEPTION_MSG + e.getMessage(), e);
        return new ResponseEntity<>(new ApiError("CATEGORY_NOT_FOUND", e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CategoryIntegrityViolationException.class)
    public ResponseEntity<ApiError> handleCategoryIntegrityViolationException(CategoryIntegrityViolationException e) {
        log.error(HANDLE_EXCEPTION_MSG + e.getMessage(), e);
        return new ResponseEntity<>(new ApiError("INTEGRITY_VIOLATION", e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TransactionException.class)
    public ResponseEntity<ApiError> handleCategoryTransactionException(TransactionException e) {
        log.error(HANDLE_EXCEPTION_MSG + e.getMessage(), e);
        return new ResponseEntity<>(new ApiError("TRANSACTION_ERROR", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiError> handleIllegalArgumentException(IllegalArgumentException e) {
        log.error(HANDLE_EXCEPTION_MSG + e.getMessage(), e);
        return new ResponseEntity<>(new ApiError("ILLEGAL_ARGUMENT", e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TransientDataAccessException.class)
    public ResponseEntity<ApiError> handleTransientDataAccessException(TransientDataAccessException e) {
        log.error(HANDLE_EXCEPTION_MSG + e.getMessage(), e);
        return new ResponseEntity<>(new ApiError("TRANSIENT_DATA_ACCESS", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(PostNotFoundException.class)
    public ResponseEntity<ApiError> handlePostNotFoundException(PostNotFoundException e) {
        log.error(HANDLE_EXCEPTION_MSG + e.getMessage(), e);
        return new ResponseEntity<>(new ApiError("POST_NOT_FOUND", e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PostIntegrityViolationException.class)
    public ResponseEntity<ApiError> handlePostIntegrityViolationException(PostIntegrityViolationException e) {
        log.error(HANDLE_EXCEPTION_MSG + e.getMessage(), e);
        return new ResponseEntity<>(new ApiError("POST_INTEGRITY_VIOLATION", e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PostTransactionException.class)
    public ResponseEntity<ApiError> handlePostTransactionException(PostTransactionException e) {
        log.error(HANDLE_EXCEPTION_MSG + e.getMessage(), e);
        return new ResponseEntity<>(new ApiError("POST_TRANSACTION_ERROR", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
