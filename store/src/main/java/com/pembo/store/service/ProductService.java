package com.pembo.store.service;

import com.pembo.store.dto.*;
import com.pembo.store.mapper.ProductCategoryMapper;
import com.pembo.store.mapper.ProductRequestMapper;
import com.pembo.store.mapper.ProductResponseMapper;
import com.pembo.store.model.Category;
import com.pembo.store.model.Product;
import com.pembo.store.model.ProductCategory;
import com.pembo.store.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductResponseMapper productResponseMapper;
    private final ProductRequestMapper productRequestMapper;
    private final CategoryService categoryService;
    private final ProductCategoryService productCategoryService;

    // Constructor with dependency injection
    public ProductService(ProductRepository productRepository, ProductResponseMapper productResponseMapper, ProductRequestMapper productRequestMapper, CategoryService categoryService, ProductCategoryService productCategoryService) {
        this.productRepository = productRepository;
        this.productResponseMapper = productResponseMapper;
        this.productRequestMapper = productRequestMapper;
        this.categoryService = categoryService;
        this.productCategoryService = productCategoryService;
    }

    public List<ProductResponseDto> getAllProducts() {
        return productRepository.findAll().stream().map(productResponseMapper::toDto).collect(Collectors.toList());
    }

    public ProductResponseDto getProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product Not found"));
        return productResponseMapper.toDto(product);
    }

    @Transactional
    public ProductResponseDto saveProduct(ProductRequestDto productRequestDto) {
        Product product = productRequestMapper.toEntity(productRequestDto);
        Product savedProduct = productRepository.save(product);

        manageProductCategories(productRequestDto.categories(), savedProduct);

        return productResponseMapper.toDto(savedProduct);
    }

    @Transactional
    public ProductResponseDto updateProduct(Long id, ProductRequestDto productRequestDto) {
        Product product = productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product Not found"));
        productRequestMapper.partialUpdate(productRequestDto, product);

        Product savedProduct = productRepository.save(product);

        manageProductCategories(productRequestDto.categories(), savedProduct);

        return productResponseMapper.toDto(savedProduct);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    private void manageProductCategories(List<String> categoryNames, Product product) {
        Set<ProductCategory> updatedProductCategories = new LinkedHashSet<>();
        ProductDto productDto = getProductDtoFromProduct(product);
        categoryNames.forEach(categoryName -> {
            CategoryDto fetchedCategory = categoryService.createOrFindCategory(categoryName);
            updatedProductCategories.add(productCategoryService.manageProductCategory(productDto, fetchedCategory));
        });

        // Remove old categories that are not in the updated list

        product.getProductCategories().removeIf(productCategory -> !updatedProductCategories.contains(productCategory));
        product.setProductCategories(updatedProductCategories);
    }

    private ProductDto getProductDtoFromProduct(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setImageUrl(product.getImageUrl());
        return productDto;
    }
}
