package com.example.spring_xp_monolith.dto.foodTypes;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UpdateFoodTypeRequestDto extends AddFoodTypeRequestDto {
    @NotNull(message = "Food type id is required")
    private Long id;
}
