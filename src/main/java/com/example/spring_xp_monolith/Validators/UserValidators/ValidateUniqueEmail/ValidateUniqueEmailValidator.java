package com.example.spring_xp_monolith.Validators.UserValidators.ValidateUniqueEmail;

import com.example.spring_xp_monolith.dao.UserRepo;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidateUniqueEmailValidator implements ConstraintValidator<ValidateUniqueEmail, String> {
    private final UserRepo userRepo;
    
    public ValidateUniqueEmailValidator(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return !userRepo.findByEmailAndIsDeleteFalse(email).isPresent();
    }
    
}
