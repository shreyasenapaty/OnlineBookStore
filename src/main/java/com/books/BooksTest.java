package com.books;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication

public class BooksTest {
    public static void main(String[] args) {
        SpringApplication.run(BooksTest.class, args);


    }
}
