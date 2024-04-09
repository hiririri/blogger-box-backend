package com.qjiang.bloggerboxbackend.mapper;

import com.qjiang.bloggerboxbackend.model.CategoryEntity;
import com.qjiang.bloggerboxbackend.dto.CategoryDto;

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
