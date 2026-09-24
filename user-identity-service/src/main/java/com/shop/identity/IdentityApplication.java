package com.shop.identity;

import com.shop.identity.domain.Account;
import com.shop.identity.dao.AccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class IdentityApplication {

    public static void main(String[] args) {
        SpringApplication.run(IdentityApplication.class, args);
    }

    // 🟢 This automatically populates the database with search data when the app runs
    @Bean
    public CommandLineRunner insertSampleData(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            Account sampleAccount = new Account(
                null, 
                "Arun", 
                "Kumar", 
                "9447001122", 
                "arunkumar@example.com", 
                passwordEncoder.encode("Password987") // Securely encoded password
            );
            
            accountRepository.save(sampleAccount);
            System.out.println("🟢 Sample search user 'Arun Kumar' inserted successfully!");
        };
    }
}
