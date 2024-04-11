package com.qjiang.bloggerboxbackend.service.impl;

import com.qjiang.bloggerboxbackend.dto.CategoryDto;
import com.qjiang.bloggerboxbackend.exception.CategoryIntegrityViolationException;
import com.qjiang.bloggerboxbackend.exception.CategoryNotFoundException;
import com.qjiang.bloggerboxbackend.mapper.CategoryMapper;
import com.qjiang.bloggerboxbackend.model.CategoryEntity;
import com.qjiang.bloggerboxbackend.repository.CategoryRepository;
import com.qjiang.bloggerboxbackend.service.CategoryService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.hibernate.TransactionException;

import java.util.List;

@AllArgsConstructor
@Transactional
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        if (categoryDto.getName() == null || categoryDto.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Category name cannot be null or empty");
        }

        if (categoryRepository.existsByName(categoryDto.getName())) {
            throw new CategoryIntegrityViolationException("A category with the name " + categoryDto.getName() + " already exists");
        }

        try {
            CategoryEntity entity = CategoryMapper.toEntity(categoryDto);
            entity = categoryRepository.save(entity);
            return CategoryMapper.toDto(entity);
        } catch (Exception e) {
            throw new TransactionException("Failed to create category due to a transaction issue", e);
        }
    }

    @Override
    public CategoryDto getCategoryByName(String name) {
        CategoryEntity categoryEntity = categoryRepository.findByName(name)
                .orElseThrow(() -> new CategoryNotFoundException("Category with name " + name + " not found"));
        return CategoryMapper.toDto(categoryEntity);
    }

    @Override
    public CategoryDto updateCategory(String oldName, String newName) {
        if (newName == null || newName.trim().isEmpty()) {
            throw new IllegalArgumentException("New category name cannot be null or empty");
        }

        CategoryEntity categoryEntity = categoryRepository.findByName(oldName)
                .orElseThrow(() -> new CategoryNotFoundException("Category with name " + oldName + " not found"));

        if (!oldName.equals(newName) && categoryRepository.existsByName(newName)) {
            throw new CategoryIntegrityViolationException("A category with the name " + newName + " already exists");
        }

        try {
            categoryEntity.setName(newName);
            categoryEntity = categoryRepository.save(categoryEntity);
            return CategoryMapper.toDto(categoryEntity);
        } catch (Exception e) {
            throw new TransactionException("Failed to update category due to a transaction issue", e);
        }
    }

    @Override
    public CategoryDto deleteCategory(String name) {
        CategoryEntity categoryEntity = categoryRepository.findByName(name)
                .orElseThrow(() -> new CategoryNotFoundException("Category with name " + name + " not found"));

        try {
            categoryRepository.delete(categoryEntity);
        } catch (Exception e) {
            throw new TransactionException("Failed to delete category due to a transaction issue", e);
        }
        return CategoryMapper.toDto(categoryEntity);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(CategoryMapper::toDto)
                .toList();
    }
}
