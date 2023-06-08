package com.pembo.store.mapper;

import com.pembo.store.dto.CartDto;
import com.pembo.store.model.Cart;
import com.pembo.store.utils.MapperUtils;

import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {CartItemMapper.class, MapperUtils.class})
public interface CartMapper {
    @Mapping(source = "userUsername", target = "user.username")
    @Mapping(source = "userId", target = "user.id")
    Cart toEntity(CartDto cartDto);

    @AfterMapping
    default void linkCartItems(@MappingTarget Cart cart) {
        cart.getCartItems().forEach(cartItem -> cartItem.setCart(cart));
    }

    @Mapping(source = "cartItems", target = "totalPrice", qualifiedByName = "calculateTotalPrice")
    CartDto toDto(Cart cart);


    @InheritConfiguration(name = "toEntity")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Cart partialUpdate(CartDto cartDto, @MappingTarget Cart cart);
}