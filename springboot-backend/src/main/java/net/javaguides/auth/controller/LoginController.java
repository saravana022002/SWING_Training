package net.javaguides.auth.controller;

import net.javaguides.auth.model.Account;
import net.javaguides.auth.repository.AccountRepository;
import net.javaguides.auth.service.AuthService;
import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class LoginController {

    private final AuthService authService;
    @Autowired
    private final AccountRepository accountRepository;

    public LoginController(AuthService authService, AccountRepository accountRepository) {
        this.authService = authService;
        this.accountRepository = accountRepository;
    }

    // get all employees
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> getLoginUser(@RequestBody Account account) {
        // Your logic to check the login credentials and retrieve the user
        boolean  isAuthenticated = authService.authenticateUser(account);
        Map<String, Object> response = new HashMap<>();
        response.put("isAuthenticated", isAuthenticated);
        if(isAuthenticated){
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    // create employee rest api
    @PostMapping("/signup")
    public ResponseEntity<Map<String, Object>>  createUser(@RequestBody Account account) {
        Map<String, Object> response = authService.signupUser(account);
        if((Boolean) response.get("isCreated")) {
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
