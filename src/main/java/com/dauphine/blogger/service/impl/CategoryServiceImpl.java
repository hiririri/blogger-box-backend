package com.dauphine.blogger.service.impl;

import com.dauphine.blogger.dto.CreationCategoryRequest;
import com.dauphine.blogger.dto.CategoryDto;
import com.dauphine.blogger.exception.CategoryIntegrityViolationException;
import com.dauphine.blogger.exception.CategoryNotFoundException;
import com.dauphine.blogger.mapper.CategoryMapper;
import com.dauphine.blogger.model.CategoryEntity;
import com.dauphine.blogger.repository.CategoryRepository;
import com.dauphine.blogger.service.CategoryService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.hibernate.TransactionException;

import java.util.List;

@AllArgsConstructor
@Transactional
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;

    @Override
    public CategoryDto createCategory(CreationCategoryRequest creationRequest) {
        if (creationRequest.getName() == null || creationRequest.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Category name cannot be null or empty");
        }

        if (categoryRepository.existsByName(creationRequest.getName())) {
            throw new CategoryIntegrityViolationException("A category with the name " + creationRequest.getName() + " already exists");
        }

        try {
            CategoryEntity entity = CategoryMapper.toEntity(creationRequest);
            entity = categoryRepository.save(entity);
            return CategoryMapper.toDto(entity);
        } catch (Exception e) {
            throw new TransactionException("Failed to create category due to a transaction issue", e);
        }
    }

    @Override
    public CategoryDto getCategoryById(String id) {
        CategoryEntity categoryEntity = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category with id " + id + " not found"));
        return CategoryMapper.toDto(categoryEntity);
    }

    @Override
    public CategoryDto updateCategory(String id, String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("New category name cannot be null or empty");
        }

        if (categoryRepository.existsByName(name)) {
            throw new CategoryIntegrityViolationException("A category with the name " + name + " already exists");
        }

        CategoryEntity categoryEntity = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category with id " + id + " not found"));

        try {
            categoryEntity.setName(name);
            categoryEntity = categoryRepository.save(categoryEntity);
            return CategoryMapper.toDto(categoryEntity);
        } catch (Exception e) {
            throw new TransactionException("Failed to update category due to a transaction issue", e);
        }
    }

    @Override
    public CategoryDto deleteCategory(String id) {
        CategoryEntity categoryEntity = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category with id " + id + " not found"));

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
