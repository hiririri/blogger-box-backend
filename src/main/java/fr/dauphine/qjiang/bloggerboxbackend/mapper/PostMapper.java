package fr.dauphine.qjiang.bloggerboxbackend.mapper;

import fr.dauphine.qjiang.bloggerboxbackend.entity.PostEntity;
import fr.dauphine.qjiang.bloggerboxbackend.model.PostDto;
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
