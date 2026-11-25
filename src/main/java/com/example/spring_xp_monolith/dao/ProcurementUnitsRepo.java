package com.example.spring_xp_monolith.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring_xp_monolith.Enums.ProcurementEnum.Types;
import com.example.spring_xp_monolith.models.ProcurementUnits;

public interface ProcurementUnitsRepo extends JpaRepository<ProcurementUnits,Long> {
    Optional<ProcurementUnits> findByIdAndIsDeleteFalse(Long id);

    List<ProcurementUnits> findAllByIsDeleteFalse();

    List<ProcurementUnits> findAllByTypeAndIsDeleteFalse(Types type);
}
