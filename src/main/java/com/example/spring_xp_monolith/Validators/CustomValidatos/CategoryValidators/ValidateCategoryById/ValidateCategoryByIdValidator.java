package com.example.spring_xp_monolith.Validators.CustomValidatos.CategoryValidators.ValidateCategoryById;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.spring_xp_monolith.dao.CategoriesRepo;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidateCategoryByIdValidator implements ConstraintValidator<ValidateCategoryById, Long> {
    @Autowired
    private CategoriesRepo categoriesRepo;

    @Override
    public boolean isValid(Long id, ConstraintValidatorContext context){
        if(categoriesRepo.findByIdAndIsDeleteFalse(id) == null){
            context.buildConstraintViolationWithTemplate("category Id does not exists");
            return false;
        }

        return true;
    }
}
