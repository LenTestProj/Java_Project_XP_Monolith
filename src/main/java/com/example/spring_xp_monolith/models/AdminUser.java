package com.example.spring_xp_monolith.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.example.spring_xp_monolith.dto.adminUsers.CreateAdminUserRequestDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
// @Table(name="admn_user")
public class AdminUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String mobile;

    private String email;

    @Column(nullable = false)
    private String password;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    private Boolean isDelete = false;

    // Required by Hibernate
    public AdminUser() {

    }
    
    public AdminUser(CreateAdminUserRequestDto request) {
        this.name = request.getName();
        this.mobile = request.getMobile();
        this.email = request.getEmail();
        this.password = request.getPassword();
    }
}
