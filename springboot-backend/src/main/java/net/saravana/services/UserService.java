package net.saravana.services;

import lombok.RequiredArgsConstructor;
import net.saravana.dto.CredentialsDto;
import net.saravana.dto.SignUpDto;
import net.saravana.dto.UserDto;
import net.saravana.entities.User;
import net.saravana.exceptions.AppException;
import net.saravana.mapper.UserMapper;
import net.saravana.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    @Autowired
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;


    public UserDto findByLogin(String login){
        User user = userRepository.findByLogin(login)
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));
        return userMapper.toUserDto(user);
    }

    public UserDto login (CredentialsDto credentialsDto){
        User user = userRepository.findByLogin(credentialsDto.getLogin())
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));

        if(passwordEncoder.matches(CharBuffer.wrap(credentialsDto.getPassword()), user.getPassword())){
            return userMapper.toUserDto(user);
        }
        throw new AppException("Invalid password", HttpStatus.BAD_REQUEST);
    }
    public UserDto register(SignUpDto userDto){
       Optional<User> optionalUser = userRepository.findByLogin(userDto.getLogin());
       if(optionalUser.isPresent()){
           throw new AppException("Login slready exists", HttpStatus.BAD_REQUEST);
       }
       User user = userMapper.signUpToUser(userDto);
       user.setPassword(passwordEncoder.encode(CharBuffer.wrap(userDto.getPassword())));

       User savedUser = userRepository.save(user);
       return userMapper.toUserDto(savedUser);
    }
}
