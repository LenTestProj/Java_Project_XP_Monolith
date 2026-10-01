package com.example.spring_xp_monolith.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.spring_xp_monolith.models.Users;


public interface UserRepo extends JpaRepository<Users,Long> {
    Optional<Users> findByIdAndIsDeleteFalse(Long userId);

    Optional<Users> findByEmailAndIsDeleteFalse(String userEmail);

    Optional<Users> findByMobileAndIsDeleteFalse(String userMobile);
}


