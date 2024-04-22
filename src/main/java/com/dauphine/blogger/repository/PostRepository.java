package com.dauphine.blogger.repository;

import com.dauphine.blogger.model.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<PostEntity, String>, JpaSpecificationExecutor<PostEntity> {
    Optional<PostEntity> findByTitle(String title);

    boolean existsByTitle(String title);

    List<PostEntity> findByCategoryName(String name);
}
