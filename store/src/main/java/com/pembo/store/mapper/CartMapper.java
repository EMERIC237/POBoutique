package com.pembo.store.mapper;

import com.pembo.store.dto.AddressDto;
import com.pembo.store.dto.CartDto;
import com.pembo.store.model.Address;
import com.pembo.store.model.Cart;
import com.pembo.store.model.CartItem;
import com.pembo.store.model.User;
import org.mapstruct.*;

import java.math.BigDecimal;
import java.util.Set;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = CartItemMapper.class)
public interface CartMapper {
    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "userUsername", source = "user.username")
    @Mapping(target = "totalPrice", expression = "java(calculateTotal(cart.getCartItems()))")
    CartDto toDto(Cart cart);


    @Mapping(target = "user", source = "userId", qualifiedByName = "mapUser")
    Cart toEntity(CartDto cartDto);

    @Named("mapUser")
    default User mapUser(Long userId) {
        User user = new User();
        user.setId(userId);
        return user;
    }

    default BigDecimal calculateTotal(Set<CartItem> cartItems) {
        return cartItems.stream()
                .map(cartItem -> cartItem.getProduct().getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Cart partialUpdate(CartDto cartDto, @MappingTarget Cart cart);
}