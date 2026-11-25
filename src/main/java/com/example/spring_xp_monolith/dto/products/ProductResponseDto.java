package com.example.spring_xp_monolith.dto.products;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.spring_xp_monolith.Enums.Products.ClassType;
import com.example.spring_xp_monolith.Enums.Products.FoodType;
import com.example.spring_xp_monolith.models.Outlets;
import com.example.spring_xp_monolith.models.Products;

import lombok.Data;

@Data
public class ProductResponseDto {
    private Long id;
    private Long categoryId;
    private String categoryName;
    private String name;
    private String description;
    private String displayImage;
    private String coverPicture;
    private Integer productCost;
    private Integer mrp;
    private Integer offerPrice;
    private String hsn;
    private String taxPercent;
    private String makingTime;
    private Boolean hasAddon;
    private FoodType foodType;
    private ClassType classType;
    private Integer calories;
    private Integer weight;
    private String keywords;
    private Integer sequenceNumber;
    private Boolean isEventProduct;
    private Boolean isComboProduct;
    private Boolean isFreebieProduct;
    private List<String> productsNature;
    private Boolean isStatusAutoUpdated;
    private List<Long> excludedOutletIds;
    private Boolean isNewProduct;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static ProductResponseDto from(Products p) {
        ProductResponseDto dto = new ProductResponseDto();
        dto.setId(p.getId());
        if (p.getCategory() != null) {
            dto.setCategoryId(p.getCategory().getId());
            dto.setCategoryName(p.getCategory().getName());
        }
        dto.setName(p.getName());
        dto.setDescription(p.getDescription());
        dto.setDisplayImage(p.getDisplayImage());
        dto.setCoverPicture(p.getCoverPicture());
        dto.setProductCost(p.getProductCost());
        dto.setMrp(p.getMrp());
        dto.setOfferPrice(p.getOfferPrice());
        dto.setHsn(p.getHsn());
        dto.setTaxPercent(p.getTaxPercent());
        dto.setMakingTime(p.getMakingTime());
        dto.setHasAddon(p.getHasAddon());
        dto.setFoodType(p.getFoodType());
        dto.setClassType(p.getClassType());
        dto.setCalories(p.getCalories());
        dto.setWeight(p.getWeight());
        dto.setKeywords(p.getKeywords());
        dto.setSequenceNumber(p.getSequenceNumber());
        dto.setIsEventProduct(p.getIsEventProduct());
        dto.setIsComboProduct(p.getIsComboProduct());
        dto.setIsFreebieProduct(p.getIsFreebieProduct());
        dto.setProductsNature(p.getProductsNature() == null ? null : new ArrayList<>(p.getProductsNature()));
        dto.setIsStatusAutoUpdated(p.getIsStatusAutoUpdated());
        dto.setExcludedOutletIds(p.getExcludedOutlets() == null ? List.of()
                : p.getExcludedOutlets().stream().map(Outlets::getId).toList());
        dto.setIsNewProduct(p.getIsNewProduct());
        dto.setCreatedAt(p.getCreatedAt());
        dto.setUpdatedAt(p.getUpdatedAt());
        return dto;
    }
}
