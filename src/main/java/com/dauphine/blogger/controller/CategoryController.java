package com.dauphine.blogger.controller;

import com.dauphine.blogger.dto.CreationCategoryRequest;
import com.dauphine.blogger.dto.UpdateCategoryRequest;
import com.dauphine.blogger.exception.ApiError;
import com.dauphine.blogger.model.CategoryEntity;
import com.dauphine.blogger.service.CategoryService;
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

@Tag(name = "Categories")
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/categories")
@Slf4j
public class CategoryController {
    private CategoryService categoryService;

    @Operation(summary = "Create a new category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Created"),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class)))})
    @PostMapping("/")
    @ResponseStatus(value = CREATED)
    public ResponseEntity<CategoryEntity> createCategory(
            @Parameter(description = "Category object to be created", required = true)
            @Valid
            @RequestBody() CreationCategoryRequest category) {
        log.info("Creating category with name: {}", category.getName());
        log.info("POST: http://localhost:8080/api/v1/categories/");
        return ResponseEntity.ok(categoryService.create(category.getName()));
    }

    @Operation(summary = "Get category by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "404", description = "Not Found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class)))})
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<CategoryEntity> getCategoryById(
            @Parameter(description = "Category id", required = true)
            @Valid
            @PathVariable() String categoryId) {
        log.info("Getting category with id: {}", categoryId);
        log.info("GET: http://localhost:8080/api/v1/categories/category/{}", categoryId);
        return ResponseEntity.ok(categoryService.getById(categoryId));
    }

    @Operation(summary = "Update category by name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "204", description = "Updated"),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "404", description = "Not Found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class)))})
    @PutMapping("/")
    public ResponseEntity<CategoryEntity> updateCategory(
            @Parameter(description = "Category object to be updated", required = true)
            @Valid
            @RequestBody UpdateCategoryRequest categoryRequest
    ) {
        log.info("Updating category with id: {} and name: {}", categoryRequest.getId(), categoryRequest.getName());
        log.info("PUT: http://localhost:8080/api/v1/categories");
        return ResponseEntity.ok(categoryService.update(categoryRequest.getId(), categoryRequest.getName()));
    }

    @Operation(summary = "Delete category by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "204", description = "No Content"),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "404", description = "Not Found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class)))})
    @DeleteMapping("/category/{categoryId}")
    @ResponseStatus(value = NO_CONTENT)
    public void deleteCategory(
            @Parameter(description = "Category id", required = true)
            @Valid
            @PathVariable() String categoryId) {
        log.info("Deleting category with id: {}", categoryId);
        log.info("DELETE: http://localhost:8080/api/v1/categories/category/{}", categoryId);
        categoryService.deleteById(categoryId);
    }

    @Operation(summary = "Get all categories")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class)))})
    @GetMapping("/")
    public ResponseEntity<List<CategoryEntity>> getAllCategories() {
        log.info("Getting all categories");
        log.info("GET: http://localhost:8080/api/v1/categories/");
        return ResponseEntity.ok(categoryService.getAll());
    }
}
