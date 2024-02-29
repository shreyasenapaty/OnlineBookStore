package com.books.controller;

import com.books.exceptions.UserNotAdminException;
import com.books.model.Books;
import com.books.model.Users;
import com.books.service.BooksService;
import com.books.service.UsersService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BooksController {
    @Autowired
    BooksService bookservice;
    @Autowired
    UsersService userservice;

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public List<Books> getBooks() {
        return bookservice.showBooks();
    }

    @RequestMapping(value = "/books/{book_name}", method = RequestMethod.GET)
    public Books getBookbyName(@PathVariable String book_name) throws Exception {
        return bookservice.getBookByName(book_name);
    }

    @RequestMapping(value = "/books/add/{username}", method = RequestMethod.POST)
    public Books addBook(@PathVariable String username, @Valid @RequestBody Books book) throws Exception {
        Users user = userservice.getUserByName(username);
        Books oldbook = getBookbyName(book.getBookname());
        if (user.getType().equals("admin")) {
            if (oldbook == null) {
                return bookservice.addNewBook(book);
            } else {
                bookservice.updateInventory(oldbook.getInventory() + book.getInventory(), book.getBookname());
                return getBookbyName(oldbook.getBookname());
            }
        } else {
            throw new UserNotAdminException("Username does not have admin credentials");
        }
    }


}
