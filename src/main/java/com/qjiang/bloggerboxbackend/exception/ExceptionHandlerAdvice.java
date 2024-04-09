package com.qjiang.bloggerboxbackend.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@RestControllerAdvice
public class ExceptionHandlerAdvice {
    public static final String HANDLE_EXCEPTION_MSG = "Handle Exception ";

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ApiError> handleCategoryNotFoundException(CategoryNotFoundException e) {
        log.error(HANDLE_EXCEPTION_MSG, e);

        return new ResponseEntity<>(new ApiError(ApiError.CATEGORY_NOT_FOUND, ApiError.messageCategoryNotFound(e.getName())), NOT_FOUND);
    }
}
