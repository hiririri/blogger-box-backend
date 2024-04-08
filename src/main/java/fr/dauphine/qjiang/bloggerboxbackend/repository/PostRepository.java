package fr.dauphine.qjiang.bloggerboxbackend.repository;

import fr.dauphine.qjiang.bloggerboxbackend.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PostRepository extends JpaRepository<PostEntity, String>, JpaSpecificationExecutor<PostEntity> {
}
