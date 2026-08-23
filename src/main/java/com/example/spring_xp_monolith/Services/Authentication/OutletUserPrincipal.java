package com.example.spring_xp_monolith.Services.Authentication;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.spring_xp_monolith.models.OutletUsers;

import jakarta.annotation.Nullable;

public class OutletUserPrincipal implements UserDetails{
    private OutletUsers outletUser;

    public OutletUserPrincipal(OutletUsers outletUser){
        this.outletUser = outletUser;
    }

    @Override
    public String getUsername(){
        return outletUser.getEmail();
    }

    @Override 
    public @Nullable String getPassword(){
        return outletUser.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return List.of(new SimpleGrantedAuthority("ROLE_OUTLET_USER"));
    }

}
