package com.dauphine.blogger.service;

import com.dauphine.blogger.model.CategoryEntity;

import java.util.List;

public interface CategoryService {
    CategoryEntity create(String name);

    CategoryEntity getById(String id);

    CategoryEntity update(String id, String name);

    void deleteById(String id);

    List<CategoryEntity> getAll();
}
