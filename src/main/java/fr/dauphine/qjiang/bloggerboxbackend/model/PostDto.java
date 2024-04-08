package fr.dauphine.qjiang.bloggerboxbackend.model;

import lombok.*;

import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
public class PostDto {
    String title;
    String content;
    LocalDate createdDate;
    CategoryDto category;
}
