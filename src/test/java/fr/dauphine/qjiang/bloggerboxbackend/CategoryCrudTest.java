package fr.dauphine.qjiang.bloggerboxbackend;

import fr.dauphine.qjiang.bloggerboxbackend.common.Result;
import fr.dauphine.qjiang.bloggerboxbackend.entity.CategoryEntity;
import fr.dauphine.qjiang.bloggerboxbackend.model.CategoryDto;
import fr.dauphine.qjiang.bloggerboxbackend.repository.CategoryRepository;
import fr.dauphine.qjiang.bloggerboxbackend.service.impl.CategoryServiceImpl;
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
public class CategoryCrudTest {
    private static final String NAME = "Test Category";

    private CategoryServiceImpl categoryService;
    @Autowired
    private CategoryRepository categoryRepository;

    @BeforeEach
    public void setUp() {
        categoryService = new CategoryServiceImpl(categoryRepository);
    }

    @Test
    public void should_return_success_when_create_category() {
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
    public void should_return_category() {
        // When
        var result = categoryService.getCategoryByName(NAME);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo(NAME);
    }

    @Sql({"/data.sql"})
    @Test
    public void should_update_category() {
        // Given
        var newName = "New Category";

        // When
        var result = categoryService.updateCategory(newName, NAME);

        // Then
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(Result.success(CategoryDto.builder().name(newName).build(), "Category updated"));
    }

    @Sql({"/data.sql"})
    @Test
    public void should_delete_category() {
        // When
        var result = categoryService.deleteCategory(NAME);

        // Then
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(Result.success(CategoryDto.builder().name(NAME).build(), "Category deleted"));

        System.out.println(result);
    }

    @Configuration
    @EnableJpaRepositories(basePackageClasses = CategoryRepository.class)
    @EntityScan(basePackageClasses = CategoryEntity.class)
    static class CategoryTestConfiguration {
    }
}
