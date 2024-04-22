package com.dauphine.blogger;

import com.dauphine.blogger.dto.CategoryDto;
import com.dauphine.blogger.mapper.CategoryMapper;
import com.dauphine.blogger.model.CategoryEntity;
import org.assertj.core.api.Assertions;
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
        Assertions.assertThat(entity).isNotNull();
        Assertions.assertThat(entity.getName()).isEqualTo("testCategory");
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
        Assertions.assertThat(dto).isNotNull();
        Assertions.assertThat(dto.getName()).isEqualTo("testCategory");
    }
}
