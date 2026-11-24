package com.example.spring_xp_monolith.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring_xp_monolith.models.FoodTypes;

public interface FoodTypesRepo extends JpaRepository<FoodTypes, Long> {
    Optional<FoodTypes> findByIdAndIsDeleteFalse(Long id);

    List<FoodTypes> findAllByIsDeleteFalse();

    boolean existsByNameIgnoreCaseAndIsDeleteFalse(String name);
}
