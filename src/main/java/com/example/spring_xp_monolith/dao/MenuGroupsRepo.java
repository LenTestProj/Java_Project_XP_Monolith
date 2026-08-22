package com.example.spring_xp_monolith.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring_xp_monolith.models.MenuGroups;

public interface MenuGroupsRepo extends JpaRepository<MenuGroups,Long> {
    Optional<MenuGroups> findByIdAndIsDeleteFalse(Long id);
}
