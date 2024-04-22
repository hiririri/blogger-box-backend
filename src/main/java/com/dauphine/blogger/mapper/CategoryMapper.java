package com.dauphine.blogger.mapper;

import com.dauphine.blogger.model.CategoryEntity;
import com.dauphine.blogger.dto.CategoryDto;

public class CategoryMapper {
    public static CategoryEntity toEntity(CategoryDto category) {
        return CategoryEntity.builder()
                .name(category.getName())
                .build();
    }

    public static CategoryDto toDto(CategoryEntity categoryEntity) {
        return CategoryDto.builder()
                .name(categoryEntity.getName())
                .build();
    }
}
