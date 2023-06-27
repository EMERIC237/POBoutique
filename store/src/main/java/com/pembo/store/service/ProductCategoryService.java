package com.pembo.store.service;

import com.pembo.store.dto.CategoryDto;
import com.pembo.store.dto.ProductCategoryDto;
import com.pembo.store.dto.ProductDto;
import com.pembo.store.mapper.ProductCategoryMapper;
import com.pembo.store.model.ProductCategory;
import com.pembo.store.repository.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductCategoryService {

    private final ProductCategoryRepository productCategoryRepository;
    private final ProductCategoryMapper productCategoryMapper;

    @Autowired
    public ProductCategoryService(ProductCategoryRepository productCategoryRepository, ProductCategoryMapper productCategoryMapper) {
        this.productCategoryRepository = productCategoryRepository;
        this.productCategoryMapper = productCategoryMapper;
    }

    public ProductCategoryDto createProductCategory(ProductCategoryDto productCategoryDto) {
        ProductCategory productCategory = productCategoryMapper.toEntity(productCategoryDto);
        ProductCategory savedProductCategory = productCategoryRepository.save(productCategory);
        return productCategoryMapper.toDto(savedProductCategory);
    }

    public ProductCategory manageProductCategory(ProductDto productDto, CategoryDto categoryDto) {
        ProductCategoryDto productCategoryDto = new ProductCategoryDto(null, productDto, categoryDto);
        ProductCategoryDto fetchedProductCategoryDto = createProductCategory(productCategoryDto);
        return productCategoryMapper.toEntity(fetchedProductCategoryDto);
    }
    public Optional<ProductCategoryDto> getProductCategoryById(Long id) {
        return productCategoryRepository.findById(id).map(productCategoryMapper::toDto);
    }

    public Optional<ProductCategoryDto> updateProductCategory(Long id, ProductCategoryDto productCategoryDto) {
        return productCategoryRepository.findById(id).map(existingProductCategory -> {
            ProductCategory updatedProductCategory = productCategoryMapper.toEntity(productCategoryDto);
            updatedProductCategory.setId(existingProductCategory.getId());
            updatedProductCategory = productCategoryRepository.save(updatedProductCategory);
            return productCategoryMapper.toDto(updatedProductCategory);
        });
    }

    public void deleteProductCategory(Long id) {
        productCategoryRepository.deleteById(id);
    }
}
