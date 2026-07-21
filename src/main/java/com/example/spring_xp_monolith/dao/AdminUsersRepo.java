package com.example.spring_xp_monolith.dao;

import org.springframework.stereotype.Repository;

import com.example.spring_xp_monolith.models.AdminUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;


@Repository
public interface AdminUsersRepo extends JpaRepository<AdminUser, Long> {

    // @Query("select * from admin_use")
    // List<AdminUsers> findActiveAdminUsers()
    
    AdminUser findByEmail(String email);
} 