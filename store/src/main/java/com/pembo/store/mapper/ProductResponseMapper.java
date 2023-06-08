package com.pembo.store.mapper;

import com.pembo.store.dto.CategoryDto;
import com.pembo.store.dto.ProductResponseDto;
import com.pembo.store.model.Category;
import com.pembo.store.model.Product;
import com.pembo.store.utils.MapperUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses ={MapperUtils.class, CategoryMapper.class} )
public interface ProductResponseMapper {

    ProductResponseMapper INSTANCE = Mappers.getMapper(ProductResponseMapper.class);

    @Mapping(source = "reviews", target = "reviewsCount", qualifiedByName = "calculateReviewCount")
    @Mapping(source = "reviews", target = "averageReviewRating", qualifiedByName = "calculateAverageRating")
    @Mapping(source = "productCategories.category", target = "categories")
    ProductResponseDto toDto(Product product);

    // Map Category to CategoryDto
    CategoryDto map(Category category);
}
