package com.example.GroceryStoreApp;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController {

    @GetMapping("/single-product")
    public String singleProduct(Model model) {

        Product product = new Product(101, "Sugar", 55.5);

        model.addAttribute("product", product);

        return "single-product";
    }

    @GetMapping("/product-list")
    public String productList(Model model) {

        List<Product> products = Arrays.asList(
            new Product(101, "Sugar", 55.5),
            new Product(102, "Salt", 20.0),
            new Product(103, "Wheat Flour", 38.75)
        );

        model.addAttribute("products", products);

        return "product-list";
    }
}