package com.dauphine.blogger.config;

import com.dauphine.blogger.repository.CategoryRepository;
import com.dauphine.blogger.repository.PostRepository;
import com.dauphine.blogger.service.CategoryService;
import com.dauphine.blogger.service.impl.CategoryServiceImpl;
import com.dauphine.blogger.service.impl.PostServiceImpl;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
public class ServiceConfiguration {
    @Bean
    public PostServiceImpl postService(PostRepository postRepository, CategoryRepository categoryRepository) {
        return new PostServiceImpl(postRepository, categoryRepository);
    }

    @Bean
    public CategoryService categoryService(CategoryRepository categoryRepository) {
        return new CategoryServiceImpl(categoryRepository);
    }

    @Configuration
    @EnableJpaRepositories(basePackages = "com.dauphine.blogger.repository")
    @EntityScan(basePackages = "com.dauphine.blogger.model")
    public static class JpaConfiguration {
    }
}
