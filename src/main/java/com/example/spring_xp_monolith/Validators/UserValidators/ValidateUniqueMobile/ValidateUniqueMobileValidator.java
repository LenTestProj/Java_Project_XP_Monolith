package com.example.spring_xp_monolith.Validators.UserValidators.ValidateUniqueMobile;

import com.example.spring_xp_monolith.dao.UserRepo;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidateUniqueMobileValidator implements ConstraintValidator<ValidateUniqueMobile, String> {
    private final UserRepo userRepo;
    
    public ValidateUniqueMobileValidator(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public boolean isValid(String mobile, ConstraintValidatorContext context) {
        if(mobile == null || mobile.isEmpty()){
            return true; // Consider empty or null mobile as valid, adjust based on your requirements
        }
        
        return !userRepo.findByMobileAndIsDeleteFalse(mobile).isPresent();
    }
    
}
