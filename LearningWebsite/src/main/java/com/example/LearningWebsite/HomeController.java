package com.example.LearningWebsite;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home(Model model) {

        String welcomeMessage = "Learning is a journey, not a destination.";

        String headingMessage = "<h1>Hello from Spring Boot!</h1>";

        boolean loginStatus = true;

        model.addAttribute("welcomeMessage", welcomeMessage);
        model.addAttribute("headingMessage", headingMessage);
        model.addAttribute("loginStatus", loginStatus);

        return "home";
    }
}