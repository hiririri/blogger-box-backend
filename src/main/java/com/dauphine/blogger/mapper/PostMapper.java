package com.dauphine.blogger.mapper;

import com.dauphine.blogger.dto.CreatePostRequest;
import com.dauphine.blogger.model.CategoryEntity;
import com.dauphine.blogger.model.PostEntity;
import com.dauphine.blogger.dto.PostDto;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

@AllArgsConstructor
public class PostMapper {
    public static PostEntity toEntity(CreatePostRequest post, CategoryEntity category) {
        return PostEntity.builder()
                .title(post.getTitle())
                .content(post.getContent())
                .createdDate(LocalDate.now())
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
