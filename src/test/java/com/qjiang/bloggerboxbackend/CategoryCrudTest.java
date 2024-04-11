package com.qjiang.bloggerboxbackend;

import com.qjiang.bloggerboxbackend.dto.CategoryDto;
import com.qjiang.bloggerboxbackend.exception.CategoryIntegrityViolationException;
import com.qjiang.bloggerboxbackend.exception.CategoryNotFoundException;
import com.qjiang.bloggerboxbackend.model.CategoryEntity;
import com.qjiang.bloggerboxbackend.repository.CategoryRepository;
import com.qjiang.bloggerboxbackend.service.impl.CategoryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
class CategoryCrudTest {
    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createCategorySuccessfully() {
        CategoryDto categoryDto = CategoryDto.builder().name("Test").build();

        when(categoryRepository.existsByName(anyString())).thenReturn(false);
        when(categoryRepository.save(any())).thenReturn(new CategoryEntity());

        CategoryDto result = categoryService.createCategory(categoryDto);

        assertNotNull(result);
        verify(categoryRepository, times(1)).existsByName(anyString());
        verify(categoryRepository, times(1)).save(any());
    }

    @Test
    void createCategoryWithExistingName() {
        CategoryDto categoryDto = CategoryDto.builder().name("Test").build();

        when(categoryRepository.existsByName(anyString())).thenReturn(true);

        assertThrows(CategoryIntegrityViolationException.class, () -> categoryService.createCategory(categoryDto));
        verify(categoryRepository, times(1)).existsByName(anyString());
    }

    @Test
    void getCategoryByNameSuccessfully() {
        when(categoryRepository.findByName(anyString())).thenReturn(Optional.of(new CategoryEntity()));

        CategoryDto result = categoryService.getCategoryByName("Test");

        assertNotNull(result);
        verify(categoryRepository, times(1)).findByName(anyString());
    }

    @Test
    void getCategoryByNameNotFound() {
        when(categoryRepository.findByName(anyString())).thenReturn(Optional.empty());

        assertThrows(CategoryNotFoundException.class, () -> categoryService.getCategoryByName("Test"));
        verify(categoryRepository, times(1)).findByName(anyString());
    }

    @Test
    void updateCategorySuccessfully() {
        when(categoryRepository.findByName(anyString())).thenReturn(Optional.of(new CategoryEntity()));
        when(categoryRepository.existsByName(anyString())).thenReturn(false);
        when(categoryRepository.save(any())).thenReturn(new CategoryEntity());

        CategoryDto result = categoryService.updateCategory("OldTest", "NewTest");

        assertNotNull(result);
        verify(categoryRepository, times(1)).findByName(anyString());
        verify(categoryRepository, times(1)).existsByName(anyString());
        verify(categoryRepository, times(1)).save(any());
    }

    @Test
    void updateCategoryWithExistingName() {
        when(categoryRepository.findByName(anyString())).thenReturn(Optional.of(new CategoryEntity()));
        when(categoryRepository.existsByName(anyString())).thenReturn(true);

        assertThrows(CategoryIntegrityViolationException.class, () -> categoryService.updateCategory("OldTest", "NewTest"));
        verify(categoryRepository, times(1)).findByName(anyString());
        verify(categoryRepository, times(1)).existsByName(anyString());
    }

    @Test
    void deleteCategorySuccessfully() {
        when(categoryRepository.findByName(anyString())).thenReturn(Optional.of(new CategoryEntity()));

        CategoryDto result = categoryService.deleteCategory("Test");

        assertNotNull(result);
        verify(categoryRepository, times(1)).findByName(anyString());
        verify(categoryRepository, times(1)).delete((CategoryEntity) any());
    }

    @Test
    void deleteCategoryNotFound() {
        when(categoryRepository.findByName(anyString())).thenReturn(Optional.empty());

        assertThrows(CategoryNotFoundException.class, () -> categoryService.deleteCategory("Test"));
        verify(categoryRepository, times(1)).findByName(anyString());
    }

    @Test
    void createCategoryWithNullName() {
        CategoryDto categoryDto = CategoryDto.builder().build();

        assertThrows(IllegalArgumentException.class, () -> categoryService.createCategory(categoryDto));
    }

    @Test
    void createCategoryWithEmptyName() {
        CategoryDto categoryDto = CategoryDto.builder().name("").build();

        assertThrows(IllegalArgumentException.class, () -> categoryService.createCategory(categoryDto));
    }

    @Test
    void updateCategoryWithNullNewName() {
        assertThrows(IllegalArgumentException.class, () -> categoryService.updateCategory("OldTest", null));
    }

    @Test
    void updateCategoryWithEmptyNewName() {
        assertThrows(IllegalArgumentException.class, () -> categoryService.updateCategory("OldTest", ""));
    }

    @Test
    void updateCategoryNotFound() {
        when(categoryRepository.findByName(anyString())).thenReturn(Optional.empty());

        assertThrows(CategoryNotFoundException.class, () -> categoryService.updateCategory("OldTest", "NewTest"));
        verify(categoryRepository, times(1)).findByName(anyString());
    }

    @Test
    void getAllCategoriesSuccessfully() {
        when(categoryRepository.findAll()).thenReturn(List.of(new CategoryEntity()));

        List<CategoryDto> result = categoryService.getAllCategories();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        verify(categoryRepository, times(1)).findAll();
    }
}
