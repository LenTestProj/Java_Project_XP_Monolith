package com.example.spring_xp_monolith.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.spring_xp_monolith.models.Categories;

@Repository
public interface CategoriesRepo extends JpaRepository <Categories,Long> {
    boolean existsByNameIgnoreCaseAndIsDeleteFalse(String name);

    Categories findByIdAndIsDeleteFalse(Long id);
}
