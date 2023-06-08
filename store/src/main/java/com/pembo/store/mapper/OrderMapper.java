package com.pembo.store.mapper;

import com.pembo.store.dto.OrderDto;
import com.pembo.store.model.Order;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {AddressMapper.class, OrderItemMapper.class})
public interface OrderMapper {
    @Mapping(source = "userUsername", target = "user.username")
    Order toEntity(OrderDto orderDto);

    @AfterMapping
    default void linkOrderItems(@MappingTarget Order order) {
        order.getOrderItems().forEach(orderItem -> orderItem.setOrder(order));
    }

    @Mapping(source = "user.username", target = "userUsername")
    OrderDto toDto(Order order);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "userUsername", target = "user.username")
    Order partialUpdate(OrderDto orderDto, @MappingTarget Order order);
}