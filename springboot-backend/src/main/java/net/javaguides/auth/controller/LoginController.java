package net.javaguides.auth.controller;

import net.javaguides.auth.model.Account;
import net.javaguides.auth.repository.AccountRepository;
import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class LoginController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AccountRepository accountRepository;

    // get all employees
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> getLoginUser(@RequestBody Account account) {
        // Your logic to check the login credentials and retrieve the user
        // For example, you might use accountRepository to validate the credentials

        // Assuming you have a method like validateLoginCredentials in accountRepository
        boolean isAuthenticated = accountRepository.existsByUserNameAndPassword(account.getUserName(), account.getPassword());

        Map<String, Object> response = new HashMap<>();
        response.put("isAuthenticated", isAuthenticated);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    // create employee rest api
    @PostMapping("/signup")
    public ResponseEntity<Map<String, Object>>  createUser(@RequestBody Account account) {
        Account savedAccount = accountRepository.save(account);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Account created successfully");
        response.put("account", savedAccount);
        response.put("isCreated", true);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
