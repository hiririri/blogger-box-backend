package com.qjiang.bloggerboxbackend.exception;

public record ApiError(String errorCode, String errorDescription) {
    public static final String CATEGORY_NOT_FOUND = "CATEGORY_NOT_FOUND";

    public static String messageCategoryNotFound(String name) {
        return String.format("Category %s does not exist", name);
    }
}
