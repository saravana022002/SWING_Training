package net.javaguides.auth.service;
import net.javaguides.auth.model.Account;
import net.javaguides.auth.repository.AccountRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

// ...

@Service
public class AuthService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
        // Initialize the Argon2PasswordEncoder with the same parameters used during encoding
        this.passwordEncoder = Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8();
    }

    // Method to verify if the raw password matches the encoded password
    public boolean verifyPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    // Example usage in your login/authentication logic
    public boolean authenticateUser(Account account) {
        // Retrieve the encoded password from your database based on the username
        String storedEncodedPassword = getEncodedPasswordByUsername(account.getUserName());

        // Verify if the raw password matches the stored encoded password
        return verifyPassword(account.getPassword(), storedEncodedPassword);
    }

    // Replace this method with your actual logic to retrieve the encoded password
    private String getEncodedPasswordByUsername(String username) {
        Account account = accountRepository.findByUserName(username);
        return (account != null) ? account.getPassword() : null;
    }
    private Account getAccountByUsername(String username) {
        return accountRepository.findByUserName(username);
    }

    public Map<String, Object> signupUser(Account account) {
        Map<String, Object> props = new HashMap<>();

        Account dbAccount = getAccountByUsername(account.getUserName());
        if (dbAccount != null) {
            props.put("message", "Account already found for this username");
            props.put("isCreated", false);
            return props;
        }

        String password = account.getPassword();
        if (StringUtils.isEmpty(password)) {
            props.put("message", "Account not created (password cannot be null or empty)");
            props.put("isCreated", false);
            return props;
        }

        account.setPassword(passwordEncoder.encode(password));
        Account savedAccount = accountRepository.save(account);

        props.put("message", "Account created successfully");
        props.put("account", savedAccount);
        props.put("isCreated", true);

        return props;
    }


}
