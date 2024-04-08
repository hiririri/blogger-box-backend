package fr.dauphine.qjiang.bloggerboxbackend.mapper;

import fr.dauphine.qjiang.bloggerboxbackend.entity.CategoryEntity;
import fr.dauphine.qjiang.bloggerboxbackend.model.CategoryDto;

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
