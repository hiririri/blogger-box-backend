package com.dauphine.blogger.controller;

import com.dauphine.blogger.dto.PostDto;
import com.dauphine.blogger.dto.PostUpdateDto;
import com.dauphine.blogger.exception.ApiError;
import com.dauphine.blogger.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@Tag(name = "Posts")
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
@Slf4j
public class PostController {
    private PostService postService;

    @Operation(
            summary = "Create a new post",
            description = "Create a new post with the given title and content"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class)))})
    @PostMapping("/post")
    @ResponseStatus(value = CREATED)
    public void addPost(
            @Parameter(description = "Post object to be created", required = true)
            @Valid
            @RequestBody PostDto post) {
        log.info("Creating post with title: {}", post.getTitle());
        log.info("POST: http://localhost:8080/api/v1/post");
        postService.addPost(post);
    }

    @Operation(
            summary = "Get post by title",
            description = "Get the post with the given title"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class)))})
    @GetMapping("/post/{title}")
    public ResponseEntity<PostDto> getPostByTitle(
            @Parameter(description = "Title of the post to retrieve", required = true)
            @Valid
            @PathVariable("title") String title) {
        log.info("Retrieving post with title: {}", title);
        log.info("GET: http://localhost:8080/api/v1/post/{}", title);
        return ResponseEntity.ok(postService.getPostByTitle(title));
    }

    @Operation(
            summary = "Get all posts",
            description = "Get all posts in the database"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class)))})
    @GetMapping("/posts")
    public ResponseEntity<List<PostDto>> getAllPosts() {
        log.info("Retrieving all posts");
        log.info("GET: http://localhost:8080/api/v1/posts");
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @Operation(summary = "Update post by title")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class)))})
    @PutMapping("/post")
    public void updatePost(
            @Parameter(description = "Post object to be updated", required = true)
            @Valid
            @RequestBody PostUpdateDto post
    ) {
        log.info("Updating post with title: {}", post.getTitle());
        log.info("PUT: http://localhost:8080/api/v1/post");
        postService.updatePost(post.getTitle(), post.getContent());
    }

    @Operation(summary = "Delete post by title")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class)))})
    @DeleteMapping("/post/{title}")
    public void deletePost(
            @Parameter(description = "Title of the post to delete", required = true)
            @Valid
            @PathVariable("title") String title
    ) {
        log.info("Deleting post with title: {}", title);
        log.info("DELETE: http://localhost:8080/api/v1/post/{}", title);
        postService.deletePost(title);
    }
}
