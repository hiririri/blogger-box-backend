package com.dauphine.blogger.service;

import com.dauphine.blogger.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto createCategory(CategoryDto category);

    CategoryDto getCategoryByName(String testCategory);

    CategoryDto updateCategory(String newName, String oldName);

    CategoryDto deleteCategory(String name);

    List<CategoryDto> getAllCategories();
}
