package com.example.spring_xp_monolith.Services.Authentication;
import java.nio.file.attribute.UserPrincipal;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.spring_xp_monolith.models.AdminUser;

import jakarta.annotation.Nullable;

public class AdminUserPrincipal implements UserDetails {
    private AdminUser adminUser;

    public AdminUserPrincipal(AdminUser adUser){
        adminUser = adUser;
    }

    @Override
    public String getUsername(){
        return adminUser.getEmail();
    }

    @Override
    public @Nullable String getPassword(){
        return adminUser.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }
}
