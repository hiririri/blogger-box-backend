package com.dauphine.blogger.exception;

import lombok.Getter;

@Getter
public class CategoryNotFoundException extends RuntimeException {
    private final String name;

    public CategoryNotFoundException(String name) {
        super(message(name));
        this.name = name;
    }

    private static String message(String name) {
        return String.format("Category %s does not exist", name);
    }
}
