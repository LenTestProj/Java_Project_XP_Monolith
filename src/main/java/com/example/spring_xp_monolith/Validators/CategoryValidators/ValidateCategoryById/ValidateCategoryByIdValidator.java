package com.example.spring_xp_monolith.Validators.CategoryValidators.ValidateCategoryById;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.spring_xp_monolith.dao.CategoriesRepo;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidateCategoryByIdValidator implements ConstraintValidator<ValidateCategoryById, Long> {
    @Autowired
    private CategoriesRepo categoriesRepo;

    @Override
    public boolean isValid(Long id, ConstraintValidatorContext context){
        // null is handled by @NotNull
        if(id == null){
            return true;
        }

        if(categoriesRepo.findByIdAndIsDeleteFalse(id).isEmpty()){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Category not found").addConstraintViolation();
            return false;
        }

        return true;
    }
}
