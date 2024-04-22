package com.dauphine.blogger.service.impl;

import com.dauphine.blogger.mapper.CategoryMapper;
import com.dauphine.blogger.dto.CategoryDto;
import com.dauphine.blogger.exception.CategoryIntegrityViolationException;
import com.dauphine.blogger.exception.CategoryNotFoundException;
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
