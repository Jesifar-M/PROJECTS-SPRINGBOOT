package com.example.BookApp;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookModel, Long> {

}