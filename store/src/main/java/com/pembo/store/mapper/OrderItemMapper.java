package com.pembo.store.mapper;

import com.pembo.store.dto.OrderItemDto;
import com.pembo.store.model.OrderItem;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderItemMapper {
    @Mapping(source = "productImageUrl", target = "product.imageUrl")
    @Mapping(source = "productName", target = "product.name")
    OrderItem toEntity(OrderItemDto orderItemDto);

    @InheritInverseConfiguration(name = "toEntity")
    OrderItemDto toDto(OrderItem orderItem);

    @InheritConfiguration(name = "toEntity")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    OrderItem partialUpdate(OrderItemDto orderItemDto, @MappingTarget OrderItem orderItem);
}