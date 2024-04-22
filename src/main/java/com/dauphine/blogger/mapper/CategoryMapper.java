package com.dauphine.blogger.mapper;

import com.dauphine.blogger.dto.CreationCategoryRequest;
import com.dauphine.blogger.model.CategoryEntity;
import com.dauphine.blogger.dto.CategoryDto;

public class CategoryMapper {
    public static CategoryEntity toEntity(CategoryDto category) {
        return CategoryEntity.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

    public static CategoryEntity toEntity(CreationCategoryRequest creationCategoryRequest) {
        return CategoryEntity.builder()
                .name(creationCategoryRequest.getName())
                .build();
    }

    public static CategoryDto toDto(CategoryEntity categoryEntity) {
        return CategoryDto.builder()
                .id(categoryEntity.getId())
                .name(categoryEntity.getName())
                .build();
    }
}
