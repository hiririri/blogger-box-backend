package com.qjiang.bloggerboxbackend.mapper;

import com.qjiang.bloggerboxbackend.model.CategoryEntity;
import com.qjiang.bloggerboxbackend.model.PostEntity;
import com.qjiang.bloggerboxbackend.dto.PostDto;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class PostMapper {
    public static PostEntity toEntity(PostDto post, CategoryEntity category) {
        return PostEntity.builder()
                .title(post.getTitle())
                .content(post.getContent())
                .createdDate(post.getCreatedDate())
                .category(category)
                .build();
    }

    public static PostDto toDto(PostEntity post) {
        return PostDto.builder()
                .title(post.getTitle())
                .content(post.getContent())
                .createdDate(post.getCreatedDate())
                .category(CategoryMapper.toDto(post.getCategory()))
                .build();
    }
}
