package com.example.userauth.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class AuthService {

    private final Map<String, User> users = new HashMap<>();
    private final Map<String, String> tokens = new HashMap<>();

    public boolean register(
            String firstName,
            String lastName,
            String mobile,
            String email,
            String password) {

        if (users.containsKey(email)) {
            return false;
        }

        users.put(email, new User(
                firstName,
                lastName,
                mobile,
                email,
                password
        ));

        return true;
    }

    public String login(String email, String password) {

        User user = users.get(email);

        if (user == null || !user.password.equals(password)) {
            return null;
        }

        String token = UUID.randomUUID().toString();

        tokens.put(token, email);

        return token;
    }

    public boolean isValidToken(String token) {
        return tokens.containsKey(token);
    }

    public String getEmailFromToken(String token) {
        return tokens.get(token);
    }

    public void logout(String token) {
        tokens.remove(token);
    }

    private static class User {

        String firstName;
        String lastName;
        String mobile;
        String email;
        String password;

        User(
                String firstName,
                String lastName,
                String mobile,
                String email,
                String password) {

            this.firstName = firstName;
            this.lastName = lastName;
            this.mobile = mobile;
            this.email = email;
            this.password = password;
        }
    }
}