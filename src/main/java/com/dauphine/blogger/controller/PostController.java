package com.dauphine.blogger.controller;

import com.dauphine.blogger.dto.CreatePostRequest;
import com.dauphine.blogger.dto.UpdatePostRequest;
import com.dauphine.blogger.exception.ApiError;
import com.dauphine.blogger.model.PostEntity;
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
import static org.springframework.http.HttpStatus.NO_CONTENT;

@Tag(name = "Posts")
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/posts")
@Slf4j
public class PostController {
    private PostService postService;

    @Operation(
            summary = "Create a new post",
            description = "Create a new post with the given title and content"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Created"),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class)))})
    @PostMapping("/")
    @ResponseStatus(value = CREATED)
    public void addPost(
            @Parameter(description = "Post object to be created", required = true)
            @Valid
            @RequestBody CreatePostRequest post) {
        log.info("Creating post with title: {}", post.getTitle());
        log.info("POST: http://localhost:8080/api/v1/post");
        postService.create(post);
    }

    @Operation(
            summary = "Get post by id",
            description = "Get the post with the given id"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class)))})
    @GetMapping("/post/{postId}")
    public ResponseEntity<PostEntity> getPostById(
            @Parameter(description = "Id of the post to retrieve", required = true)
            @Valid
            @PathVariable("postId") String postId) {
        log.info("Retrieving post with id: {}", postId);
        log.info("GET: http://localhost:8080/api/v1/post/{}", postId);
        return ResponseEntity.ok(postService.getPostById(postId));
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
    @GetMapping("/")
    public ResponseEntity<List<PostEntity>> getAllPostsOrderByCreatedDate() {
        log.info("Retrieving all posts");
        log.info("GET: http://localhost:8080/api/v1/posts");
        return ResponseEntity.ok(postService.getAll());
    }

    @Operation(
            summary = "Get all posts by category",
            description = "Get all posts in the database by category"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class)))})
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<PostEntity>> getAllPostsByCategoryId(@PathVariable String categoryId) {
        log.info("Retrieving all posts by category: {}", categoryId);
        log.info("GET: http://localhost:8080/api/v1/posts/category/{}", categoryId);
        return ResponseEntity.ok(postService.getAllByCategoryId(categoryId));
    }

    @Operation(summary = "Update post by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "204", description = "Updated"),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class)))})
    @PutMapping("/")
    public void updatePost(
            @Parameter(description = "Post object to be updated", required = true)
            @Valid
            @RequestBody UpdatePostRequest post
    ) {
        log.info("Updating post with title: {}", post.getTitle());
        log.info("PUT: http://localhost:8080/api/v1/post");
        postService.update(post.getPostId(), post.getTitle(), post.getContent());
    }

    @Operation(summary = "Delete post by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No Content"),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class)))})
    @DeleteMapping("/post/{postId}")
    @ResponseStatus(value = NO_CONTENT)
    public void deletePost(
            @Parameter(description = "Id of the post to delete", required = true)
            @Valid
            @PathVariable("postId") String postId
    ) {
        log.info("Deleting post with id: {}", postId);
        log.info("DELETE: http://localhost:8080/api/v1/post/{}", postId);
        postService.deleteById(postId);
    }
}
