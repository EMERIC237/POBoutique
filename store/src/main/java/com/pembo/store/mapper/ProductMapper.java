package com.pembo.store.mapper;

import com.pembo.store.dto.ProductDto;
import com.pembo.store.model.Product;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "imageUrl", target = "imageUrl")
    ProductDto toDto(Product product);

    @InheritInverseConfiguration
    Product toEntity(ProductDto productDto);
}
