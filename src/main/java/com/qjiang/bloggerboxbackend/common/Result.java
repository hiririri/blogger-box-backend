package com.qjiang.bloggerboxbackend.common;

import static java.util.Objects.requireNonNull;

public sealed interface Result<T> {
    record Success<T>(T value, String message) implements Result<T> {
        public Success {
            requireNonNull(value, "value is required");
            requireNonNull(message, "message is required");
        }
    }

    record Failure<T>(T value, String message) implements Result<T> {
        public Failure {
            requireNonNull(value, "value to create is required");
            requireNonNull(message, "message is required");
        }
    }

    static <T> Result<T> success(T value, String message) {
        return new Success<>(value, message);
    }

    static <T> Result<T> failure(T value, String message) {
        return new Failure<>(value, message);
    }
}
