package com.example.spring_xp_monolith.Controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring_xp_monolith.Services.FoodTypeService;
import com.example.spring_xp_monolith.dto.ApiResponse;
import com.example.spring_xp_monolith.dto.foodTypes.AddFoodTypeRequestDto;
import com.example.spring_xp_monolith.dto.foodTypes.FoodTypeResponseDto;
import com.example.spring_xp_monolith.dto.foodTypes.UpdateFoodTypeRequestDto;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/food-types")
@RequiredArgsConstructor
public class FoodTypeController {

    private final FoodTypeService foodTypeService;

    @PostMapping
    public ResponseEntity<ApiResponse<FoodTypeResponseDto>> createFoodType(
            @Valid @RequestBody AddFoodTypeRequestDto request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(foodTypeService.createFoodType(request)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<FoodTypeResponseDto>>> getFoodTypes() {
        return ResponseEntity.ok(ApiResponse.success(foodTypeService.getFoodTypes()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<FoodTypeResponseDto>> getFoodTypeById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(foodTypeService.getFoodTypeById(id)));
    }

    @PutMapping
    public ResponseEntity<ApiResponse<FoodTypeResponseDto>> updateFoodType(
            @Valid @RequestBody UpdateFoodTypeRequestDto request) {
        return ResponseEntity.ok(ApiResponse.success(foodTypeService.updateFoodType(request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<FoodTypeResponseDto>> deleteFoodType(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(foodTypeService.deleteFoodType(id)));
    }
}
