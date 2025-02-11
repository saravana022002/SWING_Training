package net.saravana.mapper;

import net.saravana.dto.AccountDto;
import net.saravana.dto.IdentityDto;
import net.saravana.dto.SignUpDto;
import net.saravana.dto.UserDto;
import net.saravana.entities.Account;
import net.saravana.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    AccountDto toAccountDto(Account account);
    @Mapping(target = "password", ignore = true)
    Account identityToAccount(IdentityDto identityDto);
}
