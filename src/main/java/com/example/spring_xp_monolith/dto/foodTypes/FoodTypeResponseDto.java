package com.example.spring_xp_monolith.dto.foodTypes;

import java.time.LocalDateTime;

import com.example.spring_xp_monolith.models.FoodTypes;

import lombok.Data;

@Data
public class FoodTypeResponseDto {
    private Long id;
    private String name;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public static FoodTypeResponseDto from(FoodTypes f) {
        FoodTypeResponseDto dto = new FoodTypeResponseDto();
        dto.setId(f.getId());
        dto.setName(f.getName());
        dto.setCreatedDate(f.getCreatedDate());
        dto.setUpdatedDate(f.getUpdatedDate());
        return dto;
    }
}
