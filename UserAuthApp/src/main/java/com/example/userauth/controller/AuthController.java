package com.example.userauth.controller;

import com.example.userauth.service.AuthService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/")
    public String home() {
        return "User Auth App is Running Successfully!";
    }

    @PostMapping("/api/register")
    public ResponseEntity<?> register(
            @RequestBody Map<String, String> request) {

        boolean result = authService.register(
                request.get("firstName"),
                request.get("lastName"),
                request.get("mobile"),
                request.get("email"),
                request.get("password")
        );

        if (!result) {
            return ResponseEntity.badRequest()
                    .body("Email already registered");
        }

        return ResponseEntity.ok(
                "Registration successful"
        );
    }

    @PostMapping("/api/login")
    public ResponseEntity<?> login(
            @RequestBody Map<String, String> request) {

        String token = authService.login(
                request.get("email"),
                request.get("password")
        );

        if (token == null) {
            return ResponseEntity.status(401)
                    .body("Invalid email or password");
        }

        return ResponseEntity.ok(
                Map.of(
                        "message", "Login successful",
                        "token", token
                )
        );
    }

    @GetMapping("/api/profile")
    public ResponseEntity<?> profile() {

        return ResponseEntity.ok(
                Map.of(
                        "message",
                        "Protected profile accessed successfully"
                )
        );
    }

    @PostMapping("/api/logout")
    public ResponseEntity<?> logout(
            @RequestHeader(
                    value = "Authorization",
                    required = false
            ) String header) {

        if (header == null ||
                !header.startsWith("Bearer ")) {

            return ResponseEntity.badRequest()
                    .body("Token is required");
        }

        String token = header.substring(7);

        authService.logout(token);

        return ResponseEntity.ok(
                "Logout successful. Token invalidated."
        );
    }
}