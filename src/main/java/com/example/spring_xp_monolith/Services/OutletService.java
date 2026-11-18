package com.example.spring_xp_monolith.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.spring_xp_monolith.Controllers.Errors.ResourceNotFoundException;
import com.example.spring_xp_monolith.dao.MenuGroupsRepo;
import com.example.spring_xp_monolith.dao.OutletsRepo;
import com.example.spring_xp_monolith.dao.ProcurementUnitsRepo;
import com.example.spring_xp_monolith.dto.outlets.AddOutletRequestDto;
import com.example.spring_xp_monolith.dto.outlets.OutletResponseDto;
import com.example.spring_xp_monolith.dto.outlets.UpdateOutletRequestDto;
import com.example.spring_xp_monolith.models.MenuGroups;
import com.example.spring_xp_monolith.models.Outlets;
import com.example.spring_xp_monolith.models.ProcurementUnits;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OutletService {

    private final OutletsRepo outletsRepo;
    private final MenuGroupsRepo menuGroupsRepo;
    private final ProcurementUnitsRepo procurementUnitsRepo;

    @Transactional
    public OutletResponseDto createOutlet(AddOutletRequestDto request) {
        Outlets outlet = new Outlets();
        applyFields(outlet, request);
        return OutletResponseDto.from(outletsRepo.save(outlet));
    }

    @Transactional(readOnly = true)
    public List<OutletResponseDto> getOutlets() {
        return outletsRepo.findAllByIsDeleteFalse()
                .stream()
                .map(OutletResponseDto::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public OutletResponseDto getOutletById(Long id) {
        return OutletResponseDto.from(findOutlet(id));
    }

    @Transactional
    public OutletResponseDto updateOutlet(UpdateOutletRequestDto request) {
        Outlets outlet = findOutlet(request.getId());
        applyFields(outlet, request);
        return OutletResponseDto.from(outletsRepo.save(outlet));
    }

    @Transactional
    public OutletResponseDto deleteOutlet(Long id) {
        Outlets outlet = findOutlet(id);
        outlet.setIsDelete(true);
        return OutletResponseDto.from(outletsRepo.save(outlet));
    }

    private Outlets findOutlet(Long id) {
        return outletsRepo.findByIdAndIsDeleteFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Outlet not found"));
    }

    private ProcurementUnits findUnit(Long id, String label) {
        return procurementUnitsRepo.findByIdAndIsDeleteFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException(label + " not found"));
    }

    private void applyFields(Outlets outlet, AddOutletRequestDto request) {
        MenuGroups menuGroup = menuGroupsRepo.findByIdAndIsDeleteFalse(request.getMenuGroupId())
                .orElseThrow(() -> new ResourceNotFoundException("Menu group not found"));

        outlet.setName(request.getName());
        outlet.setPhone(request.getPhone());
        outlet.setEmail(request.getEmail());
        outlet.setAddress(request.getAddress());
        outlet.setPincode(request.getPincode());
        outlet.setCity(request.getCity());
        outlet.setState(request.getState());
        outlet.setCountry(request.getCountry());
        outlet.setStatus(request.getStatus());
        outlet.setPaymentType(request.getPaymentType());
        outlet.setOrderAcceptType(request.getOrderAcceptType());
        outlet.setCoordinates(request.getCoordinates());
        outlet.setLocation(request.getLocation());
        outlet.setPinelabCredentials(request.getPineLabCredentials());
        outlet.setGstin(request.getGstin());
        outlet.setFssai(request.getFssai());
        outlet.setType(request.getType());
        outlet.setOutlet_no(request.getOutletNo());
        outlet.setOutlet_sequence(request.getOutletSequence());
        outlet.setOutletImage(request.getOutletImage());
        outlet.setOpeningTime(request.getOpeningTime());
        outlet.setClosingTime(request.getClosingTime());
        outlet.setMinimumDeliveryRadius(request.getMinimumDeliveryRadius());
        outlet.setFranchiseName(request.getFranchiseName());
        outlet.setFranchisePhone(request.getFranchisePhone());
        outlet.setFranchiseEmail(request.getFranchiseEmail());

        if (request.getIsEventOutlet() != null) {
            outlet.setIsEventOutlet(request.getIsEventOutlet());
        }

        outlet.getPlatforms().clear();
        if (request.getPlatforms() != null) {
            outlet.getPlatforms().addAll(new ArrayList<>(request.getPlatforms()));
        }

        outlet.setMenuGroup(menuGroup);
        outlet.setBeverageUnit(findUnit(request.getBeverageUnitId(), "Beverage unit"));
        outlet.setCentralUnit(findUnit(request.getCentralUnitId(), "Central unit"));
        outlet.setFoodUnit(request.getFoodUnitId() != null
                ? findUnit(request.getFoodUnitId(), "Food unit")
                : null);
    }
}
