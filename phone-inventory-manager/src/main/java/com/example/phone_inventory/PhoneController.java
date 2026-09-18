package com.example.phone_inventory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PhoneController {

    @Autowired
    private PhoneRepository phoneRepository;

    @GetMapping("/")
    public String listPhones(Model model) {
        model.addAttribute("phones", phoneRepository.findAll());
        model.addAttribute("phone", new Phone()); // Passes the empty form object
        return "inventory"; 
    }

    @PostMapping("/save")
    public String savePhone(@ModelAttribute("phone") Phone phone) {
        phoneRepository.save(phone);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Phone existingPhone = phoneRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid phone Id:" + id));
        model.addAttribute("phone", existingPhone);
        model.addAttribute("phones", phoneRepository.findAll());
        return "inventory"; 
    }

    @GetMapping("/delete/{id}")
    public String deletePhone(@PathVariable("id") Long id) {
        phoneRepository.deleteById(id);
        return "redirect:/";
    }
}
