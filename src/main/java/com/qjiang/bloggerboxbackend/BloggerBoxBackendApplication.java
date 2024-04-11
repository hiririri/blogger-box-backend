package com.qjiang.bloggerboxbackend;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition
public class BloggerBoxBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BloggerBoxBackendApplication.class, args);
    }

}
