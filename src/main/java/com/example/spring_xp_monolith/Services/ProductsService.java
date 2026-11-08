package com.example.spring_xp_monolith.Services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.spring_xp_monolith.dao.ProductsRepo;
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

    @Transactional
    public Products createProduct(CreateProductRequestDto request) {

        // Find category
        Categories category = categoriesRepo.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Products product = new Products();

        product.setCategory(category);
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        // product.setDisplayName(request.getDisplayName());
        product.setCoverPicture(request.getCoverPicture());
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

        // Assign excluded outlets
        if (request.getExcludedOutletIds() != null &&
                !request.getExcludedOutletIds().isEmpty()) {

            List<Outlets> excludedOutlets = outletsRepo.findAllById(
                    request.getExcludedOutletIds()
            );

            if (excludedOutlets.size() != request.getExcludedOutletIds().size()) {
                throw new RuntimeException("One or more outlets not found");
            }

            product.setExcludedOutlets(excludedOutlets);
        }

        return productsRepo.save(product);
    }
}
