package com.example.UserLogin.controller;

import com.example.UserLogin.User;
import com.example.UserLogin.UserRepository;

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

    // Register page
    @GetMapping("/register")
    public String registerPage(Model model) {

        model.addAttribute("user", new User());

        return "register";
    }

    // Register user
    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, Model model) {

        userRepository.save(user);

        model.addAttribute("message", "Registration successful! Please login.");

        return "login";
    }

    // Login page
    @GetMapping("/login")
    public String loginPage() {

        return "login";
    }

    // Login user
    @PostMapping("/login")
    public String loginUser(
            @ModelAttribute User user,
            HttpSession session,
            Model model) {

        User existingUser =
                userRepository.findByUsername(user.getUsername()).orElse(null);

        if (existingUser != null &&
                existingUser.getPassword().equals(user.getPassword())) {

            session.setAttribute("username", existingUser.getUsername());

            return "redirect:/dashboard";
        }

        model.addAttribute("error", "Invalid username or password");

        return "login";
    }

    // Dashboard
    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {

        String username = (String) session.getAttribute("username");

        if (username == null) {
            return "redirect:/login";
        }

        model.addAttribute("username", username);

        return "dashboard";
    }

    // Logout
    @GetMapping("/logout")
    public String logout(HttpSession session) {

        session.invalidate();

        return "redirect:/login";
    }
}