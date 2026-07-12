package com.example.spring_xp_monolith.Validators.CustomValidatos.OutletValidators.ValidateExcludedOutlets;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.spring_xp_monolith.dao.OutletsRepo;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class ValidateExcludedOutletsValidator implements ConstraintValidator<ValidateExcludedOutlets, List<Long>>{
    
    @Autowired
    private OutletsRepo outletRepository;

    @Override
    public boolean isValid(List<Long> outletIds,ConstraintValidatorContext context){
        if(outletIds.isEmpty()){
            return true;
        }

        Set<Long> uniqueIds = new HashSet<Long>();

        context.disableDefaultConstraintViolation();
        for(Integer i=0;i<outletIds.size();i++){

            Long id = outletIds.get(i);
            //cehck for duplicate Ids.
            if(!uniqueIds.add(id)){
                context.buildConstraintViolationWithTemplate("Duplciate Ids exists");
                return false;
            }

            if(!outletRepository.existsById(id)){
                //send error message
                context.buildConstraintViolationWithTemplate("The outlet Id "+id+"does not exist at position "+(i+1));
                return false;
            }
        }

        return true;
    }


}
