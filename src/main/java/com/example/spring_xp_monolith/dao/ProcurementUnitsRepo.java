package com.example.spring_xp_monolith.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring_xp_monolith.models.ProcurementUnits;

public interface ProcurementUnitsRepo extends JpaRepository<ProcurementUnits,Long> {
    
}
