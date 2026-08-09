package com.example.spring_xp_monolith.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class OutletUsers {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="outlet_id")
    @Column(nullable = false)
    private Outlets outlet;

    @Column(nullable = false)
    private Integer serialNumber;

    @Column(nullable = false)
    private String idNumber;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(nullable = false)
    private String mobile;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Status status = Status.INACTIVE;

    private Boolean isDelete = false;

    //ENUM
    public enum Role {
        CASHIER,
        MANAGER,
        CAFE_MANAGER
    }

    public enum Status {
        ACTIVE,
        INACTIVE
    }
}
