package com.example.spring_xp_monolith.config;

import java.beans.Customizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import com.example.spring_xp_monolith.Services.Authentication.AdminUserDetials;

@Configuration
public class SecurityConfig {

    @Autowired
    private AdminUserDetials adminUserDetials;

    @Bean
    public AuthenticationProvider authprovider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(adminUserDetials);
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        return provider;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http){
        http.csrf(customizer -> customizer.disable())
        .authorizeHttpRequests(request->request
        .requestMatchers("/register","/login")
        .permitAll()    ///request matching "register" should be permitted
        .anyRequest().authenticated()) //authenticate http request
        .httpBasic(Customizer)
        .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    AuthenticationProvider authenticationProvider(
            CustomAuthenticationProvider provider){

        return provider;
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
        return config.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}