package com.pembo.store.mapper;

import com.pembo.store.dto.AddressDto;
import com.pembo.store.model.Address;
import com.pembo.store.model.User;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface AddressMapper {
    @Mapping(source = "userId", target = "user")
    Address toEntity(AddressDto addressDto);

    @Mapping(source = "user.id", target = "userId")
    AddressDto toDto(Address address);

    default User map(Long userId) {
        if (userId == null) {
            return null;
        }

        User user = new User();
        user.setId(userId);
        return user;
    }

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Address partialUpdate(AddressDto addressDto, @MappingTarget Address address);
}