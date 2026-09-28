package com.example.userauth.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final TokenFilter tokenFilter;

    public SecurityConfig(TokenFilter tokenFilter) {
        this.tokenFilter = tokenFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())

            .formLogin(form -> form.disable())

            .httpBasic(basic -> basic.disable())

            .authorizeHttpRequests(auth -> auth

                .requestMatchers(
                        "/",
                        "/index.html",
                        "/register.html",
                        "/login.html",
                        "/profile.html",
                        "/api/register",
                        "/api/login",
                        "/api/logout"
                ).permitAll()

                .requestMatchers("/api/profile")
                .authenticated()

                .anyRequest()
                .permitAll()
            )

            .addFilterBefore(
                    tokenFilter,
                    UsernamePasswordAuthenticationFilter.class
            );

        return http.build();
    }
}