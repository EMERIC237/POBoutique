package com.pembo.store.dto;

import java.math.BigDecimal;

public class ProductDto {
    private Long id;
    private String name;
    private String slug;
    private String description;
    private BigDecimal price;
    private Long stockQuantity;
    private String imageUrl;
    private Long categoryId;
    private int reviewsCount;
    private Double averageReviewRating;

    public ProductDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        this.slug = createSlug(name) + "-" + id;
    }

    public String getSlug() {
        return slug;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Long stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public int getReviewsCount() {
        return reviewsCount;
    }

    public void setReviewsCount(int reviewsCount) {
        this.reviewsCount = reviewsCount;
    }

    public Double getAverageReviewRating() {
        return averageReviewRating;
    }

    public void setAverageReviewRating(Double averageReviewRating) {
        this.averageReviewRating = averageReviewRating;
    }

    private static String createSlug(String input) {
        return input.trim()      // Remove leading and trailing spaces
                .toLowerCase()       // Convert to lowercase
                .replaceAll("[^a-z0-9\\s]", "")   // Replace special characters with nothing
                .replaceAll("\\s+", "-");  // Replace spaces with hyphens
    }
}

