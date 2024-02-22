package com.books.controller;

import com.books.model.Books;
import com.books.model.Users;
import com.books.service.MyBooks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
public class BooksController {
    @Autowired
    MyBooks service;
    @Autowired
    UserController users;

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public List<Books> getBooks() {
        return service.showBooks();
    }

    @RequestMapping(value = "/books/{book_name}", method = RequestMethod.GET)
    public Books GetBookbyName(@PathVariable String book_name) throws Exception {
        Optional<Books> book = service.getBookByName(book_name);
        return book.get();
    }

    @RequestMapping(value = "/book/add/{username}", method = RequestMethod.POST)
    public Object AddBook(@PathVariable String username, @RequestBody Books book) throws Exception {
        Users user1 = users.GetUser(username);
        Books oldbook = GetBookbyName(book.getBookname());
        if (Objects.equals(user1.getType(), "admin")) {
            if (oldbook == null) {
                return service.addNewBook(book);
            } else {
                service.updateInventory(oldbook.getInventory() + book.getInventory(), book.getBookname());
                return GetBookbyName(oldbook.getBookname());
            }
        } else {
            return ("You are not admin");
        }
    }


}
