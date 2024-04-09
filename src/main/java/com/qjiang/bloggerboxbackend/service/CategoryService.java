package com.qjiang.bloggerboxbackend.service;

import com.qjiang.bloggerboxbackend.common.Result;
import com.qjiang.bloggerboxbackend.dto.CategoryDto;

public interface CategoryService {
    Result<CategoryDto> createCategory(CategoryDto category);

    CategoryDto getCategoryByName(String testCategory);

    CategoryDto updateCategory(String newName, String oldName);

    CategoryDto deleteCategory(String name);
}
