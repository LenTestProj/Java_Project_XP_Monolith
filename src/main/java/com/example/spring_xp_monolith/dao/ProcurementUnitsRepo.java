package com.example.spring_xp_monolith.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.spring_xp_monolith.models.ProcurementUnits;

@Repository
public interface ProcurementUnitsRepo extends JpaRepository<ProcurementUnits,Long> {
    
}
