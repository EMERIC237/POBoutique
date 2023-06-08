package com.pembo.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pembo.store.model.ProductCategory;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
}