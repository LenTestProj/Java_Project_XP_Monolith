package com.example.spring_xp_monolith.Validators.MenuGroupValidators;

import com.example.spring_xp_monolith.dao.MenuGroupsRepo;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidateExistingMenuGroupValidator implements ConstraintValidator<ValidateExistingMenuGroup,Long>{
    
    private final MenuGroupsRepo menuGroupRepository;

    ValidateExistingMenuGroupValidator(MenuGroupsRepo menuGroupRepository){
        this.menuGroupRepository = menuGroupRepository;
    }

    @Override
    public boolean isValid(Long menuGroupId, ConstraintValidatorContext context){
        // null is handled by @NotNull
        if(menuGroupId == null){
            return true;
        }

        context.disableDefaultConstraintViolation();
        if(menuGroupRepository.findByIdAndIsDeleteFalse(menuGroupId).isEmpty()){
            //send error messages
            context.buildConstraintViolationWithTemplate("Menu Group with the id: "+menuGroupId+ " does not exist").addConstraintViolation();
            return false;
        }

        return true;
    }
}
