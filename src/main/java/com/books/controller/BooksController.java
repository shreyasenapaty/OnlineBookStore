package com.books.controller;

import com.books.model.*;
import com.books.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BooksController {
    @Autowired
    BooksService bookservice;
    @Autowired
    UsersService userservice;

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public List<com.books.model.Books> getBooks() {
        return bookservice.showBooks();
    }

    @RequestMapping(value = "/books/{book_name}", method = RequestMethod.GET)
    public com.books.model.Books getBookbyName(@PathVariable String book_name) throws Exception {
        Optional<com.books.model.Books> book = bookservice.getBookByName(book_name);
        return book.get();
    }

    @RequestMapping(value = "/book/add/{username}", method = RequestMethod.POST)
    public Object addBook(@PathVariable String username, @RequestBody com.books.model.Books book) throws Exception {
        Optional<Users> user = userservice.getUserByName(username);
        Users user1 = user.get();
        Books oldbook = getBookbyName(book.getBookname());
        if (user1.getType().equals("admin")) {
            if (oldbook == null) {
                return bookservice.addNewBook(book);
            } else {
                bookservice.updateInventory(oldbook.getInventory() + book.getInventory(), book.getBookname());
                return getBookbyName(oldbook.getBookname());
            }
        } else {
            return ("You are not admin");
        }
    }


}
