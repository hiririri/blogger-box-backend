package com.dauphine.blogger.service;

import com.dauphine.blogger.dto.CreatePostRequest;
import com.dauphine.blogger.model.PostEntity;

import java.util.List;

public interface PostService {
    PostEntity create(CreatePostRequest post);

    PostEntity getPostById(String title);

    List<PostEntity> getAll();

    void update(String postId, String title, String content);

    void deleteById(String id);

    List<PostEntity> getAllByCategoryId(String id);
}
