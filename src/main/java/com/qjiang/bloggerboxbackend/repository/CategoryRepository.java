package com.qjiang.bloggerboxbackend.repository;

import com.qjiang.bloggerboxbackend.model.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CategoryRepository extends JpaRepository<CategoryEntity, String>, JpaSpecificationExecutor<CategoryEntity> {
    CategoryEntity findByName(String name);
}
