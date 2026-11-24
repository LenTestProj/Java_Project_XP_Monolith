package com.example.spring_xp_monolith.Services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.spring_xp_monolith.Controllers.Errors.ResourceNotFoundException;
import com.example.spring_xp_monolith.dao.MenuGroupsRepo;
import com.example.spring_xp_monolith.dto.menuGroups.AddMenuGroupRequestDto;
import com.example.spring_xp_monolith.dto.menuGroups.MenuGroupResponseDto;
import com.example.spring_xp_monolith.dto.menuGroups.UpdateMenuGroupRequestDto;
import com.example.spring_xp_monolith.models.MenuGroups;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MenuGroupService {

    private final MenuGroupsRepo menuGroupsRepo;

    @Transactional
    public MenuGroupResponseDto createMenuGroup(AddMenuGroupRequestDto request) {
        MenuGroups menuGroup = new MenuGroups();
        applyFields(menuGroup, request);
        return MenuGroupResponseDto.from(menuGroupsRepo.save(menuGroup));
    }

    @Transactional(readOnly = true)
    public List<MenuGroupResponseDto> getMenuGroups() {
        return menuGroupsRepo.findAllByIsDeleteFalse()
                .stream()
                .map(MenuGroupResponseDto::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public MenuGroupResponseDto getMenuGroupById(Long id) {
        return MenuGroupResponseDto.from(findMenuGroup(id));
    }

    @Transactional
    public MenuGroupResponseDto updateMenuGroup(UpdateMenuGroupRequestDto request) {
        MenuGroups menuGroup = findMenuGroup(request.getId());
        applyFields(menuGroup, request);
        return MenuGroupResponseDto.from(menuGroupsRepo.save(menuGroup));
    }

    @Transactional
    public MenuGroupResponseDto deleteMenuGroup(Long id) {
        MenuGroups menuGroup = findMenuGroup(id);
        menuGroup.setIsDelete(true);
        return MenuGroupResponseDto.from(menuGroupsRepo.save(menuGroup));
    }

    private MenuGroups findMenuGroup(Long id) {
        return menuGroupsRepo.findByIdAndIsDeleteFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Menu group not found"));
    }

    private void applyFields(MenuGroups menuGroup, AddMenuGroupRequestDto request) {
        menuGroup.setGroupName(request.getGroupName().trim());
        menuGroup.setDescription(request.getDescription().trim());

        if (request.getIsDefaultGroup() != null) {
            menuGroup.setIsDefaultGroup(request.getIsDefaultGroup());
        }
    }
}
