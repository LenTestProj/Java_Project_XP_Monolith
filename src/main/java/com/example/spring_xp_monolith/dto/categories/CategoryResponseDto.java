package com.example.spring_xp_monolith.dto.categories;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.spring_xp_monolith.Enums.Products.FoodType;
import com.example.spring_xp_monolith.models.Categories;
import com.example.spring_xp_monolith.models.Outlets;

import lombok.Data;

@Data
public class CategoryResponseDto {
    private Long id;
    private String name;
    private String description;
    private String displayImage;
    private String bannerImage;
    private Integer sequenceNumber;
    private Categories.Status status;
    private Boolean isComboCategory;
    private Categories.OrderType orderType;
    private Boolean isStatusAutoUpdated;
    private List<FoodType> foodType;
    private List<Long> excludedOutletIds;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static CategoryResponseDto from(Categories c) {
        CategoryResponseDto dto = new CategoryResponseDto();
        dto.setId(c.getId());
        dto.setName(c.getName());
        dto.setDescription(c.getDescription());
        dto.setDisplayImage(c.getDisplayImage());
        dto.setBannerImage(c.getBannerImage());
        dto.setSequenceNumber(c.getSequenceNumber());
        dto.setStatus(c.getStatus());
        dto.setIsComboCategory(c.getIsComboCategory());
        dto.setOrderType(c.getOrderType());
        dto.setIsStatusAutoUpdated(c.getIsStatusAutoUpdated());
        dto.setFoodType(c.getFoodType() == null ? List.of() : new ArrayList<>(c.getFoodType()));
        dto.setExcludedOutletIds(c.getExcludedOutlets() == null ? List.of()
                : c.getExcludedOutlets().stream().map(Outlets::getId).toList());
        dto.setCreatedAt(c.getCreatedDate());
        dto.setUpdatedAt(c.getUpdatedDate());
        return dto;
    }
}
