package com.example.spring_xp_monolith.Services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.spring_xp_monolith.Controllers.Errors.ResourceNotFoundException;
import com.example.spring_xp_monolith.Enums.ProcurementEnum.Types;
import com.example.spring_xp_monolith.dao.ProcurementUnitsRepo;
import com.example.spring_xp_monolith.dto.procurementUnits.AddProcurementUnitRequestDto;
import com.example.spring_xp_monolith.dto.procurementUnits.ProcurementUnitResponseDto;
import com.example.spring_xp_monolith.dto.procurementUnits.UpdateProcurementUnitRequestDto;
import com.example.spring_xp_monolith.models.ProcurementUnits;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProcurementUnitService {

    private final ProcurementUnitsRepo procurementUnitsRepo;

    @Transactional
    public ProcurementUnitResponseDto createProcurementUnit(AddProcurementUnitRequestDto request) {
        ProcurementUnits unit = new ProcurementUnits();
        applyRequest(unit, request);
        return ProcurementUnitResponseDto.from(procurementUnitsRepo.save(unit));
    }

    @Transactional(readOnly = true)
    public List<ProcurementUnitResponseDto> getProcurementUnits(Types type) {
        List<ProcurementUnits> units = type == null
                ? procurementUnitsRepo.findAllByIsDeleteFalse()
                : procurementUnitsRepo.findAllByTypeAndIsDeleteFalse(type);
        return units.stream()
                .map(ProcurementUnitResponseDto::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public ProcurementUnitResponseDto getProcurementUnitById(Long id) {
        return ProcurementUnitResponseDto.from(findUnit(id));
    }

    @Transactional
    public ProcurementUnitResponseDto updateProcurementUnit(UpdateProcurementUnitRequestDto request) {
        ProcurementUnits unit = findUnit(request.getId());
        applyRequest(unit, request);
        return ProcurementUnitResponseDto.from(procurementUnitsRepo.save(unit));
    }

    @Transactional
    public ProcurementUnitResponseDto deleteProcurementUnit(Long id) {
        ProcurementUnits unit = findUnit(id);
        unit.setIsDelete(true);
        return ProcurementUnitResponseDto.from(procurementUnitsRepo.save(unit));
    }

    private void applyRequest(ProcurementUnits unit, AddProcurementUnitRequestDto request) {
        unit.setName(request.getName().trim());
        unit.setType(request.getType());
        unit.setContactPerson(request.getContactPerson().trim());
        unit.setMobile(request.getMobile());
        unit.setEmail(request.getEmail().trim());
        unit.setAddress(request.getAddress().trim());
        unit.setCity(request.getCity().trim());
        unit.setPincode(request.getPincode());
        unit.setState(request.getState().trim());
    }

    private ProcurementUnits findUnit(Long id) {
        return procurementUnitsRepo.findByIdAndIsDeleteFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Procurement unit not found"));
    }
}
