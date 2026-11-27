package com.example.spring_xp_monolith.Controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.spring_xp_monolith.Services.CategoryService;
import com.example.spring_xp_monolith.dto.ApiResponse;
import com.example.spring_xp_monolith.dto.categories.CategoryResponseDto;
import com.example.spring_xp_monolith.dto.categories.CreateCategoryRequestDto;
import com.example.spring_xp_monolith.dto.categories.UpdateCategoryRequestDto;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse<CategoryResponseDto>> createCategory(
            @Valid @ModelAttribute CreateCategoryRequestDto request,
            @RequestPart("displayImage") MultipartFile displayImage,
            @RequestPart("coverImage") MultipartFile bannerImage) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(categoryService.createCategory(request, displayImage, bannerImage)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CategoryResponseDto>>> getCategories() {
        return ResponseEntity.ok(ApiResponse.success(categoryService.getCategories()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryResponseDto>> getCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(categoryService.getCategoryById(id)));
    }

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse<CategoryResponseDto>> updateCategory(
            @Valid @ModelAttribute UpdateCategoryRequestDto request,
            @RequestPart(value = "displayImage", required = false) MultipartFile displayImage,
            @RequestPart(value = "bannerImage", required = false) MultipartFile bannerImage) {
        return ResponseEntity.ok(ApiResponse.success(categoryService.updateCategory(request, displayImage, bannerImage)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryResponseDto>> deleteCategory(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(categoryService.deleteCategory(id)));
    }
}
