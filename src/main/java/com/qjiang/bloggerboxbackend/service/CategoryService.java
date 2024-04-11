package com.qjiang.bloggerboxbackend.service;

import com.qjiang.bloggerboxbackend.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto createCategory(CategoryDto category);

    CategoryDto getCategoryByName(String testCategory);

    CategoryDto updateCategory(String newName, String oldName);

    CategoryDto deleteCategory(String name);

    List<CategoryDto> getAllCategories();
}
