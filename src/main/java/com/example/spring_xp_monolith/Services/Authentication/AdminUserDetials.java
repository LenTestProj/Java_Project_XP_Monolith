package com.example.spring_xp_monolith.Services.Authentication;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.spring_xp_monolith.dao.AdminUsersRepo;
import com.example.spring_xp_monolith.models.AdminUser;

public class AdminUserDetials implements UserDetailsService  {
    private AdminUsersRepo adminUserRepo;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        AdminUser adminUser = adminUserRepo.findByEmail(username);
        if(adminUser == null){
            throw new UsernameNotFoundException("user 404");
        }
        return new AdminUserPrincipal(adminUser);
    }
}
