package com.example.spring_xp_monolith.Services;

import java.util.Base64;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class JwtService2 {
    private String secretKey;

    public JwtService2(){
        this.secretKey = generateSecretKey();
    }

    public String generateSecretKey(){
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
            SecretKey secretKey = keyGen.generateKey();
            return Base64.getEncoder().encodeToString(secretKey.getEncoded());
        } catch (Exception e) {
           throw new RuntimeException("Error generating secret key");
        }
    }
    
    

}
