package com.example.spring_xp_monolith.dao;

import com.example.spring_xp_monolith.models.AdminUser;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminUsersRepo extends JpaRepository<AdminUser, Long> {

    // @Query("select * from admin_use")
    // List<AdminUsers> findActiveAdminUsers()
    
    AdminUser findByEmail(String email);

    Optional<AdminUser> findByEmailAndIsDeleteFalse(String email);
} 