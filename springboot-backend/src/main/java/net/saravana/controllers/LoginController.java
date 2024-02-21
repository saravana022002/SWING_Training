package net.saravana.controllers;

import lombok.RequiredArgsConstructor;
import net.saravana.config.UserAuthProvider;
import net.saravana.dto.AccountDto;
import net.saravana.dto.CredentialsDto;
import net.saravana.dto.IdentityDto;
import net.saravana.services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/")
public class LoginController {

    private final AuthService authService;
    private final UserAuthProvider userAuthProvider;


    // get all employees
    @PostMapping("/login")
    public ResponseEntity<AccountDto> getLoginUser(@RequestBody CredentialsDto credentialsDto) {
        // Your logic to check the login credentials and retrieve the user
        AccountDto accountDto = authService.authenticateUser(credentialsDto);
        accountDto.setToken(userAuthProvider.createToken(accountDto.getEmail()));
        return ResponseEntity.ok(accountDto);

    }


    // create employee rest api
    @PostMapping("/signup")
    public ResponseEntity<AccountDto> createUser(@RequestBody IdentityDto identityDto) {
        AccountDto accountDto = authService.signupUser(identityDto);
        return ResponseEntity.ok(accountDto);
    }
}
