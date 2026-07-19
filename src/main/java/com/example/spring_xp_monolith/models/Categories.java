package com.example.spring_xp_monolith.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import io.micrometer.common.lang.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Data;

@Data
@Entity
public class Categories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name="excluded_outlets", 
        joinColumns = @JoinColumn(name="product_id"),
        inverseJoinColumns = @JoinColumn(name="outlet_id")
    )
    private List<Outlets> excludedOutlets=new ArrayList<Outlets>();

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime createdDate;

    @UpdateTimestamp
    private LocalDateTime updatedDate;

    //enum types
    public enum Status {
        ACTIVE,
        INACTIVE
    }

    public enum OrderType {
        PICKUP, DELIVERY, ALL
    }
}
