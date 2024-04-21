package com.qjiang.bloggerboxbackend.service.impl;

import com.qjiang.bloggerboxbackend.dto.PostDto;
import com.qjiang.bloggerboxbackend.exception.CategoryIntegrityViolationException;
import com.qjiang.bloggerboxbackend.exception.PostIntegrityViolationException;
import com.qjiang.bloggerboxbackend.exception.PostNotFoundException;
import com.qjiang.bloggerboxbackend.exception.PostTransactionException;
import com.qjiang.bloggerboxbackend.mapper.PostMapper;
import com.qjiang.bloggerboxbackend.model.CategoryEntity;
import com.qjiang.bloggerboxbackend.model.PostEntity;
import com.qjiang.bloggerboxbackend.repository.CategoryRepository;
import com.qjiang.bloggerboxbackend.repository.PostRepository;
import com.qjiang.bloggerboxbackend.service.PostService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Transactional
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public void addPost(PostDto postDto) {
        if (postDto.getCategory() == null || postDto.getCategory().getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Category name cannot be null or empty");
        }

        if (postDto.getTitle() == null || postDto.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Post title cannot be null or empty");
        }

        if (postRepository.existsByTitle(postDto.getTitle())) {
            throw new PostIntegrityViolationException("A post with the title " + postDto.getTitle() + " already exists");
        }

        CategoryEntity category = categoryRepository.findByName(postDto.getCategory().getName())
                .orElseGet(() -> {
                    try {
                        return categoryRepository.save(CategoryEntity.builder()
                                .name(postDto.getCategory().getName())
                                .build());
                    } catch (DataIntegrityViolationException e) {
                        throw new CategoryIntegrityViolationException("Failed to save new category due to integrity constraints", e);
                    }
                });

        // Attempt to save the post
        try {
            postRepository.save(PostMapper.toEntity(postDto, category));
        } catch (DataIntegrityViolationException e) {
            throw new PostIntegrityViolationException("Failed to save the post due to integrity constraints", e);
        }
    }

    @Override
    public PostDto getPostByTitle(String title) {
        return postRepository.findByTitle(title)
                .map(PostMapper::toDto)
                .orElseThrow(() -> new PostNotFoundException("Post with title '" + title + "' not found"));
    }

    @Override
    public List<PostDto> getAllPosts() {
        try {
            return postRepository.findAll().stream()
                    .map(PostMapper::toDto)
                    .collect(Collectors.toList());
        } catch (DataAccessException e) {
            throw new PostTransactionException("Failed to retrieve all posts", e);
        }
    }

    @Override
    public void updatePost(String title, String content) {
        PostEntity postEntity = postRepository.findByTitle(title)
                .orElseThrow(() -> new PostNotFoundException("Post not found with title: " + title));

        try {
            postEntity.setContent(content);
            postRepository.save(postEntity);
        } catch (DataAccessException e) {
            throw new PostTransactionException("Failed to update post", e);
        }
    }

    @Override
    public void deletePost(String title) {
        PostEntity postEntity = postRepository.findByTitle(title)
                .orElseThrow(() -> new PostNotFoundException("Post not found with title: " + title));

        try {
            postRepository.delete(postEntity);
        } catch (DataAccessException e) {
            throw new PostTransactionException("Failed to delete post", e);
        }
    }
}
