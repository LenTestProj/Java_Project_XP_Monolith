package com.example.spring_xp_monolith.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.spring_xp_monolith.Services.ProductsService;
import com.example.spring_xp_monolith.dto.ApiResponse;
import com.example.spring_xp_monolith.dto.products.CreateProductRequestDto;
import com.example.spring_xp_monolith.models.Products;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductsService productsService;

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<ApiResponse<Products>> createProduct(
        @Valid @RequestPart("request")
        CreateProductRequestDto request,
        
        @RequestPart("displayImage")
        MultipartFile displayImage,

        @RequestPart("coverImage")
        MultipartFile coverImage
     ) {

        Products product = productsService.createProduct(request,displayImage,coverImage);

        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(product));
    }
}