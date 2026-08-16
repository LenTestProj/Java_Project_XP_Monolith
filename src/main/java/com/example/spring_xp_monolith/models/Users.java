package com.example.spring_xp_monolith.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.spring_xp_monolith.Enums.Users.Gender;
import com.example.spring_xp_monolith.Enums.Users.SignupType;
import com.example.spring_xp_monolith.models.embedded.Users.Device;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;

@Data
@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String mobile;

    @Column(unique = true)
    private String email;

    private String password;

    private String profileImage;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SignupType signupType;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private LocalDate dob;

    private String socialId;

    // DEVICES
    @ElementCollection
    @CollectionTable(
        name="user_devices",
        joinColumns = @JoinColumn(name="user_id")
    )
    private List<Device> devices = new ArrayList<>();

    //Referring User
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "referring_user_id")
    private Users referringUser;

    //Outlets
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="outlet_id")
    private Outlets outlet;

    // ------ Other Fields ------
    private Boolean isOutletAccount = false;

    private Integer journeyMileStone;

    private Integer orderCount = 0;

    private Boolean isDelete = false;

    // ------ TIme stamps ----

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate(){
        LocalDateTime now = LocalDateTime.now();
        createdAt = now;
        updatedAt = now;
    }

    @PreUpdate
    protected void onUpdate(){
        updatedAt = LocalDateTime.now();
    }
}
