package com.example.spring_xp_monolith.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.spring_xp_monolith.Controllers.Errors.ResourceNotFoundException;
import com.example.spring_xp_monolith.dao.CategoriesRepo;
import com.example.spring_xp_monolith.dao.OutletsRepo;
import com.example.spring_xp_monolith.dto.categories.CategoryResponseDto;
import com.example.spring_xp_monolith.dto.categories.CreateCategoryRequestDto;
import com.example.spring_xp_monolith.dto.categories.UpdateCategoryRequestDto;
import com.example.spring_xp_monolith.models.Categories;
import com.example.spring_xp_monolith.models.Outlets;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private static final String IMAGE_FOLDER = "categories";

    private final CategoriesRepo categoriesRepo;
    private final OutletsRepo outletsRepo;
    private final ProductImageService imageService;

    @Transactional
    public CategoryResponseDto createCategory(
            CreateCategoryRequestDto request,
            MultipartFile displayImage,
            MultipartFile bannerImage) {

        if (categoriesRepo.existsByNameIgnoreCaseAndIsDeleteFalse(request.getName())) {
            throw new IllegalArgumentException("Category name already exists");
        }

        Categories category = new Categories();
        applyFields(category, request);

        category.setDisplayImage(imageService.uploadImage(displayImage, IMAGE_FOLDER, "display", 400, 400));
        category.setBannerImage(imageService.uploadImage(bannerImage, IMAGE_FOLDER, "banner", 1200, 400));

        return CategoryResponseDto.from(categoriesRepo.save(category));
    }

    @Transactional(readOnly = true)
    public List<CategoryResponseDto> getCategories() {
        return categoriesRepo.findAllByIsDeleteFalseOrderBySequenceNumberAsc()
                .stream()
                .map(CategoryResponseDto::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public CategoryResponseDto getCategoryById(Long id) {
        return CategoryResponseDto.from(findCategory(id));
    }

    @Transactional
    public CategoryResponseDto updateCategory(
            UpdateCategoryRequestDto request,
            MultipartFile displayImage,
            MultipartFile bannerImage) {

        Categories category = findCategory(request.getId());

        if (categoriesRepo.existsByNameIgnoreCaseAndIsDeleteFalseAndIdNot(request.getName(), request.getId())) {
            throw new IllegalArgumentException("Category name already exists");
        }

        applyFields(category, request);

        // Images are optional on update; keep the existing ones if not sent.
        if (displayImage != null && !displayImage.isEmpty()) {
            category.setDisplayImage(imageService.uploadImage(displayImage, IMAGE_FOLDER, "display", 400, 400));
        }
        if (bannerImage != null && !bannerImage.isEmpty()) {
            category.setBannerImage(imageService.uploadImage(bannerImage, IMAGE_FOLDER, "banner", 1200, 400));
        }

        return CategoryResponseDto.from(categoriesRepo.save(category));
    }

    @Transactional
    public CategoryResponseDto deleteCategory(Long id) {
        Categories category = findCategory(id);
        category.setIsDelete(true);
        return CategoryResponseDto.from(categoriesRepo.save(category));
    }

    private Categories findCategory(Long id) {
        return categoriesRepo.findByIdAndIsDeleteFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
    }

    private void applyFields(Categories category, CreateCategoryRequestDto request) {
        category.setName(request.getName());
        category.setDescription(request.getDescription());
        category.setSequenceNumber(request.getSequenceNumber());
        category.setOrderType(request.getOrderType());

        if (request.getStatus() != null) category.setStatus(request.getStatus());
        if (request.getIsComboCategory() != null) category.setIsComboCategory(request.getIsComboCategory());
        if (request.getIsStatusAutoUpdated() != null) category.setIsStatusAutoUpdated(request.getIsStatusAutoUpdated());

        // null = not sent (keep as is), empty = clear, values = replace
        if (request.getFoodType() != null) {
            category.getFoodType().clear();
            category.getFoodType().addAll(new ArrayList<>(request.getFoodType()));
        }

        if (request.getExcludedOutletIds() != null) {
            List<Outlets> outlets = request.getExcludedOutletIds().isEmpty()
                    ? List.of()
                    : outletsRepo.findAllById(request.getExcludedOutletIds());

            if (outlets.size() != request.getExcludedOutletIds().size()) {
                throw new IllegalArgumentException("One or more outlets not found");
            }

            category.getExcludedOutlets().clear();
            category.getExcludedOutlets().addAll(outlets);
        }
    }
}
