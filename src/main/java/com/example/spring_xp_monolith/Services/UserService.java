package com.example.spring_xp_monolith.Services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.spring_xp_monolith.Controllers.Errors.ResourceNotFoundException;
import com.example.spring_xp_monolith.dao.OutletsRepo;
import com.example.spring_xp_monolith.dao.UserRepo;
import com.example.spring_xp_monolith.dto.Users.CreateAccountDto;
import com.example.spring_xp_monolith.models.Outlets;
import com.example.spring_xp_monolith.models.Users;

@Service 
public class UserService {
    private UserRepo userRepo;
    private OutletsRepo outletsRepo;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepo userRepo, BCryptPasswordEncoder bCryptPasswordEncoder, OutletsRepo outletsRepo){
        this.userRepo = userRepo;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.outletsRepo = outletsRepo;
    }

    public Users addUser(CreateAccountDto userData){
        Users user = new Users();
        user.setName(userData.getName()); 
        user.setMobile(userData.getMobile()); 
        user.setEmail(userData.getEmail()); 
        user.setPassword(bCryptPasswordEncoder.encode(userData.getPassword())); 
        user.setSignupType(userData.getSignupType()); 

        if(user.getDevices() != null){
            user.setDevices(userData.getDevices()); 
        }

        Users referringUser = null;        
        if(userData.getReferringUser() != null && !userData.getReferringUser().isBlank()){
            Long referringUserId = Long.parseLong(userData.getReferringUser());
            referringUser = userRepo.findByIdAndIsDeleteFalse(referringUserId).orElseThrow(() -> new ResourceNotFoundException("Referring User not found")); 
        }

        else if(userData.getReferringUserMobile() != null && !userData.getReferringUserMobile().isBlank()){
            referringUser = userRepo.findByMobileAndIsDeleteFalse(userData.getReferringUserMobile())
            .orElseThrow(() ->
                    new ResourceNotFoundException(
                            "Referring user not found"));
        }
        if(referringUser!=null){
            user.setReferringUser(referringUser);
        }

        //in case of outlet user
        if (userData.getOutletId() != null && !userData.getOutletId().isBlank()) {
            Long outletId;
            outletId = Long.parseLong(userData.getOutletId());
            Outlets outlet = outletsRepo.findById(outletId).orElseThrow(() -> new ResourceNotFoundException("Outlet not found"));
            user.setOutlet(outlet);
        }
        Users userResponse = userRepo.save(user); 
        return userResponse;
    }
        
}
