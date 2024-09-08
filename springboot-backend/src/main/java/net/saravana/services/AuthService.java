package net.saravana.services;
import lombok.RequiredArgsConstructor;
import net.saravana.dto.AccountDto;
import net.saravana.dto.CredentialsDto;
import net.saravana.dto.IdentityDto;
import net.saravana.entities.User;
import net.saravana.exceptions.AppException;
import net.saravana.mapper.AccountMapper;
import net.saravana.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;
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
        User user = accountRepository.findByEmail(email)
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));
        return accountMapper.toAccountDto(user);
    }

    // Example usage in your login/authentication logic
    public AccountDto authenticateUser(CredentialsDto credentialsDto) {
        // Retrieve the encoded password from your database based on the username
        User dbUser = accountRepository.findByEmail(credentialsDto.getEmail())
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));

        if(passwordEncoder.matches(CharBuffer.wrap(credentialsDto.getPassword()), dbUser.getPassword())){
            return accountMapper.toAccountDto(dbUser);
        }
        throw new AppException("Invalid password", HttpStatus.BAD_REQUEST);
    }


    public AccountDto signupUser(IdentityDto identityDto) {
        Optional<User> optionalUser = accountRepository.findByEmail(identityDto.getEmail());
        if(optionalUser.isPresent()){
            throw new AppException("Account already exists", HttpStatus.BAD_REQUEST);
        }
        User user = accountMapper.identityToAccount(identityDto);
        user.setPassword(passwordEncoder.encode(CharBuffer.wrap(identityDto.getPassword())));
        User savedUser = accountRepository.save(user);
        return accountMapper.toAccountDto(savedUser);
    }


}
