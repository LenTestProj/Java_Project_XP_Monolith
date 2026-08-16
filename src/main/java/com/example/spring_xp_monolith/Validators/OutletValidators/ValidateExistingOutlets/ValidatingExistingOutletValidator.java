package com.example.spring_xp_monolith.Validators.OutletValidators.ValidateExistingOutlets;

import org.springframework.stereotype.Component;

import com.example.spring_xp_monolith.dao.OutletsRepo;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class ValidatingExistingOutletValidator implements ConstraintValidator<ValidateExistingOutlets, Long>{
    
    private final OutletsRepo outletRepository;

    ValidatingExistingOutletValidator(OutletsRepo outletRepository){
        this.outletRepository = outletRepository;
    }

    @Override
    public boolean isValid(Long outletId, ConstraintValidatorContext context){
        
        context.disableDefaultConstraintViolation();
        if(outletRepository.findByIdAndIsDeleteFalse(outletId).isEmpty()){
            //send error messages
            context.buildConstraintViolationWithTemplate("Outlet with the id: "+outletId+ "does not exist").addConstraintViolation();
            return false;
        }

        return true;
    }
}
