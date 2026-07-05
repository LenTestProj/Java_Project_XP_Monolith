package com.example.spring_xp_monolith.models;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;

public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer productCost = 0;

    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String displayName;

    private String coverPicture;

    private Integer mrp;

    private Integer offerPrice;

    private String hsn;

    private String taxPercent;

    //stores minutes
    private String makingTime;

    private Boolean hasAddon = false;

    @Enumerated(EnumType.STRING)
    private FoodType foodType;

    @Enumerated(EnumType.STRING)
    private ClassType classType;

    private Integer calories;

    private Integer weight;

    private String keywords;

    private Integer sequenceNumber;

    private Boolean isEventProduct = false;

    private Boolean isComboProduct = false;

    private Boolean isFreebieProduct = false;

    @ElementCollection
    private List<String> productsNature;

    private Boolean isStatusAutoUpdated = false;


    private Boolean isNewProduct = false;


    private Boolean isDelete = false;

    // timestamps:true equivalent

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @PrePersist
    public void onCreate(){
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }
    
    //atore enums
    public enum Status {
        ACTIVE, 
        INACTIVE
    }

    public enum FoodType {
        VEG,
        NON_VEG,
        VEGAN,
        VEG_VEGAN
    }

    public enum ClassType {
        FOOD,
        BEVERAGE,
        COMBOS,
        DESSERTS,
        EXTRAS
    }

    public enum WeightUnit {
        KG,
        GMS,
        LITRES,
        ML
    }

    public enum OrderType {
        PICKUP,
        DELIVERY,
        ALL
    }
}
