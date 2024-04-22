package com.dauphine.blogger.config;

import com.dauphine.blogger.controller.CategoryController;
import com.dauphine.blogger.controller.PostController;
import com.dauphine.blogger.service.CategoryService;
import com.dauphine.blogger.service.PostService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ControllerConfiguration {
    @Bean
    public CategoryController categoryController(CategoryService categoryService) {
        return new CategoryController(categoryService);
    }

    @Bean
    public PostController postController(PostService postService) {
        return new PostController(postService);
    }
}
