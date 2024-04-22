package com.dauphine.blogger.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
public class PostDto {
    String title;
    String content;
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate createdDate;
    CategoryDto category;
}
