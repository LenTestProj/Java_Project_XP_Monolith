package com.example.spring_xp_monolith.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.spring_xp_monolith.models.Outlets;

@Repository
public interface OutletsRepo extends JpaRepository<Outlets, Long>{
    
}
