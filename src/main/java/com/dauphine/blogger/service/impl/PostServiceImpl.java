package com.dauphine.blogger.service.impl;

import com.dauphine.blogger.dto.CreatePostRequest;
import com.dauphine.blogger.exception.PostIntegrityViolationException;
import com.dauphine.blogger.exception.PostNotFoundException;
import com.dauphine.blogger.exception.PostTransactionException;
import com.dauphine.blogger.model.CategoryEntity;
import com.dauphine.blogger.model.PostEntity;
import com.dauphine.blogger.repository.PostRepository;
import com.dauphine.blogger.service.CategoryService;
import com.dauphine.blogger.service.PostService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Transactional
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final CategoryService categoryService;

    @Override
    public PostEntity create(CreatePostRequest postRequest) {
        if (postRequest.getTitle() == null || postRequest.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Post title cannot be null or empty");
        }

        CategoryEntity category = categoryService.getById(postRequest.getCategoryId());

        PostEntity entity = PostEntity.builder()
                .id(UUID.randomUUID().toString())
                .title(postRequest.getTitle())
                .content(postRequest.getContent())
                .createdDate(LocalDateTime.now())
                .category(category)
                .build();

        if (postRepository.existsById(entity.getId())) {
            throw new PostIntegrityViolationException("Post with id '" + entity.getId() + "' already exists");
        }

        return postRepository.save(entity);
    }

    @Override
    public PostEntity getPostById(String postId) {
        return postRepository.findByTitle(postId)
                .orElseThrow(() -> new PostNotFoundException("Post not found with title: " + postId));
    }

    @Override
    public List<PostEntity> getAll() {
        try {
            categoryService.getAll();
            return postRepository.findAll((root, query, builder) -> {
                query.orderBy(builder.desc(root.get("createdDate")));
                return query.getRestriction();
            });
        } catch (DataAccessException e) {
            throw new PostTransactionException("Failed to retrieve all posts", e);
        }
    }

    @Override
    public void update(String postId, String title, String content) {
        PostEntity postEntity = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("Post not found with id: " + postId));

        try {
            postEntity.setTitle(title);
            postEntity.setContent(content);
            postRepository.save(postEntity);
        } catch (DataAccessException e) {
            throw new PostTransactionException("Failed to update post", e);
        }
    }

    @Override
    public void deleteById(String postId) {
        PostEntity postEntity = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("Post not found with title: " + postId));

        try {
            postRepository.delete(postEntity);
        } catch (DataAccessException e) {
            throw new PostTransactionException("Failed to delete post", e);
        }
    }

    @Override
    public List<PostEntity> getAllByCategoryId(String categoryId) {
        return postRepository.findByCategory(categoryService.getById(categoryId));
    }
}
