package com.example.LibraryMembership;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LibraryController {

    @Autowired
    private LibraryRepository repository;

    @GetMapping("/")
    public String showForm(Model model) {
        model.addAttribute("library", new LibraryModel());
        return "library";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("library") LibraryModel library, Model model) {

        repository.save(library);

        model.addAttribute("name", library.getName());

        return "success";
    }
}