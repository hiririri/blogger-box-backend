package com.dauphine.blogger.dto;

import lombok.*;

@Builder
@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
public class UpdateCategoryRequest {
    String name;
}
