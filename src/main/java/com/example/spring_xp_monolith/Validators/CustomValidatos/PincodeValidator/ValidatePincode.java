package com.example.spring_xp_monolith.Validators.CustomValidatos.PincodeValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Payload;


@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface ValidatePincode {
    String message() default "Value cannot be blank";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
