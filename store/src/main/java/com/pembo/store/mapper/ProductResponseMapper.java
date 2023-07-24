package com.pembo.store.mapper;

import com.pembo.store.dto.CategoryDto;
import com.pembo.store.dto.ProductResponseDto;
import com.pembo.store.model.Category;
import com.pembo.store.model.Product;
import com.pembo.store.model.ProductCategory;
import com.pembo.store.model.Review;
import org.mapstruct.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


//!TODO: Find a way to implement this mathcing without using Xml since using string to call function can lead to errors
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring", uses = CategoryMapper.class)
public abstract class ProductResponseMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "price", source = "price")
    @Mapping(target = "stockQuantity", source = "stockQuantity")
    @Mapping(target = "imageUrl", source = "imageUrl")
    @Mapping(target = "categories", expression = "java(getCategoryList(product.getProductCategories()))")
    @Mapping(target = "reviewsCount", expression = "java(product.getReviews().size())")
    @Mapping(target = "averageReviewRating", expression = "java(getAverageRating(product))")
    public abstract ProductResponseDto toDto(Product product);

    protected List<CategoryDto> getCategoryList(Set<ProductCategory> productCategories) {
        return productCategories.stream().map(ProductCategory::getCategory).map(this::toDto).collect(Collectors.toList());
    }

    protected Double getAverageRating(Product product) {
        return product.getReviews().stream().mapToDouble(Review::getRating).average().orElse(0.0);
    }

    protected abstract CategoryDto toDto(Category category);
}


