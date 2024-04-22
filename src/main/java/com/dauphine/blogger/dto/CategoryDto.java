package com.dauphine.blogger.dto;

import lombok.*;

@Builder
@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
public class CategoryDto {
    String id;
    String name;
}
