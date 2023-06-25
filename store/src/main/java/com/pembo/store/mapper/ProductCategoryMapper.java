package com.pembo.store.mapper;

import com.pembo.store.dto.ProductCategoryDto;
import com.pembo.store.model.ProductCategory;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {CategoryMapper.class})
public interface ProductCategoryMapper {

    @Mapping(target="id", source = "id")
    @Mapping(target = "category", source = "category")
    ProductCategoryDto toDto(ProductCategory productCategory);

     @InheritInverseConfiguration
     @Mapping(target = "product", ignore = true)
    ProductCategory toEntity(ProductCategoryDto productCategoryDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ProductCategory partialUpdate(ProductCategoryDto productCategoryDto, @MappingTarget ProductCategory productCategory);
}