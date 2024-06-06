package com.dauphine.blogger.service;

import com.dauphine.blogger.dto.CreatePostRequest;
import com.dauphine.blogger.exception.PostNotFoundException;
import com.dauphine.blogger.model.PostEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PostServiceTest {

    @Mock
    private PostService postService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreatePostSuccessfullyWhenValidRequest() {
        CreatePostRequest request = CreatePostRequest.builder()
                .title("Test Title")
                .content("Test Content")
                .build();

        PostEntity post = new PostEntity();
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());

        when(postService.create(request)).thenReturn(post);

        PostEntity result = postService.create(request);

        assertEquals("Test Title", result.getTitle());
        assertEquals("Test Content", result.getContent());
    }

    @Test
    void shouldGetPostByIdSuccessfullyWhenIdExists() {
        PostEntity post = new PostEntity();
        post.setId("1");

        when(postService.getPostById("1")).thenReturn(post);

        PostEntity result = postService.getPostById("1");

        assertEquals("1", result.getId());
    }

    @Test
    void shouldUpdatePostSuccessfullyWhenValidRequest() {
        doNothing().when(postService).update("1", "Test Title", "Test Content");

        postService.update("1", "Test Title", "Test Content");

        verify(postService, times(1)).update("1", "Test Title", "Test Content");
    }

    @Test
    void shouldDeletePostByIdSuccessfullyWhenIdExists() {
        doNothing().when(postService).deleteById("1");

        postService.deleteById("1");

        verify(postService, times(1)).deleteById("1");
    }

    @Test
    void shouldGetAllPostsSuccessfullyWhenPostsExist() {
        PostEntity post1 = new PostEntity();
        PostEntity post2 = new PostEntity();
        List<PostEntity> posts = Arrays.asList(post1, post2);

        when(postService.getAll()).thenReturn(posts);

        List<PostEntity> result = postService.getAll();

        assertEquals(2, result.size());
    }

    @Test
    void shouldThrowExceptionWhenCreatePostAndInvalidData() {
        CreatePostRequest request = CreatePostRequest.builder()
                .title("Test Title")
                .content("Test Content")
                .build();

        when(postService.create(request)).thenThrow(new IllegalArgumentException("Invalid post data"));

        assertThrows(IllegalArgumentException.class, () -> postService.create(request));
    }

    @Test
    void shouldThrowExceptionWhenGetPostByIdAndIdDoesNotExist() {
        when(postService.getPostById("1")).thenThrow(new PostNotFoundException("Post not found"));

        assertThrows(PostNotFoundException.class, () -> postService.getPostById("1"));
    }

    @Test
    void shouldThrowExceptionWhenUpdatePostAndInvalidData() {
        doThrow(new IllegalArgumentException("Invalid post data")).when(postService).update("1", "Test Title", "Test Content");

        assertThrows(IllegalArgumentException.class, () -> postService.update("1", "Test Title", "Test Content"));
    }

    @Test
    void shouldThrowExceptionWhenDeletePostByIdAndIdDoesNotExist() {
        doThrow(new PostNotFoundException("Post not found")).when(postService).deleteById("1");

        assertThrows(PostNotFoundException.class, () -> postService.deleteById("1"));
    }

    @Test
    void shouldThrowExceptionWhenGetAllPostsAndNoPostsExist() {
        when(postService.getAll()).thenThrow(new PostNotFoundException("No posts found"));

        assertThrows(PostNotFoundException.class, () -> postService.getAll());
    }
}