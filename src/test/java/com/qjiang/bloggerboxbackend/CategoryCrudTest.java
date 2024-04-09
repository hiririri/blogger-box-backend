package com.qjiang.bloggerboxbackend;

import com.qjiang.bloggerboxbackend.common.Result;
import com.qjiang.bloggerboxbackend.dto.CategoryDto;
import com.qjiang.bloggerboxbackend.model.CategoryEntity;
import com.qjiang.bloggerboxbackend.repository.CategoryRepository;
import com.qjiang.bloggerboxbackend.service.impl.CategoryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@Import(CategoryCrudTest.CategoryTestConfiguration.class)
@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
class CategoryCrudTest {
    private static final String NAME = "Test Category";

    private CategoryServiceImpl categoryService;
    @Autowired
    private CategoryRepository categoryRepository;

    @BeforeEach
    public void setUp() {
        categoryService = new CategoryServiceImpl(categoryRepository);
    }

    @Test
    void should_return_success_when_create_category() {
        // Given
        CategoryDto categoryDto = CategoryDto.builder().name(NAME).build();

        // When
        var result = categoryService.createCategory(categoryDto);

        // Then
        var entity = categoryService.getCategoryByName(NAME);
        assertThat(entity).isNotNull();
        assertThat(result).isEqualTo(Result.success(CategoryDto.builder().name(NAME).build(), "Category created"));
    }

    @Sql({"/data.sql"})
    @Test
    void should_return_category() {
        // When
        var result = categoryService.getCategoryByName(NAME);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo(NAME);
    }

    @Sql({"/data.sql"})
    @Test
    void should_update_category() {
        // Given
        var newName = "New Category";

        // When
        var result = categoryService.updateCategory(newName, NAME);

        // Then
        assertThat(result).isNotNull()
                .isEqualTo(CategoryDto.builder().name(newName).build());
    }

    @Sql({"/data.sql"})
    @Test
    void should_delete_category() {
        // When
        var result = categoryService.deleteCategory(NAME);

        // Then
        assertThat(result).isNotNull().isEqualTo(CategoryDto.builder().name(NAME).build());

        System.out.println(result);
    }

    @Configuration
    @EnableJpaRepositories(basePackageClasses = CategoryRepository.class)
    @EntityScan(basePackageClasses = CategoryEntity.class)
    static class CategoryTestConfiguration {
    }
}
