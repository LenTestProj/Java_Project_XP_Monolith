package com.example.spring_xp_monolith.Validators.MenuGroupValidators;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Payload;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface ValidateExistingMenuGroup {   
    String message() default "Meu Group Id does not exist";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
