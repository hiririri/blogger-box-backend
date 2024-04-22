package com.dauphine.blogger.service;

import com.dauphine.blogger.dto.PostDto;

import java.util.List;

public interface PostService {
    void addPost(PostDto post);

    PostDto getPostByTitle(String title);

    List<PostDto> getAllPosts();

    void updatePost(String title, String content);

    void deletePost(String title);
}