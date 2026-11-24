package com.example.spring_xp_monolith.Services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.spring_xp_monolith.Controllers.Errors.ResourceNotFoundException;
import com.example.spring_xp_monolith.dao.FoodTypesRepo;
import com.example.spring_xp_monolith.dto.foodTypes.AddFoodTypeRequestDto;
import com.example.spring_xp_monolith.dto.foodTypes.FoodTypeResponseDto;
import com.example.spring_xp_monolith.dto.foodTypes.UpdateFoodTypeRequestDto;
import com.example.spring_xp_monolith.models.FoodTypes;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FoodTypeService {

    private final FoodTypesRepo foodTypesRepo;

    @Transactional
    public FoodTypeResponseDto createFoodType(AddFoodTypeRequestDto request) {
        String name = request.getName().trim();
        if (foodTypesRepo.existsByNameIgnoreCaseAndIsDeleteFalse(name)) {
            throw new IllegalArgumentException("A food type with this name already exists");
        }

        FoodTypes foodType = new FoodTypes();
        foodType.setName(name);
        return FoodTypeResponseDto.from(foodTypesRepo.save(foodType));
    }

    @Transactional(readOnly = true)
    public List<FoodTypeResponseDto> getFoodTypes() {
        return foodTypesRepo.findAllByIsDeleteFalse()
                .stream()
                .map(FoodTypeResponseDto::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public FoodTypeResponseDto getFoodTypeById(Long id) {
        return FoodTypeResponseDto.from(findFoodType(id));
    }

    @Transactional
    public FoodTypeResponseDto updateFoodType(UpdateFoodTypeRequestDto request) {
        FoodTypes foodType = findFoodType(request.getId());
        String name = request.getName().trim();

        if (!foodType.getName().equalsIgnoreCase(name)
                && foodTypesRepo.existsByNameIgnoreCaseAndIsDeleteFalse(name)) {
            throw new IllegalArgumentException("A food type with this name already exists");
        }

        foodType.setName(name);
        return FoodTypeResponseDto.from(foodTypesRepo.save(foodType));
    }

    @Transactional
    public FoodTypeResponseDto deleteFoodType(Long id) {
        FoodTypes foodType = findFoodType(id);
        foodType.setIsDelete(true);
        return FoodTypeResponseDto.from(foodTypesRepo.save(foodType));
    }

    private FoodTypes findFoodType(Long id) {
        return foodTypesRepo.findByIdAndIsDeleteFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Food type not found"));
    }
}
