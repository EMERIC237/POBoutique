package com.pembo.store.mapper;

import com.pembo.store.dto.CartItemDto;
import com.pembo.store.model.CartItem;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CartItemMapper {
    @Mapping(source = "productImageUrl", target = "product.imageUrl")
    @Mapping(source = "productPrice", target = "product.price")
    @Mapping(source = "productDescription", target = "product.description")
    @Mapping(source = "productName", target = "product.name")
    @Mapping(source = "productId", target = "product.id")
    CartItem toEntity(CartItemDto cartItemDto);

    @InheritInverseConfiguration(name = "toEntity")
    CartItemDto toDto(CartItem cartItem);

    @InheritConfiguration(name = "toEntity")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    CartItem partialUpdate(CartItemDto cartItemDto, @MappingTarget CartItem cartItem);
}