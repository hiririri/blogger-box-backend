package com.qjiang.bloggerboxbackend.repository;

import com.qjiang.bloggerboxbackend.model.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<CategoryEntity, String>, JpaSpecificationExecutor<CategoryEntity> {
    Optional<CategoryEntity> findByName(String name);

    boolean existsByName(String name);
}
