package com.example.StudentInfoApp;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StudentController {

    @GetMapping("/student-info")
    public String studentInfo(Model model) {

        Student student = new Student(101, "Anjali Sharma", 92.5);

        model.addAttribute("student", student);

        return "student-info";
    }

    @GetMapping("/student-list")
    public String studentList(Model model) {

        List<Student> students = Arrays.asList(
            new Student(101, "Anjali Sharma", 92.5),
            new Student(102, "Rohit Mehta", 85.0),
            new Student(103, "Sneha Iyer", 78.6)
        );

        model.addAttribute("students", students);

        return "student-list";
    }
}