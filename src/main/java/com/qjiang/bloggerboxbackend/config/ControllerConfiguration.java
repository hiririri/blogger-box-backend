package com.qjiang.bloggerboxbackend.config;

import com.qjiang.bloggerboxbackend.controller.CategoryController;
import com.qjiang.bloggerboxbackend.service.CategoryService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ControllerConfiguration {
    @Bean
    public CategoryController categoryController(CategoryService categoryService) {
        return new CategoryController(categoryService);
    }
}
