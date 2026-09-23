package com.example.bookstore;

import com.example.bookstore.model.Book;
import com.example.bookstore.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.time.LocalDate;

@SpringBootApplication
public class BookstoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookstoreApplication.class, args);
    }

    // 🟢 This will automatically insert sample books on startup
    @Bean
    public CommandLineRunner loadData(BookRepository repository) {
        return args -> {
            repository.save(new Book(null, "Spring in Action", "Craig Walls", "Programming", 599.00, LocalDate.parse("2023-05-10")));
            repository.save(new Book(null, "Java Beginners Guide", "Herbert Schildt", "Programming", 450.00, LocalDate.parse("2022-03-15")));
            System.out.println("🟢 Sample books inserted successfully!");
        };
    }
}
