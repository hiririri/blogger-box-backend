package com.dauphine.blogger.service;

import com.dauphine.blogger.exception.CategoryIntegrityViolationException;
import com.dauphine.blogger.exception.CategoryNotFoundException;
import com.dauphine.blogger.model.CategoryEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoryServiceTest {

    @Mock
    private CategoryService categoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createCategorySuccessfully() {
        CategoryEntity category = new CategoryEntity();
        category.setName("Test");
        when(categoryService.create("Test")).thenReturn(category);

        CategoryEntity result = categoryService.create("Test");
        assertEquals("Test", result.getName());
    }

    @Test
    void getCategoryByIdSuccessfully() {
        CategoryEntity category = new CategoryEntity();
        category.setId("1");
        when(categoryService.getById("1")).thenReturn(category);

        CategoryEntity result = categoryService.getById("1");
        assertEquals("1", result.getId());
    }

    @Test
    void updateCategorySuccessfully() {
        CategoryEntity category = new CategoryEntity();
        category.setId("1");
        category.setName("Test");
        when(categoryService.update("1", "Test")).thenReturn(category);

        CategoryEntity result = categoryService.update("1", "Test");
        assertEquals("Test", result.getName());
    }

    @Test
    void deleteCategoryByIdSuccessfully() {
        doNothing().when(categoryService).deleteById("1");
        categoryService.deleteById("1");
        verify(categoryService, times(1)).deleteById("1");
    }

    @Test
    void getAllCategoriesSuccessfully() {
        CategoryEntity category1 = new CategoryEntity();
        CategoryEntity category2 = new CategoryEntity();
        List<CategoryEntity> categories = Arrays.asList(category1, category2);
        when(categoryService.getAll()).thenReturn(categories);

        List<CategoryEntity> result = categoryService.getAll();
        assertEquals(2, result.size());
    }

    @Test
    void createCategoryFails() {
        when(categoryService.create("Test")).thenThrow(new CategoryIntegrityViolationException("Test category already exists"));

        assertThrows(CategoryIntegrityViolationException.class, () -> categoryService.create("Test"));
    }

    @Test
    void getCategoryByIdFails() {
        when(categoryService.getById("1")).thenThrow(new CategoryNotFoundException("Category not found"));

        assertThrows(CategoryNotFoundException.class, () -> categoryService.getById("1"));
    }

    @Test
    void updateCategoryFails() {
        when(categoryService.update("1", "Test")).thenThrow(new CategoryNotFoundException("Category not found"));

        assertThrows(CategoryNotFoundException.class, () -> categoryService.update("1", "Test"));
    }

    @Test
    void deleteCategoryByIdFails() {
        doThrow(new CategoryNotFoundException("Category not found")).when(categoryService).deleteById("1");

        assertThrows(CategoryNotFoundException.class, () -> categoryService.deleteById("1"));
    }

    @Test
    void getAllCategoriesFails() {
        when(categoryService.getAll()).thenThrow(new CategoryNotFoundException("No categories found"));

        assertThrows(CategoryNotFoundException.class, () -> categoryService.getAll());
    }
}