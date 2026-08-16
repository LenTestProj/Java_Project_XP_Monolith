package com.example.spring_xp_monolith.Validators.CustomValidatos.GstValidator;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.spring_xp_monolith.Validators.CustomValidatos.NullorNotBlank;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class ValidateGSTValidator implements ConstraintValidator<ValidateGST, String>{
    
    private static final String URL = "https://api.postalpincode.in/pincode/";

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context){
        try {
             String regexValidateGST="^\\d{2}[0-9a-zA-Z]+$";

            if(value == null){
                return true;
            }
            if(value.trim().isEmpty()){
                context.buildConstraintViolationWithTemplate("GST is empty").addConstraintViolation();
                return false;
            }

            if(value.trim().length() != 15){
                context.buildConstraintViolationWithTemplate("GST number should be less than 15").addConstraintViolation();
                return false;
            }

            if(!value.matches(regexValidateGST)){
                context.buildConstraintViolationWithTemplate("Invalid GST Format. GSTIN first 2 digits should be numeric and length should not exceed 15 and should be alphanumeric").addConstraintViolation();
                return false;
            }

            return true;

        } catch (Exception e) {
            context.buildConstraintViolationWithTemplate("The error occured while validating Pin Code is:").addConstraintViolation();
            return false;
        }
    }

}
