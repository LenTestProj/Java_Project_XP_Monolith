package com.example.spring_xp_monolith.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    private SignupType signupType;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private LocalDate dob;

    private String socialId;

    public enum SignupType {
        REGULAR,
        GOOGLE,
        APPLE
    }

    public enum Gender {
        MALE,
        FEMALE,
        OTHERS
    }
}
