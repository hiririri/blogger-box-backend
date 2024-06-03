package com.dauphine.blogger.controller;

import com.dauphine.blogger.dto.CreationCategoryRequest;
import com.dauphine.blogger.dto.UpdateCategoryRequest;
import com.dauphine.blogger.exception.CategoryNotFoundException;
import com.dauphine.blogger.model.CategoryEntity;
import com.dauphine.blogger.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class CategoryControllerTest {

    @InjectMocks
    private CategoryController categoryController;

    @Mock
    private CategoryService categoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createCategorySuccessfully() {
        CreationCategoryRequest request = CreationCategoryRequest.builder().build();
        request.setName("Test Category");

        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName("Test Category");

        when(categoryService.create(any())).thenReturn(categoryEntity);

        ResponseEntity<CategoryEntity> response = categoryController.createCategory(request);

        assertEquals("Test Category", response.getBody().getName());
        verify(categoryService, times(1)).create(any());
    }

    @Test
    void getCategoryByIdSuccessfully() {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName("Test Category");

        when(categoryService.getById(any())).thenReturn(categoryEntity);

        ResponseEntity<CategoryEntity> response = categoryController.getCategoryById("1");

        assertEquals("Test Category", response.getBody().getName());
        verify(categoryService, times(1)).getById(any());
    }

    @Test
    void updateCategorySuccessfully() {
        UpdateCategoryRequest request = UpdateCategoryRequest.builder().build();
        request.setId("1");
        request.setName("Updated Category");

        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName("Updated Category");

        when(categoryService.update(any(), any())).thenReturn(categoryEntity);

        ResponseEntity<CategoryEntity> response = categoryController.updateCategory(request);

        assertEquals("Updated Category", response.getBody().getName());
        verify(categoryService, times(1)).update(any(), any());
    }

    @Test
    void deleteCategorySuccessfully() {
        doNothing().when(categoryService).deleteById(any());

        categoryController.deleteCategory("1");

        verify(categoryService, times(1)).deleteById(any());
    }

    @Test
    void getAllCategoriesSuccessfully() {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName("Test Category");

        when(categoryService.getAll()).thenReturn(Collections.singletonList(categoryEntity));

        ResponseEntity<List<CategoryEntity>> response = categoryController.getAllCategories();

        assertEquals("Test Category", response.getBody().get(0).getName());
        verify(categoryService, times(1)).getAll();
    }

    @Test
    void getCategoryByIdThrowsException() {
        when(categoryService.getById(any())).thenThrow(new CategoryNotFoundException("Category not found"));

        try {
            categoryController.getCategoryById("1");
        } catch (CategoryNotFoundException e) {
            assertEquals("Category Category not found does not exist", e.getMessage());
        }

        verify(categoryService, times(1)).getById(any());
    }
}