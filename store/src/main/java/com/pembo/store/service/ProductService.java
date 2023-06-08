package com.pembo.store.service;

import com.pembo.store.dto.ProductRequestDto;
import com.pembo.store.dto.ProductResponseDto;
import com.pembo.store.mapper.ProductRequestMapper;
import com.pembo.store.mapper.ProductResponseMapper;
import com.pembo.store.model.Product;
import com.pembo.store.repository.CartItemRepository;
import com.pembo.store.repository.CategoryRepository;
import com.pembo.store.repository.ProductCategoryRepository;
import com.pembo.store.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductCategoryRepository productCategoryRepository;
    private final ProductResponseMapper productResponseMapper;
    private final ProductRequestMapper productRequestMapper;
    private final CartItemRepository cartItemRepository;

    // inject the dependencies in the constructor
    @Autowired
    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository,
            ProductCategoryRepository productCategoryRepository, ProductResponseMapper productResponseMapper,
            ProductRequestMapper productRequestMapper, CartItemRepository cartItemRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.productCategoryRepository = productCategoryRepository;
        this.productResponseMapper = productResponseMapper;
        this.productRequestMapper = productRequestMapper;
        this.cartItemRepository = cartItemRepository;
    }

    public List<ProductResponseDto> getAllProducts() {
        return productRepository.findAll().stream().map(productResponseMapper::toDto).collect(Collectors.toList());
    }

    public ProductResponseDto getProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product Not found"));
        return productResponseMapper.toDto(product);
    }

    @Transactional
    public ProductResponseDto saveProduct(ProductRequestDto productRequestDto) {
        Product product = productRequestMapper.toEntity(productRequestDto);

        // handle the categories
        // Product finalProduct = product;
        // productRequestDto.getCategories().forEach(categoryName -> {
        //     Category category = categoryRepository.findByName(categoryName);
        //     // if category does not exist, create it and save it to the database
        //     if (category == null) {
        //         category = new Category();
        //         category.setName(categoryName);
        //         category = categoryRepository.save(category);
        //     }

        //     ProductCategory productCategory = new ProductCategory();
        //     productCategory.setProduct(finalProduct);
        //     productCategory.setCategory(category);
        //     productCategoryRepository.save(productCategory);
        // });

        Product savedProduct = productRepository.save(product);

        return productResponseMapper.toDto(savedProduct);
    }


    public ProductResponseDto updateProduct(Long id, ProductRequestDto productRequestDto) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product Not found"));
        productRequestMapper.partialUpdate(productRequestDto, product);
        Product savedProduct = productRepository.save(product);
        return productResponseMapper.toDto(savedProduct);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

}
