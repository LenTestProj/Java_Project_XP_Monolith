package com.example.spring_xp_monolith.Validators.CustomValidatos.NameValidators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidateNameValidator implements ConstraintValidator<ValidateName, String>{
    
    @Override
    public boolean isValid(String name, ConstraintValidatorContext context){
        if(name == null || name.trim().isEmpty()){
            return true;
        }

        return name.matches("^[a-zA-Z]+$");
    }

}
