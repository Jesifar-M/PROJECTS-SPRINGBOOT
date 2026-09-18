package com.restaurant.dishdisplay;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class DishController {

    @GetMapping("/dish/{name}/{price}")
    public String showDishDetails(
            @PathVariable("name") String name, 
            @PathVariable("price") Double price, 
            Model model) {
        
        // Pass values securely onto the Thymeleaf template page
        model.addAttribute("dishName", name);
        model.addAttribute("dishPrice", price);
        
        // Maps directly to dish.html template file
        return "dish"; 
    }
}
