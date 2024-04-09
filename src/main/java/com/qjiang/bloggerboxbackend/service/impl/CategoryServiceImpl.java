package com.qjiang.bloggerboxbackend.service.impl;

import com.qjiang.bloggerboxbackend.exception.CategoryNotFoundException;
import com.qjiang.bloggerboxbackend.mapper.CategoryMapper;
import com.qjiang.bloggerboxbackend.common.Result;
import com.qjiang.bloggerboxbackend.model.CategoryEntity;
import com.qjiang.bloggerboxbackend.dto.CategoryDto;
import com.qjiang.bloggerboxbackend.repository.CategoryRepository;
import com.qjiang.bloggerboxbackend.service.CategoryService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;

    @Override
    public Result<CategoryDto> createCategory(CategoryDto category) {
        try {
            CategoryEntity entity = CategoryMapper.toEntity(category);
            return Result.success(CategoryMapper.toDto(categoryRepository.save(entity)), "Category created");
        } catch (Exception e) {
            return Result.failure(category, e.getMessage());
        }
    }

    @Override
    public CategoryDto getCategoryByName(String name) {
        CategoryEntity categoryEntity = getCategory(name);
        return CategoryMapper.toDto(categoryEntity);
    }

    @Override
    public CategoryDto updateCategory(String newName, String oldName) {
        try {
            CategoryEntity categoryEntity = getCategory(oldName);
            categoryEntity.setName(newName);
            return CategoryMapper.toDto(categoryRepository.save(categoryEntity));
        } catch (CategoryNotFoundException e) {
            throw new CategoryNotFoundException("Category not found");
        }
    }

    @Override
    public CategoryDto deleteCategory(String name) {
        CategoryEntity categoryEntity = getCategory(name);
        categoryRepository.delete(categoryEntity);
        return CategoryMapper.toDto(categoryEntity);
    }

    private CategoryEntity getCategory(String oldName) {
        return categoryRepository.findByName(oldName);
    }
}
