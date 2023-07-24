package com.pembo.store.mapper;

import com.pembo.store.dto.UserDto;
import com.pembo.store.model.User;
import com.pembo.store.model.UserRole;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    @Mapping(target = "role", source = "role", qualifiedByName = "stringToUserRole")
    User toEntity(UserDto userDto);

    UserDto toDto(User user);

    @Named("stringToUserRole")
    default UserRole stringToUserRole(String role) {
        return UserRole.valueOf(role.toUpperCase());
    }

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User partialUpdate(UserDto userDto, @MappingTarget User user);
}