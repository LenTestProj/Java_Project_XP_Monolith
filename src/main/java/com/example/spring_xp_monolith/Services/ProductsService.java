package com.example.spring_xp_monolith.Services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.spring_xp_monolith.Controllers.Errors.ResourceNotFoundException;
import com.example.spring_xp_monolith.dao.ProductsRepo;
import com.example.spring_xp_monolith.dto.products.ProductResponseDto;
import com.example.spring_xp_monolith.dao.CategoriesRepo;
import com.example.spring_xp_monolith.dao.OutletsRepo;
import com.example.spring_xp_monolith.dto.products.CreateProductRequestDto;
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

        // Find category
        Categories category = categoriesRepo.findByIdAndIsDeleteFalse(request.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        Products product = new Products();

        // Display image - 400 x 400
        String displayImagePath = productImageService.uploadProductImage(
                displayImage,
                "display",
                400,
                400
        );

        // Cover image - 1200 x 400
        String coverImagePath = productImageService.uploadProductImage(
                coverImage,
                "cover",
                1200,
                400
        );

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
        product.setKeywords(request.getKeywords());
        product.setSequenceNumber(request.getSequenceNumber());
        product.setIsEventProduct(request.getIsEventProduct());
        product.setIsComboProduct(request.getIsComboProduct());
        product.setIsFreebieProduct(request.getIsFreebieProduct());
        product.setProductsNature(request.getProductsNature());
        product.setIsStatusAutoUpdated(request.getIsStatusAutoUpdated());
        product.setIsNewProduct(request.getIsNewProduct());


        product.setDisplayImage(displayImagePath);
        product.setCoverPicture(coverImagePath);

        // Assign excluded outlets
        if (request.getExcludedOutletIds() != null &&
                !request.getExcludedOutletIds().isEmpty()) {

            List<Outlets> excludedOutlets = outletsRepo.findAllById(
                    request.getExcludedOutletIds()
            );

            if (excludedOutlets.size() != request.getExcludedOutletIds().size()) {
                throw new IllegalArgumentException("One or more outlets not found");
            }

            product.setExcludedOutlets(excludedOutlets);
        }

        return ProductResponseDto.from(productsRepo.save(product));
    }
}