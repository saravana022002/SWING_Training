package net.saravana.mapper;

import net.saravana.dto.AccountDto;
import net.saravana.dto.IdentityDto;
import net.saravana.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    AccountDto toAccountDto(User user);
    @Mapping(target = "password", ignore = true)
    User identityToAccount(IdentityDto identityDto);
}
