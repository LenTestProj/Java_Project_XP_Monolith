package com.example.spring_xp_monolith.Services.Authentication;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.spring_xp_monolith.dao.AdminUsersRepo;
import com.example.spring_xp_monolith.dao.OutletUserRepo;
import com.example.spring_xp_monolith.dao.UserRepo;

@Service
public class UserLookupService {

    private final AdminUsersRepo adminUsersRepo;
    private final UserRepo userRepo;
    private final OutletUserRepo outletUserRepo;

    public UserLookupService(
            AdminUsersRepo adminUsersRepo,
            UserRepo userRepo,
            OutletUserRepo outletUserRepo) {

        this.adminUsersRepo = adminUsersRepo;
        this.userRepo = userRepo;
        this.outletUserRepo = outletUserRepo;
    }

    public UserDetails loadUser(String username, String role) {

        switch (role) {

            case "A":
                return adminUsersRepo
                        .findByEmailAndIsDeleteFalse(username)
                        .map(AdminUserPrincipal::new)
                        .orElseThrow(() ->
                                new BadCredentialsException(
                                        "Admin not found"));

            case "U":
                return userRepo
                        .findByEmailAndIsDeleteFalse(username)
                        .map(UserPrincipal::new)
                        .orElseThrow(() ->
                                new BadCredentialsException(
                                        "User not found"));

            case "O":
                return outletUserRepo
                        .findByEmailAndIsDeleteFalse(username)
                        .map(OutletUserPrincipal::new)
                        .orElseThrow(() ->
                                new BadCredentialsException(
                                        "Outlet user not found"));

            default:
                throw new BadCredentialsException(
                        "Invalid role");
        }
    }
}
