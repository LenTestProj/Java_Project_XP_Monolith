package com.example.spring_xp_monolith.Controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.spring_xp_monolith.Services.ProductsService;
import com.example.spring_xp_monolith.dto.ApiResponse;
import com.example.spring_xp_monolith.dto.products.CreateProductRequestDto;
import com.example.spring_xp_monolith.dto.products.ProductResponseDto;
import com.example.spring_xp_monolith.dto.products.UpdateProductRequestDto;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductsService productsService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse<ProductResponseDto>> createProduct(
            @Valid @ModelAttribute CreateProductRequestDto request,
            @RequestPart("displayImage") MultipartFile displayImage,
            @RequestPart("coverImage") MultipartFile coverImage) {

        ProductResponseDto product = productsService.createProduct(request, displayImage, coverImage);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(product));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductResponseDto>>> getProducts(
            @RequestParam(required = false) Long categoryId) {
        return ResponseEntity.ok(ApiResponse.success(productsService.getProducts(categoryId)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponseDto>> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(productsService.getProductById(id)));
    }

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse<ProductResponseDto>> updateProduct(
            @Valid @ModelAttribute UpdateProductRequestDto request,
            @RequestPart(value = "displayImage", required = false) MultipartFile displayImage,
            @RequestPart(value = "coverImage", required = false) MultipartFile coverImage) {
        return ResponseEntity.ok(ApiResponse.success(productsService.updateProduct(request, displayImage, coverImage)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponseDto>> deleteProduct(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(productsService.deleteProduct(id)));
    }
}
