package com.pembo.store.mapper;

import com.pembo.store.dto.ProductCategoryDto;
import com.pembo.store.model.CategoryMapper;
import com.pembo.store.model.ProductCategory;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {CategoryMapper.class})
public interface ProductCategoryMapper {
    ProductCategory toEntity(ProductCategoryDto productCategoryDto);

    ProductCategoryDto toDto(ProductCategory productCategory);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ProductCategory partialUpdate(ProductCategoryDto productCategoryDto, @MappingTarget ProductCategory productCategory);
}