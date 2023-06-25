package com.pembo.store.mapper;

import com.pembo.store.dto.OrderItemDto;
import com.pembo.store.model.OrderItem;
import org.mapstruct.*;


@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public interface OrderItemMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "unitPrice", target = "unitPrice")
    @Mapping(source = "quantity", target = "quantity")
    @Mapping(source = "product", target = "product")
    OrderItemDto toDto(OrderItem orderItem);

    @InheritInverseConfiguration
    @Mapping(target = "order", ignore = true)
    OrderItem toEntity(OrderItemDto orderItemDto);
}
