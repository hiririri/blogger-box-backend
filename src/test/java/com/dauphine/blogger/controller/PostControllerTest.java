package com.dauphine.blogger.controller;

import com.dauphine.blogger.dto.CreatePostRequest;
import com.dauphine.blogger.dto.UpdatePostRequest;
import com.dauphine.blogger.exception.PostNotFoundException;
import com.dauphine.blogger.model.CategoryEntity;
import com.dauphine.blogger.model.PostEntity;
import com.dauphine.blogger.repository.CategoryRepository;
import com.dauphine.blogger.service.PostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class PostControllerTest {

    @InjectMocks
    private PostController postController;

    @Mock
    private PostService postService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addPostSuccessfully() {
        CreatePostRequest request = CreatePostRequest.builder().build();

        request.setTitle("Test Post");
        request.setContent("Test Content");
        request.setCategoryId("1");

        when(postService.create(any())).thenReturn(null); // Assuming create() method returns null on success

        postController.addPost(request);

        verify(postService, times(1)).create(any());
    }

    @Test
    void getPostByIdSuccessfully() {
        PostEntity postEntity = new PostEntity();
        postEntity.setTitle("Test Post");

        when(postService.getPostById(any())).thenReturn(postEntity);

        ResponseEntity<PostEntity> response = postController.getPostById("1");

        assertEquals("Test Post", response.getBody().getTitle());
        verify(postService, times(1)).getPostById(any());
    }

    @Test
    void updatePostSuccessfully() {
        UpdatePostRequest request = UpdatePostRequest.builder().build();
        request.setPostId("1");
        request.setTitle("Updated Post");
        request.setContent("Updated Content");

        doNothing().when(postService).update(any(), any(), any());

        postController.updatePost(request);

        verify(postService, times(1)).update(any(), any(), any());
    }

    @Test
    void deletePostSuccessfully() {
        doNothing().when(postService).deleteById(any());

        postController.deletePost("1");

        verify(postService, times(1)).deleteById(any());
    }

    @Test
    void getAllPostsSuccessfully() {
        PostEntity postEntity = new PostEntity();
        postEntity.setTitle("Test Post");

        when(postService.getAll()).thenReturn(Collections.singletonList(postEntity));

        ResponseEntity<List<PostEntity>> response = postController.getAllPostsOrderByCreatedDate();

        assertEquals("Test Post", response.getBody().get(0).getTitle());
        verify(postService, times(1)).getAll();
    }

    @Test
    void getAllPostsByCategoryIdSuccessfully() {
        PostEntity postEntity = new PostEntity();
        postEntity.setTitle("Test Post");

        when(postService.getAllByCategoryId(any())).thenReturn(Collections.singletonList(postEntity));

        ResponseEntity<List<PostEntity>> response = postController.getAllPostsByCategoryId("1");

        assertEquals("Test Post", response.getBody().get(0).getTitle());
        verify(postService, times(1)).getAllByCategoryId(any());
    }

    @Test
    void getPostByIdThrowsException() {
        when(postService.getPostById(any())).thenThrow(new PostNotFoundException("Post not found"));

        try {
            postController.getPostById("1");
        } catch (PostNotFoundException e) {
            assertEquals("Post not found", e.getMessage());
        }

        verify(postService, times(1)).getPostById(any());
    }

    @Test
    void getAllPostsByCategoryIdThrowsException() {
        when(postService.getAllByCategoryId(any())).thenThrow(new PostNotFoundException("Posts not found for the category"));

        Exception exception = assertThrows(PostNotFoundException.class, () -> {
            postController.getAllPostsByCategoryId("1");
        });

        assertEquals("Posts not found for the category", exception.getMessage());
        verify(postService, times(1)).getAllByCategoryId(any());
    }
}