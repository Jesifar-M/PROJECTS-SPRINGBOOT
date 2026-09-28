package com.example.usertokenauth.controller;

import com.example.usertokenauth.model.User;
import com.example.usertokenauth.repository.UserRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder =
            new BCryptPasswordEncoder();

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestBody Map<String, String> request) {

        String username = request.get("username");
        String email = request.get("email");
        String password = request.get("password");
        String confirmPassword = request.get("confirmPassword");

        if (username == null || username.isBlank()
                || email == null || email.isBlank()
                || password == null || password.isBlank()
                || confirmPassword == null
                || confirmPassword.isBlank()) {

            return ResponseEntity
                    .badRequest()
                    .body("All fields are required.");
        }

        if (!password.equals(confirmPassword)) {

            return ResponseEntity
                    .badRequest()
                    .body("Password and confirm password do not match.");
        }

        if (userRepository.existsByEmail(email)) {

            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("Email already exists.");
        }

        User user = new User();

        user.setUsername(username);
        user.setEmail(email);

        user.setPassword(
                passwordEncoder.encode(password)
        );

        userRepository.save(user);

        return ResponseEntity.ok("Registration successful.");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody Map<String, String> request) {

        String email = request.get("email");
        String password = request.get("password");

        if (email == null || email.isBlank()
                || password == null || password.isBlank()) {

            return ResponseEntity
                    .badRequest()
                    .body("Email and password are required.");
        }

        User user =
                userRepository.findByEmail(email).orElse(null);

        if (user == null) {

            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid email or password.");
        }

        if (!passwordEncoder.matches(
                password,
                user.getPassword())) {

            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid email or password.");
        }

        String token = UUID.randomUUID().toString();

        user.setToken(token);

        userRepository.save(user);

        Map<String, String> response = new HashMap<>();

        response.put("message", "Login successful.");
        response.put("token", token);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(
            @RequestHeader(
                    value = "Authorization",
                    required = false) String authorization) {

        if (authorization == null
                || authorization.isBlank()) {

            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Token is required.");
        }

        String token = authorization;

        if (authorization.startsWith("Bearer ")) {
            token = authorization.substring(7);
        }

        User user =
                userRepository.findByToken(token).orElse(null);

        if (user == null) {

            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid token.");
        }

        user.setToken(null);

        userRepository.save(user);

        return ResponseEntity.ok("Logout successful.");
    }
}