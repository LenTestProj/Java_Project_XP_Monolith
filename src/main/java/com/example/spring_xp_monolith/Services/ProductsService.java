package com.example.spring_xp_monolith.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.spring_xp_monolith.Controllers.Errors.ResourceNotFoundException;
import com.example.spring_xp_monolith.dao.CategoriesRepo;
import com.example.spring_xp_monolith.dao.OutletsRepo;
import com.example.spring_xp_monolith.dao.ProductsRepo;
import com.example.spring_xp_monolith.dto.products.CreateProductRequestDto;
import com.example.spring_xp_monolith.dto.products.ProductResponseDto;
import com.example.spring_xp_monolith.dto.products.UpdateProductRequestDto;
import com.example.spring_xp_monolith.models.Categories;
import com.example.spring_xp_monolith.models.Outlets;
import com.example.spring_xp_monolith.models.Products;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductsService {

    private final ProductsRepo productsRepo;
    private final CategoriesRepo categoriesRepo;
    private final OutletsRepo outletsRepo;
    private final ProductImageService productImageService;

    @Transactional
    public ProductResponseDto createProduct(
            CreateProductRequestDto request,
            MultipartFile displayImage,
            MultipartFile coverImage) {

        Products product = new Products();
        applyFields(product, request);

        // Display image - 400 x 400, cover image - 1200 x 400
        product.setDisplayImage(productImageService.uploadProductImage(displayImage, "display", 400, 400));
        product.setCoverPicture(productImageService.uploadProductImage(coverImage, "cover", 1200, 400));

        return ProductResponseDto.from(productsRepo.save(product));
    }

    @Transactional(readOnly = true)
    public List<ProductResponseDto> getProducts(Long categoryId) {
        List<Products> products = categoryId == null
                ? productsRepo.findAllByIsDeleteFalseOrderBySequenceNumberAsc()
                : productsRepo.findAllByCategoryIdAndIsDeleteFalseOrderBySequenceNumberAsc(categoryId);

        return products.stream().map(ProductResponseDto::from).toList();
    }

    @Transactional(readOnly = true)
    public ProductResponseDto getProductById(Long id) {
        return ProductResponseDto.from(findProduct(id));
    }

    @Transactional
    public ProductResponseDto updateProduct(
            UpdateProductRequestDto request,
            MultipartFile displayImage,
            MultipartFile coverImage) {

        Products product = findProduct(request.getId());
        applyFields(product, request);

        // Images are optional on update; keep the existing ones if not sent.
        if (displayImage != null && !displayImage.isEmpty()) {
            product.setDisplayImage(productImageService.uploadProductImage(displayImage, "display", 400, 400));
        }
        if (coverImage != null && !coverImage.isEmpty()) {
            product.setCoverPicture(productImageService.uploadProductImage(coverImage, "cover", 1200, 400));
        }

        return ProductResponseDto.from(productsRepo.save(product));
    }

    @Transactional
    public ProductResponseDto deleteProduct(Long id) {
        Products product = findProduct(id);
        product.setIsDelete(true);
        return ProductResponseDto.from(productsRepo.save(product));
    }

    private Products findProduct(Long id) {
        return productsRepo.findByIdAndIsDeleteFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    }

    private void applyFields(Products product, CreateProductRequestDto request) {
        Categories category = categoriesRepo.findByIdAndIsDeleteFalse(request.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        product.setCategory(category);
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setProductCost(request.getProductCost());
        product.setMrp(request.getMrp());
        product.setOfferPrice(request.getOfferPrice());
        product.setHsn(request.getHsn());
        product.setTaxPercent(request.getTaxPercent());
        product.setMakingTime(request.getMakingTime());
        product.setHasAddon(request.getHasAddon());
        product.setFoodType(request.getFoodType());
        product.setClassType(request.getClassType());
        product.setCalories(request.getCalories());
        product.setWeight(request.getWeight());
        product.setKeywords(request.getKeywords() == null ? null : String.join(",", request.getKeywords()));
        product.setSequenceNumber(request.getSequenceNumber());
        product.setIsComboProduct(request.getIsComboProduct());
        product.setIsFreebieProduct(request.getIsFreebieProduct());
        product.setIsStatusAutoUpdated(request.getIsStatusAutoUpdated());

        // Lists: null = not sent (keep as is), empty = clear, values = replace
        if (request.getProductsNature() != null) {
            product.setProductsNature(new ArrayList<>(request.getProductsNature()));
        }

        if (request.getExcludedOutletIds() != null) {
            List<Outlets> outlets = request.getExcludedOutletIds().isEmpty()
                    ? List.of()
                    : outletsRepo.findAllById(request.getExcludedOutletIds());

            if (outlets.size() != request.getExcludedOutletIds().size()) {
                throw new IllegalArgumentException("One or more outlets not found");
            }

            product.getExcludedOutlets().clear();
            product.getExcludedOutlets().addAll(outlets);
        }
    }
}
