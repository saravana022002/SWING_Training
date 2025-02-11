package net.saravana.mapper;

import net.saravana.dto.SignUpDto;
import net.saravana.dto.UserDto;
import net.saravana.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toUserDto(User user);
    @Mapping(target = "password", ignore = true)
    User signUpToUser(SignUpDto userDto);
}
