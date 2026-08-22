package com.example.spring_xp_monolith.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring_xp_monolith.models.Categories;

public interface CategoriesRepo extends JpaRepository <Categories,Long> {
    boolean existsByNameIgnoreCaseAndIsDeleteFalse(String name);

    Categories findByIdAndIsDeleteFalse(Long id);
}
