package com.example.spring_xp_monolith.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring_xp_monolith.models.OutletUsers;

public interface OutletUserRepo extends JpaRepository<OutletUsers,Long> {
    Optional<OutletUsers> findByIdAndIsDeleteFalse(Long outletUserId);

    Optional<OutletUsers> findByEmailAndIsDeleteFalse(String username);
}
