package com.example.trainingregistration.controller;

import com.example.trainingregistration.model.Registration;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("registration", new Registration());
        return "register";
    }

    @PostMapping("/register")
    public String submit(
            @Valid Registration registration,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            return "register";
        }

        model.addAttribute("registration", registration);
        return "success";
    }
}