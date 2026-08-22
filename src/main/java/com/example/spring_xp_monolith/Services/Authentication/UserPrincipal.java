package com.example.spring_xp_monolith.Services.Authentication;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.annotation.Nullable;

public class UserPrincipal implements UserDetails {
    private User user;

    public UserPrincipal(User user){
        this.user = user;
    }

    @Override
    public String getUsername(){
        return user.getUsername();
    }

    @Override
    public @Nullable String getPassword(){
        return user.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }
}
