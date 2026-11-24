package com.example.spring_xp_monolith.dto.foodTypes;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AddFoodTypeRequestDto {
    @NotBlank(message = "Food type name is required")
    private String name;
}
