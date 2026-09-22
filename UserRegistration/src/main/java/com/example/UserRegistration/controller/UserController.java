package com.example.UserRegistration.controller;

import com.example.UserRegistration.model.User;
import com.example.UserRegistration.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Sign Up page
    @GetMapping("/")
    public String signupPage(Model model) {

        model.addAttribute("user", new User());

        return "signup";
    }

    // Save user
    @PostMapping("/signup")
    public String signup(
            @ModelAttribute User user,
            Model model) {

        // Check email already exists
        if (userRepository.existsByEmail(user.getEmail())) {

            model.addAttribute("error", "Email already exists.");

            return "signup";
        }

        userRepository.save(user);

        model.addAttribute("message",
                "Registration successful! Please login.");

        return "login";
    }

    // Login page
    @GetMapping("/login")
    public String loginPage() {

        return "login";
    }

    // Login
    @PostMapping("/login")
    public String login(
            @ModelAttribute User user,
            HttpSession session,
            Model model) {

        User existingUser =
                userRepository.findByEmail(user.getEmail()).orElse(null);

        if (existingUser != null &&
                existingUser.getPassword().equals(user.getPassword())) {

            session.setAttribute("userId", existingUser.getId());
            session.setAttribute("fullName", existingUser.getFullName());

            return "redirect:/welcome";
        }

        model.addAttribute("error",
                "Invalid email or password.");

        return "login";
    }

    // Welcome page
    @GetMapping("/welcome")
    public String welcomePage(
            HttpSession session,
            Model model) {

        String fullName =
                (String) session.getAttribute("fullName");

        if (fullName == null) {
            return "redirect:/login";
        }

        model.addAttribute("fullName", fullName);

        return "welcome";
    }

    // Logout
    @GetMapping("/logout")
    public String logout(HttpSession session) {

        session.invalidate();

        return "redirect:/login";
    }
}