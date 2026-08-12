package com.example.spring_xp_monolith.Validators.CustomValidatos.NameValidators;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
@Documented
@Constraint(validatedBy = ValidateNameValidator.class)
@Target({
    ElementType.FIELD,
    ElementType.PARAMETER
})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidateName {
    String message() default "Name is invalid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
