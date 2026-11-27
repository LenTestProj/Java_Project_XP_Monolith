package com.example.spring_xp_monolith.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring_xp_monolith.models.Products;

public interface ProductsRepo extends JpaRepository<Products,Long> {

    Optional<Products> findByIdAndIsDeleteFalse(Long id);

    List<Products> findAllByIsDeleteFalseOrderBySequenceNumberAsc();

    List<Products> findAllByCategoryIdAndIsDeleteFalseOrderBySequenceNumberAsc(Long categoryId);
}
