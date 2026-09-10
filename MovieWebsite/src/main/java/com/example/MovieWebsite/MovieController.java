package com.example.MovieWebsite;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MovieController {

    @GetMapping("/movie")
    public String movie(Model model) {

        String movieTitle = "Inception";

        String movieDescription =
                "A skilled thief enters the dreams of others.\n"
              + "He is given a chance to erase his criminal past.\n"
              + "But the mission becomes very dangerous.";

        String firstLine =
                "A skilled thief enters the dreams of others.";

        boolean loginStatus = true;

        model.addAttribute("movieTitle", movieTitle);
        model.addAttribute("movieDescription", movieDescription);
        model.addAttribute("firstLine", firstLine);
        model.addAttribute("loginStatus", loginStatus);

        return "movie";
    }
}