package com.pembo.store.repository;

import com.pembo.store.model.Category;
import com.pembo.store.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import com.pembo.store.model.ProductCategory;

import java.util.List;
import java.util.Optional;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {

    Optional<ProductCategory> findByProductAndCategory(Product product, Category category);
}