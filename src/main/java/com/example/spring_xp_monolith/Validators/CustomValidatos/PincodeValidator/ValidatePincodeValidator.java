package com.example.spring_xp_monolith.Validators.CustomValidatos.PincodeValidator;

import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidatePincodeValidator implements ConstraintValidator<ValidatePincode, String>{

    private static final String URL = "https://api.postalpincode.in/pincode/";

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context){
        try {
                if(value == null ||  value.trim().isEmpty()){
                context.buildConstraintViolationWithTemplate("Value cannot be empty").addConstraintViolation();
                return false;
            }

            //check for 6 repeating digits (000000, 111111, etc.)
            if(value.matches(("(\\d)\\1{5}"))){
                context.buildConstraintViolationWithTemplate("Value cannot have 6 consecutive digits").addConstraintViolation();
                return false;
            }

            String response = restTemplate.getForObject(URL,String.class);

            ObjectMapper mapper = new ObjectMapper();

            JsonNode json = mapper.readTree(response);

            String status = json.get(0).get("Status").asText();

            return "Success".equals(status);

        } catch (Exception e) {
            return false;
        }
    }

}
