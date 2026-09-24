package com.shop.identity.resource;

import com.shop.identity.payload.RegistrationResponse;
import com.shop.identity.domain.Account;
import com.shop.identity.dao.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List; // 🟢 CRITICAL IMPORT

@RestController
@RequestMapping("/api/v1/auth")
public class RegistrationResource {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder securityEncoder;

    @PostMapping("/signup")
    public ResponseEntity<RegistrationResponse> createNewAccount(@RequestBody Account account) {
        String securePassword = securityEncoder.encode(account.getPassword());
        account.setPassword(securePassword);
        accountRepository.save(account);

        String dynamicUsername = account.getFirstName() + " " + account.getLastName();
        RegistrationResponse trackingPayload = new RegistrationResponse(dynamicUsername, account.getEmail());
        return new ResponseEntity<>(trackingPayload, HttpStatus.CREATED);
    }

    // 🟢 CLEAN COMPILING SEARCH ENDPOINT
    @GetMapping("/search") 
    public ResponseEntity<List<Account>> searchAccounts(@RequestParam("keyword") String keyword) {
        List<Account> matchingAccounts = accountRepository.searchAccounts(keyword);
        return new ResponseEntity<>(matchingAccounts, HttpStatus.OK);
    }
}
