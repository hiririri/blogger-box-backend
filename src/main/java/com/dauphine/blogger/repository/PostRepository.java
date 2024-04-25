package com.dauphine.blogger.repository;

import com.dauphine.blogger.model.CategoryEntity;
import com.dauphine.blogger.model.PostEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<PostEntity, String>, JpaSpecificationExecutor<PostEntity> {
    Optional<PostEntity> findByTitle(String title);

    List<PostEntity> findByCategory(CategoryEntity category);

    List<PostEntity> findAll(Specification specification);
}
