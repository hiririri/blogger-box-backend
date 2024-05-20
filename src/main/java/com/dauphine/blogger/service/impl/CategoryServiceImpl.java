package com.dauphine.blogger.service.impl;

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
    public CategoryEntity create(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Category name cannot be null or empty");
        }

        if (categoryRepository.existsByName(name)) {
            throw new CategoryIntegrityViolationException("A category with the name {" + name + "} already exists");
        }

        try {
            return categoryRepository.save(CategoryEntity.builder().name(name).build());
        } catch (Exception e) {
            throw new TransactionException("Failed to create category due to a transaction issue", e);
        }
    }

    @Override
    public CategoryEntity getById(String id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category with id " + id + " not found"));
    }

    @Override
    public CategoryEntity update(String id, String name) {
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
            return categoryRepository.save(categoryEntity);
        } catch (Exception e) {
            throw new TransactionException("Failed to update category due to a transaction issue", e);
        }
    }

    @Override
    public void deleteById(String id) {
        CategoryEntity categoryEntity = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category with id " + id + " not found"));

        try {
            categoryRepository.delete(categoryEntity);
        } catch (Exception e) {
            throw new TransactionException("Failed to delete category due to a transaction issue", e);
        }
    }

    @Override
    public List<CategoryEntity> getAll() {
        return categoryRepository.findAll();
    }
}
