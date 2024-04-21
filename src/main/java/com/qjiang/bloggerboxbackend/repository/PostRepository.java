package com.qjiang.bloggerboxbackend.repository;

import com.qjiang.bloggerboxbackend.model.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface PostRepository extends JpaRepository<PostEntity, String>, JpaSpecificationExecutor<PostEntity> {
    Optional<PostEntity> findByTitle(String title);

    boolean existsByTitle(String title);
}
