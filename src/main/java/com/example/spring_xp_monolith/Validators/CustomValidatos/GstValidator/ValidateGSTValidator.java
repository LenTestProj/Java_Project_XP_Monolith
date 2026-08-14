package com.example.spring_xp_monolith.Validators.CustomValidatos.GstValidator;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.spring_xp_monolith.Validators.CustomValidatos.NullorNotBlank;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.validation.ConstraintValidator;

@Component
public class ValidateGSTValidator implements ConstraintValidator<ValidateGST, String>{
    
    private static final String URL = "https://api.postalpincode.in/pincode/";

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public boolean isValid(String value, ConstraintValidator context ){
        try {
            if(value == null || value.trim().isEmpty()){
                return false;
            }

            //Check for 6 repeating digits (000000, 111111, etc.)
            if(value.matches("(\\d)\\1{5}")){
                return false;
            }

            String response =
                    restTemplate.getForObject(
                            URL + value,
                            String.class
                    );

            ObjectMapper mapper = new ObjectMapper();

            JsonNode json =
                    mapper.readTree(response);

            String status =
                    json.get(0)
                            .get("Status")
                            .asText();

            return "Success".equals(status);
        } catch (Exception e) {
            return false;
        }
    }

}
