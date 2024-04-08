package fr.dauphine.qjiang.bloggerboxbackend.service.impl;

import fr.dauphine.qjiang.bloggerboxbackend.mapper.PostMapper;
import fr.dauphine.qjiang.bloggerboxbackend.model.PostDto;
import fr.dauphine.qjiang.bloggerboxbackend.repository.PostRepository;
import fr.dauphine.qjiang.bloggerboxbackend.service.PostService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PostServiceImpl implements PostService {
    private PostRepository postRepository;

    @Override
    public void addPost(PostDto post) {
        postRepository.save(PostMapper.toEntity(post));
    }
}
