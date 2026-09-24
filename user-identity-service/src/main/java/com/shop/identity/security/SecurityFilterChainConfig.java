package com.shop.identity.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityFilterChainConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain baseSecurityFilter(HttpSecurity http) throws Exception {
        http
            // 1. Completely disable CSRF protection
            .csrf(csrf -> csrf.disable())
            
            // 2. Open up EVERY SINGLE ENDPOINT so security cannot block anything at all
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll() // 🟢 This completely disables 403 blocks for all URLs!
            )
            
            // 3. Disable frame options so H2 console works
            .headers(headers -> headers.frameOptions(frame -> frame.disable()));
        
        return http.build();
    }
}
