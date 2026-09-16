package com.example.BookApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/book")
    public String showBookForm(Model model) {
        model.addAttribute("book", new BookModel());
        return "book";
    }

    @PostMapping("/save-book")
    public String saveBook(@ModelAttribute BookModel book) {
        bookRepository.save(book);
        return "redirect:/books";
    }

    @GetMapping("/books")
    public String showBooks(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "books";
    }
}