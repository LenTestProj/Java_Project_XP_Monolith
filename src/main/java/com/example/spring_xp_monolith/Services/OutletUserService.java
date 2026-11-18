package com.example.spring_xp_monolith.Services;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.spring_xp_monolith.Controllers.Errors.ResourceNotFoundException;
import com.example.spring_xp_monolith.dao.OutletUserRepo;
import com.example.spring_xp_monolith.dao.OutletsRepo;
import com.example.spring_xp_monolith.dto.OutletUsers.AddOutletUserRequestDto;
import com.example.spring_xp_monolith.dto.OutletUsers.OutletUserResponseDto;
import com.example.spring_xp_monolith.dto.OutletUsers.UpdateOutletUserRequestDto;
import com.example.spring_xp_monolith.models.OutletUsers;
import com.example.spring_xp_monolith.models.Outlets;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OutletUserService {

    private final OutletUserRepo outletUserRepo;
    private final OutletsRepo outletsRepo;
    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public OutletUserResponseDto createOutletUser(AddOutletUserRequestDto request) {
        // email is the login key for outlet users
        if (outletUserRepo.existsByEmailAndIsDeleteFalse(request.getEmail())) {
            throw new IllegalArgumentException("An outlet user with this email already exists");
        }

        OutletUsers user = new OutletUsers();
        user.setOutlet(findOutlet(request.getOutletId()));
        user.setSerialNumber(request.getSerialNumber());
        user.setIdNumber(request.getIdNumber());
        user.setName(request.getName());
        user.setRole(request.getRole());
        user.setMobile(request.getMobile());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        if (request.getStatus() != null) {
            user.setStatus(request.getStatus());
        }

        return OutletUserResponseDto.from(outletUserRepo.save(user));
    }

    @Transactional(readOnly = true)
    public List<OutletUserResponseDto> getOutletUsers(Long outletId) {
        List<OutletUsers> users = outletId != null
                ? outletUserRepo.findAllByOutletIdAndIsDeleteFalse(outletId)
                : outletUserRepo.findAllByIsDeleteFalse();

        return users.stream().map(OutletUserResponseDto::from).toList();
    }

    @Transactional(readOnly = true)
    public OutletUserResponseDto getOutletUserById(Long id) {
        return OutletUserResponseDto.from(findOutletUser(id));
    }

    @Transactional
    public OutletUserResponseDto updateOutletUser(UpdateOutletUserRequestDto request) {
        OutletUsers user = findOutletUser(request.getId());

        if (!user.getEmail().equalsIgnoreCase(request.getEmail())
                && outletUserRepo.existsByEmailAndIsDeleteFalse(request.getEmail())) {
            throw new IllegalArgumentException("An outlet user with this email already exists");
        }

        user.setOutlet(findOutlet(request.getOutletId()));
        user.setSerialNumber(request.getSerialNumber());
        user.setIdNumber(request.getIdNumber());
        user.setName(request.getName());
        user.setRole(request.getRole());
        user.setMobile(request.getMobile());
        user.setEmail(request.getEmail());

        // only re-hash if password is being changed
        if (request.getPassword() != null && !request.getPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }

        if (request.getStatus() != null) {
            user.setStatus(request.getStatus());
        }

        return OutletUserResponseDto.from(outletUserRepo.save(user));
    }

    @Transactional
    public OutletUserResponseDto deleteOutletUser(Long id) {
        OutletUsers user = findOutletUser(id);
        user.setIsDelete(true);
        return OutletUserResponseDto.from(outletUserRepo.save(user));
    }

    private OutletUsers findOutletUser(Long id) {
        return outletUserRepo.findByIdAndIsDeleteFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Outlet user not found"));
    }

    private Outlets findOutlet(Long id) {
        return outletsRepo.findByIdAndIsDeleteFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Outlet not found"));
    }
}
