package fr.dauphine.qjiang.bloggerboxbackend.service;

import fr.dauphine.qjiang.bloggerboxbackend.common.Result;
import fr.dauphine.qjiang.bloggerboxbackend.model.CategoryDto;

public interface CategoryService {
    Result<CategoryDto> createCategory(CategoryDto category);

    CategoryDto getCategoryByName(String testCategory);

    Result<CategoryDto> updateCategory(String newName, String oldName);

    Result<CategoryDto> deleteCategory(String name);
}
