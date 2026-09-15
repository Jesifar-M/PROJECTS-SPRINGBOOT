package com.example.UserFormApp.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @GetMapping("/")
    public String form() {
        return "form";
    }

    @GetMapping("/result")
    public String result(@RequestParam String username, Model model) {

        model.addAttribute("username", username);
        model.addAttribute("formData", "username=" + username);

        return "result";
    }
}