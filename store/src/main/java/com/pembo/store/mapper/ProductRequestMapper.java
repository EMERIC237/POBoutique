package com.pembo.store.mapper;

import com.pembo.store.dto.ProductRequestDto;
import com.pembo.store.model.Product;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductRequestMapper {
    Product toEntity(ProductRequestDto productRequestDto);

    ProductRequestDto toDto(Product product);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Product partialUpdate(ProductRequestDto productRequestDto, @MappingTarget Product product);
}