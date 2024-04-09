package com.qjiang.bloggerboxbackend.service.impl;

import com.qjiang.bloggerboxbackend.mapper.PostMapper;
import com.qjiang.bloggerboxbackend.dto.PostDto;
import com.qjiang.bloggerboxbackend.repository.PostRepository;
import com.qjiang.bloggerboxbackend.service.PostService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PostServiceImpl implements PostService {
    private PostRepository postRepository;

    @Override
    public void addPost(PostDto post) {
        postRepository.save(PostMapper.toEntity(post));
    }
}
