package com.example.spring_xp_monolith.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring_xp_monolith.models.Categories;

public interface CategoriesRepo extends JpaRepository <Categories,Long> {
    boolean existsByNameIgnoreCaseAndIsDeleteFalse(String name);

    Optional<Categories> findByIdAndIsDeleteFalse(Long id);
}
