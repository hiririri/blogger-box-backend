package com.qjiang.bloggerboxbackend;

import com.qjiang.bloggerboxbackend.mapper.CategoryMapper;
import com.qjiang.bloggerboxbackend.model.CategoryEntity;
import com.qjiang.bloggerboxbackend.dto.CategoryDto;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CategoryMapperTest {
    @Test
    void testToEntity() {
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
    void testToDto() {
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
