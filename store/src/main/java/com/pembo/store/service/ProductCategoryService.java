package com.pembo.store.service;

import com.pembo.store.dto.CategoryDto;
import com.pembo.store.dto.ProductCategoryDto;
import com.pembo.store.dto.ProductDto;
import com.pembo.store.exception.ResourceNotFoundException;
import com.pembo.store.mapper.ProductCategoryMapper;
import com.pembo.store.model.Category;
import com.pembo.store.model.Product;
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

    public ProductCategory getOrCreateProductCategory(ProductDto productDto, CategoryDto categoryDto) {
        ProductCategoryDto productCategoryDto = new ProductCategoryDto(null, productDto, categoryDto);
        Optional<ProductCategoryDto> existingProductCategoryDto = findByProductAndCategory(productDto, categoryDto);
        if (existingProductCategoryDto.isPresent()) {
            return productCategoryMapper.toEntity(existingProductCategoryDto.get());
        } else {
            ProductCategoryDto fetchedProductCategoryDto = createProductCategory(productCategoryDto);
            return productCategoryMapper.toEntity(fetchedProductCategoryDto);
        }
    }

    public ProductCategoryDto findProductCategoryById(Long id) {
        return productCategoryRepository.findById(id)
                .map(productCategoryMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("ProductCategory", id));
    }

    public ProductCategoryDto updateProductCategory(Long id, ProductCategoryDto productCategoryDto) {
        return productCategoryRepository.findById(id).map(existingProductCategory -> {
            ProductCategory updatedProductCategory = productCategoryMapper.toEntity(productCategoryDto);
            updatedProductCategory.setId(existingProductCategory.getId());
            updatedProductCategory = productCategoryRepository.save(updatedProductCategory);
            return productCategoryMapper.toDto(updatedProductCategory);
        }).orElseThrow(() -> new ResourceNotFoundException("ProductCategory", id));
    }

    public Optional<ProductCategoryDto> findByProductAndCategory(ProductDto productDto, CategoryDto categoryDto) {
        ProductCategoryDto productCategoryDto = new ProductCategoryDto(null, productDto, categoryDto);
        ProductCategory productCategory = productCategoryMapper.toEntity(productCategoryDto);
        return productCategoryRepository.findByProductAndCategory(productCategory.getProduct(), productCategory.getCategory())
                .map(productCategoryMapper::toDto);
    }

    public void deleteProductCategory(Long id) {
        productCategoryRepository.deleteById(id);
    }

    public void deleteProductCategoryByProductAndCategory(Product product, Category category) {
        ProductCategory productCategory = productCategoryRepository.findByProductAndCategory(product, category)
                .orElseThrow(() -> new ResourceNotFoundException("ProductCategory not found for given Product and Category"));
        productCategoryRepository.delete(productCategory);
    }

}