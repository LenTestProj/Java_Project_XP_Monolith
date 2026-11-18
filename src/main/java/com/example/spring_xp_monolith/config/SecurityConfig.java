package com.example.spring_xp_monolith.config;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;

import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import com.example.spring_xp_monolith.Services.Authentication.AdminUserDetials;

@Configuration
public class SecurityConfig {

    private AdminUserDetials adminUserDetials;

    SecurityConfig(AdminUserDetials adminUserDetials){
        this.adminUserDetials = adminUserDetials;
    }

    // @Bean
    // public AuthenticationProvider authprovider(){
    //     DaoAuthenticationProvider provider = new DaoAuthenticationProvider(adminUserDetials);
    //     provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
    //     return provider;
    // }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,CustomAuthenticationProvider provider,JsonAuthErrorHandler authErrorHandler,JwtFilter jwtFilter) throws Exception{
        return http
            .csrf(customizer -> customizer.disable())

            .exceptionHandling(e -> e
                .authenticationEntryPoint(authErrorHandler)
                .accessDeniedHandler(authErrorHandler)
            )

            .authenticationProvider(provider)
            .authorizeHttpRequests(request -> request             
                .requestMatchers("/register","/user","/admin-users")
                .permitAll()
                .requestMatchers(HttpMethod.POST,"/auth/user/login","/auth/admin/login")
                .permitAll()
                .anyRequest()
                .authenticated()
            )

            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

            // run JWT authentication inside the security chain, before the username/password filter
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)

            .build();

    }

    @Bean
    AuthenticationProvider authenticationProvider(
            CustomAuthenticationProvider provider){

        return provider;
    }

    // @Bean
    // AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
    //     return config.getAuthenticationManager();
    // }

    @Bean
    AuthenticationManager authenticationManager(CustomAuthenticationProvider provider) throws Exception{
        return new ProviderManager(provider);
    }

    // JwtFilter is a @Component, so Spring Boot would also register it as a plain
    // servlet filter. Disable that so it only runs inside the security chain.
    @Bean
    FilterRegistrationBean<JwtFilter> jwtFilterRegistration(JwtFilter jwtFilter) {
        FilterRegistrationBean<JwtFilter> registration = new FilterRegistrationBean<>(jwtFilter);
        registration.setEnabled(false);
        return registration;
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    // @Bean
    // PasswordEncoder passwordEncoder(){
    //     return new BCryptPasswordEncoder();
    // }
}