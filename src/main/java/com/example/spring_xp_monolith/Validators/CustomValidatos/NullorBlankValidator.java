package com.example.spring_xp_monolith.Validators.CustomValidatos;

import org.springframework.stereotype.Component;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class NullorBlankValidator implements ConstraintValidator<NullorNotBlank,String>{
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context){
        if(value == null){
            return true;
        }

        context.disableDefaultConstraintViolation();
        if(value.trim().isEmpty()){
            context.buildConstraintViolationWithTemplate("Value cannot be empty");
            return false;
        }

        return true;
    }
}
