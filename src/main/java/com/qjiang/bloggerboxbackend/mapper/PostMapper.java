package com.qjiang.bloggerboxbackend.mapper;

import com.qjiang.bloggerboxbackend.model.PostEntity;
import com.qjiang.bloggerboxbackend.dto.PostDto;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PostMapper {
    public static PostEntity toEntity(PostDto post) {
        return PostEntity.builder()
                .title(post.getTitle())
                .content(post.getContent())
                .createdDate(post.getCreatedDate())
                .category(CategoryMapper.toEntity(post.getCategory()))
                .build();
    }
}
