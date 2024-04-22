package com.dauphine.blogger.service;

import com.dauphine.blogger.dto.CreationCategoryRequest;
import com.dauphine.blogger.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto createCategory(CreationCategoryRequest category);

    CategoryDto getCategoryById(String id);

    CategoryDto updateCategory(String id, String name);

    CategoryDto deleteCategory(String id);

    List<CategoryDto> getAllCategories();
}
