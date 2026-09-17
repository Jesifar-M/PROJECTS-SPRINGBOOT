package com.example.MobileApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MobileController {

    @Autowired
    private MobileRepository mobileRepository;

    @GetMapping("/mobile")
    public String showForm(Model model) {

        model.addAttribute("mobile", new MobileModel());

        return "mobile";
    }

    @PostMapping("/save-mobile")
    public String saveMobile(@ModelAttribute MobileModel mobile) {

        mobileRepository.save(mobile);

        return "redirect:/mobiles";
    }

    @GetMapping("/mobiles")
    public String showMobiles(Model model) {

        model.addAttribute("namePrices",
                mobileRepository.findNameAndPrice());

        model.addAttribute("below20000",
                mobileRepository.findBelow20000());

        model.addAttribute("typeCount",
                mobileRepository.countByType());

        return "mobiles";
    }
}