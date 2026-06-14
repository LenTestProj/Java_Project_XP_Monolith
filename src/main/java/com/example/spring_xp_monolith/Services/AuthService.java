package com.example.spring_xp_monolith.Services;

import org.springframework.stereotype.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.spring_xp_monolith.dao.AdminUsersRepo;
import com.example.spring_xp_monolith.models.AdminUser;

@Service
public class AuthService {
    @Autowired
    private AdminUsersRepo repo;

    private BCryptPasswordEncoder encoder =  new BCryptPasswordEncoder(12);

    public AdminUser createAdminUser(AdminUser adminUser){
        adminUser.setPassword(encoder.encode(adminUser.getPassword()));
        return repo.save(adminUser);
    }

    public AdminUser updateAdminUser(Long id, AdminUser adminUser){
        AdminUser existingUser = repo.findById(id).orElseThrow(() -> new RuntimeException("Admin user not found"));

        existingUser.setName(adminUser.getName());
        existingUser.setEmail(adminUser.getEmail());
        existingUser.setMobile(null);

        //only encode if password is being changed
        if(adminUser.getPassword() != null && !adminUser.getPassword().isEmpty()){
            existingUser.setPassword(encoder.encode(adminUser.getPassword()));
        }
        
        return repo.save(existingUser);
    }

    public AdminUser deleteAdminUser(Long id){
        AdminUser existingAdminUser = repo.findById(id).orElseThrow(() -> new RuntimeException("Admin user not found"));

        existingAdminUser.setIsDelete(true);
        return repo.save(existingAdminUser);
    }

    public List<AdminUser> getAdminUsers(Long id){
        List<AdminUser> adminUsers;
        if(id != null){
            adminUsers = repo.findById(id).stream().toList();
        }
        else{
            adminUsers = repo.findAll();
        }
        return adminUsers;
    }
}
