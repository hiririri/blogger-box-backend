package com.dauphine.blogger.controller;

import com.dauphine.blogger.dto.CreationCategoryRequest;
import com.dauphine.blogger.dto.CategoryDto;
import com.dauphine.blogger.exception.ApiError;
import com.dauphine.blogger.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@Tag(name = "Categories")
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/categories")
@Slf4j
public class CategoryController {
    private CategoryService categoryService;

    @Operation(summary = "Create a new category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class)))})
    @PostMapping("/")
    @ResponseStatus(value = CREATED)
    public ResponseEntity<CategoryDto> createCategory(@RequestBody() CreationCategoryRequest category) {
        log.info("Creating category with name: {}", category.getName());
        log.info("POST /api/v1/categories/category");
        return ResponseEntity.ok(categoryService.createCategory(category));
    }

    @Operation(summary = "Get category by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class)))})
    @GetMapping("/category/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable() String id) {
        log.info("Getting category with id: {}", id);
        log.info("GET /api/v1/categories/category/{}", id);
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    @Operation(summary = "Update category by name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class)))})
    @PutMapping("/category/{id}/{name}")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable() String id, @PathVariable() String name) {
        log.info("Updating category with id: {} and name: {}", id, name);
        log.info("PUT /api/v1/categories/category/{}/{}", id, name);
        return ResponseEntity.ok(categoryService.updateCategory(id, name));
    }

    @Operation(summary = "Delete category by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class)))})
    @DeleteMapping("/category/{id}")
    public ResponseEntity<CategoryDto> deleteCategory(@PathVariable("id") String id) {
        log.info("Deleting category with id: {}", id);
        log.info("DELETE /api/v1/categories/category/{}", id);
        return ResponseEntity.ok(categoryService.deleteCategory(id));
    }

    @Operation(summary = "Get all categories")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class)))})
    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        log.info("Getting all categories");
        log.info("GET /api/v1/categories/");
        return ResponseEntity.ok(categoryService.getAllCategories());
    }
}
