package com.example.spring_xp_monolith.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OutletRepository outletRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(
            Authentication authentication)
            throws AuthenticationException {

        CustomAuthenticationToken token =
                (CustomAuthenticationToken) authentication;

        String username = token.getName();

        String password = token.getCredentials().toString();

        String role = token.getRole();

        UserDetails userDetails;

        switch (role) {

            case "A":

                userDetails = adminRepository
                        .findByEmailAndIsDeleteFalse(username)
                        .map(CustomUserPrincipal::new)
                        .orElseThrow(() ->
                                new BadCredentialsException("Admin not found"));

                break;

            case "U":

                userDetails = userRepository
                        .findByEmailAndIsDeleteFalse(username)
                        .map(CustomUserPrincipal::new)
                        .orElseThrow(() ->
                                new BadCredentialsException("User not found"));

                break;

            case "O":

                userDetails = outletRepository
                        .findByEmailAndIsDeleteFalse(username)
                        .map(CustomUserPrincipal::new)
                        .orElseThrow(() ->
                                new BadCredentialsException("Outlet user not found"));

                break;

            default:
                throw new BadCredentialsException("Invalid role");
        }

        if (!passwordEncoder.matches(
                password,
                userDetails.getPassword())) {

            throw new BadCredentialsException(
                    "Invalid password");
        }

        return new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {

        return CustomAuthenticationToken.class
                .isAssignableFrom(authentication);
    }
}
