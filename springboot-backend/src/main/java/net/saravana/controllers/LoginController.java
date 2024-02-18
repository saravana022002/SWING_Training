package net.saravana.controllers;

import net.saravana.entities.Account;
import net.saravana.services.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
public class LoginController {

    private final AuthService authService;


    public LoginController(AuthService authService) {
        this.authService = authService;
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
