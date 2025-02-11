package net.saravana.services;
import lombok.RequiredArgsConstructor;
import net.saravana.dto.AccountDto;
import net.saravana.dto.CredentialsDto;
import net.saravana.dto.IdentityDto;
import net.saravana.dto.UserDto;
import net.saravana.entities.Account;
import net.saravana.entities.User;
import net.saravana.exceptions.AppException;
import net.saravana.mapper.AccountMapper;
import net.saravana.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthService {

    @Autowired
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final PasswordEncoder passwordEncoder;


    // Method to verify if the raw password matches the encoded password
    public AccountDto findByEmail(String email){
        Account account = accountRepository.findByEmail(email)
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));
        return accountMapper.toAccountDto(account);
    }

    // Example usage in your login/authentication logic
    public AccountDto authenticateUser(CredentialsDto credentialsDto) {
        // Retrieve the encoded password from your database based on the username
        Account dbAccount = accountRepository.findByEmail(credentialsDto.getEmail())
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));

        if(passwordEncoder.matches(CharBuffer.wrap(credentialsDto.getPassword()), dbAccount.getPassword())){
            return accountMapper.toAccountDto(dbAccount);
        }
        throw new AppException("Invalid password", HttpStatus.BAD_REQUEST);
    }


    public AccountDto signupUser(IdentityDto identityDto) {
        Optional<Account> optionalUser = accountRepository.findByEmail(identityDto.getEmail());
        if(optionalUser.isPresent()){
            throw new AppException("Account already exists", HttpStatus.BAD_REQUEST);
        }
        Account account = accountMapper.identityToAccount(identityDto);
        account.setPassword(passwordEncoder.encode(CharBuffer.wrap(identityDto.getPassword())));
        Account savedAccount = accountRepository.save(account);
        return accountMapper.toAccountDto(savedAccount);
    }


}
