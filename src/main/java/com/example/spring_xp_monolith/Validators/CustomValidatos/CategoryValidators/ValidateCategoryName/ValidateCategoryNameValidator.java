package com.example.spring_xp_monolith.Validators.CustomValidatos.CategoryValidators.ValidateCategoryName;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.spring_xp_monolith.dao.CategoriesRepo;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class ValidateCategoryNameValidator implements ConstraintValidator<ValidateCategoryName,String>{
    @Autowired
    private CategoriesRepo categoryRepo;
    
    @Override
    public boolean isValid(String name, ConstraintValidatorContext context){
        context.disableDefaultConstraintViolation();
        
        if(categoryRepo.existsByNameIgnoreCaseAndIsDeleteFalse(name)){
            context.buildConstraintViolationWithTemplate("category name already exists");
        }
        return true;
    }
}
