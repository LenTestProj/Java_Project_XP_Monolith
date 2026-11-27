package com.example.spring_xp_monolith.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring_xp_monolith.models.Categories;

public interface CategoriesRepo extends JpaRepository <Categories,Long> {
    boolean existsByNameIgnoreCaseAndIsDeleteFalse(String name);

    boolean existsByNameIgnoreCaseAndIsDeleteFalseAndIdNot(String name, Long id);

    Optional<Categories> findByIdAndIsDeleteFalse(Long id);

    List<Categories> findAllByIsDeleteFalseOrderBySequenceNumberAsc();
}
