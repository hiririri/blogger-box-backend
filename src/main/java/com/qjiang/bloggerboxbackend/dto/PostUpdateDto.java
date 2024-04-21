package com.qjiang.bloggerboxbackend.dto;

import lombok.*;

@Builder
@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
public class PostUpdateDto {
    String title;
    String content;
}
