package fr.dauphine.qjiang.bloggerboxbackend.service.impl;

import fr.dauphine.qjiang.bloggerboxbackend.common.Result;
import fr.dauphine.qjiang.bloggerboxbackend.entity.CategoryEntity;
import fr.dauphine.qjiang.bloggerboxbackend.mapper.CategoryMapper;
import fr.dauphine.qjiang.bloggerboxbackend.model.CategoryDto;
import fr.dauphine.qjiang.bloggerboxbackend.repository.CategoryRepository;
import fr.dauphine.qjiang.bloggerboxbackend.service.CategoryService;
import lombok.AllArgsConstructor;

import static fr.dauphine.qjiang.bloggerboxbackend.mapper.CategoryMapper.toDto;

@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;

    @Override
    public Result<CategoryDto> createCategory(CategoryDto category) {
        CategoryEntity entity = CategoryMapper.toEntity(category);
        return Result.success(toDto(categoryRepository.save(entity)), "Category created");
    }

    @Override
    public CategoryDto getCategoryByName(String name) {
        CategoryEntity categoryEntity = getCategory(name);
        return toDto(categoryEntity);
    }

    @Override
    public Result<CategoryDto> updateCategory(String newName, String oldName) {
        CategoryEntity categoryEntity = getCategory(oldName);
        categoryEntity.setName(newName);
        return Result.success(toDto(categoryRepository.save(categoryEntity)), "Category updated");
    }

    @Override
    public Result<CategoryDto> deleteCategory(String name) {
        CategoryEntity categoryEntity = getCategory(name);
        categoryRepository.delete(categoryEntity);
        return Result.success(toDto(categoryEntity), "Category deleted");
    }

    private CategoryEntity getCategory(String oldName) {
        return categoryRepository.findByName(oldName);
    }
}
