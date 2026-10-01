package com.example.spring_xp_monolith.Services;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtService {
    private String secretKey;

    JwtService(){
        this.secretKey = generateSecretKey();
    }

    public String generateSecretKey(){
        try{
            KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
            SecretKey secretKey = keyGen.generateKey();
            System.out.println("Secret Key : " + secretKey.toString());
            return Base64.getEncoder().encodeToString(secretKey.getEncoded());
        }
        catch(NoSuchAlgorithmException e){
            throw new RuntimeException("Error generating secret key", e);
        }
    }
    
    public String generateToken(String username, String role, Long expirationTimeMillis){
        Map<String, Object> claims = new HashMap<>();
        claims.put("role",role);

        JwtBuilder builder = Jwts.builder()
        .setClaims(claims)
        .setSubject(username)
        .setIssuedAt(new Date(System.currentTimeMillis()))
        // .setExpiration(expiry)
        .signWith(getKey(), SignatureAlgorithm.HS256);
        
        if(expirationTimeMillis != null){
            Date expiration = new Date(System.currentTimeMillis() + expirationTimeMillis);
            builder.setExpiration(expiration);
        }
    
        return builder.compact();
    }

    private Key getKey(){
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    
   private Claims extractAllClaims(String token){
        return Jwts.parserBuilder()
        .setSigningKey(getKey())
        .build().parseClaimsJws(token)
        .getBody();   
   }


    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String extractUserName(String token){
        return extractClaim(token, Claims::getSubject);
    }

    public String extractRole(String token){
        return extractClaim(token, claims -> claims.get("role",String.class));
    }

    public Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }

    public boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    public boolean validateToken(String token, UserDetails userDetails){
       final String username = extractUserName(token);
       return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }


}
