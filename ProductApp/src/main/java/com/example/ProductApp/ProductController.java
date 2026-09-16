package com.example.ProductApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/product")
    public String showProductForm(Model model) {

        model.addAttribute("product", new Product());

        return "product";
    }

    @PostMapping("/save-product")
    public String saveProduct(@ModelAttribute Product product) {

        productRepository.save(product);

        return "redirect:/products";
    }

    @GetMapping("/products")
    public String showProducts(Model model) {

        model.addAttribute("products", productRepository.findAll());

        return "products";
    }
}