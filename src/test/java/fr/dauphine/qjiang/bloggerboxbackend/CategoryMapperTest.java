package fr.dauphine.qjiang.bloggerboxbackend;

import fr.dauphine.qjiang.bloggerboxbackend.entity.CategoryEntity;
import fr.dauphine.qjiang.bloggerboxbackend.mapper.CategoryMapper;
import fr.dauphine.qjiang.bloggerboxbackend.model.CategoryDto;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CategoryMapperTest {
    @Test
    public void testToEntity() {
        // Given
        CategoryDto categoryDto = CategoryDto.builder()
                .name("testCategory")
                .build();

        // When
        var entity = CategoryMapper.toEntity(categoryDto);

        // Then
        assertThat(entity).isNotNull();
        assertThat(entity.getName()).isEqualTo("testCategory");
    }

    @Test
    public void testToDto() {
        // Given
        var entity = CategoryEntity.builder()
                .name("testCategory")
                .build();

        // When
        var dto = CategoryMapper.toDto(entity);

        // Then
        assertThat(dto).isNotNull();
        assertThat(dto.getName()).isEqualTo("testCategory");
    }
}
