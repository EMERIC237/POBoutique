package com.pembo.store.mapper;

import com.pembo.store.dto.ProductDto;
import com.pembo.store.model.Product;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "imageUrl", target = "imageUrl")
    ProductDto toDto(Product product);

    @InheritInverseConfiguration
    @Mapping(target = "description", ignore = true)
    @Mapping(target = "price", ignore = true)
    @Mapping(target = "stockQuantity", ignore = true)
    @Mapping(target = "reviews", ignore = true)
    @Mapping(target = "orderItems", ignore = true)
    @Mapping(target = "cartItems", ignore = true)
    @Mapping(target = "productCategories", ignore = true)
    Product toEntity(ProductDto productDto);
}
