package fr.dauphine.qjiang.bloggerboxbackend.config;

import fr.dauphine.qjiang.bloggerboxbackend.repository.CategoryRepository;
import fr.dauphine.qjiang.bloggerboxbackend.repository.PostRepository;
import fr.dauphine.qjiang.bloggerboxbackend.service.impl.CategoryServiceImpl;
import fr.dauphine.qjiang.bloggerboxbackend.service.impl.PostServiceImpl;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
public class ServiceConfiguration {
    @Bean
    public PostServiceImpl postService(PostRepository postRepository) {
        return new PostServiceImpl(postRepository);
    }

    @Bean
    public CategoryServiceImpl categoryService(CategoryRepository categoryRepository) {
        return new CategoryServiceImpl(categoryRepository);
    }

    @Configuration
    @EnableJpaRepositories(basePackages = "fr.dauphine.qjiang.bloggerboxbackend.repository")
    @EntityScan(basePackages = "fr.dauphine.qjiang.bloggerboxbackend.entity")
    public static class JpaConfiguration {
    }
}
