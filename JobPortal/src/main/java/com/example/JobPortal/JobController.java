package com.example.JobPortal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class JobController {

    @Autowired
    private JobRepository repository;

    @GetMapping("/")
    public String showForm(Model model) {
        model.addAttribute("job", new JobModel());
        return "job";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("job") JobModel job, Model model) {
        repository.save(job);

        model.addAttribute("fullName", job.getFullName());

        return "success";
    }
}