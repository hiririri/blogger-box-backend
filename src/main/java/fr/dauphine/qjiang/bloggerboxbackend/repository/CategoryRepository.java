package fr.dauphine.qjiang.bloggerboxbackend.repository;

import fr.dauphine.qjiang.bloggerboxbackend.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CategoryRepository extends JpaRepository<CategoryEntity, String>, JpaSpecificationExecutor<CategoryEntity> {
    CategoryEntity findByName(String name);
}
