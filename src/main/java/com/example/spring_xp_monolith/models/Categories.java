package com.example.spring_xp_monolith.models;

import io.micrometer.common.lang.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class Categories {
    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;

    @Column(nullable = false)
    private String displayImage;

    @Column(nullable = false)
    private String bannerImage;

    @Column(nullable = false)
    private Integer sequenceNumber;

    @Enumerated(EnumType.STRING)
    private Status status = Status.INACTIVE;

    private Boolean isEventCategory = false;

    private Boolean isComboCategory = false;

    @Enumerated(EnumType.STRING)
    private OrderType orderType;

    private Boolean isNewCategory = false;

    private Boolean isDelete = false;


    //enum types
    public enum Status {
        ACTIVE,
        INACTIVE
    }

    public enum OrderType {
        PICKUP, DELIVERY, ALL
    }
}
