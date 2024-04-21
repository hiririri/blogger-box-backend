package com.qjiang.bloggerboxbackend.service;

import com.qjiang.bloggerboxbackend.dto.PostDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PostService {
    void addPost(PostDto post);

    PostDto getPostByTitle(String title);

    List<PostDto> getAllPosts();

    void updatePost(String title, String content);

    void deletePost(String title);
}
