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
    @RequestMapping(value="/hi",method= RequestMethod.GET)
    public String Hello(){
        return ("Hello");
    }

    @RequestMapping(value="/books",method= RequestMethod.GET)
    public List<Books> getBooks(){
        return service.showBooks();
    }

    @RequestMapping(value="/books/{book_name}", method=RequestMethod.GET)
    public Books GetBookbyName(@PathVariable String book_name) throws Exception{
        Optional<Books> book= service.getBookByName(book_name);
        return book.get();
    }

    @RequestMapping(value="/{username}/add/book", method=RequestMethod.POST)
    public Object AddBook(@PathVariable String username, @RequestBody Books book) throws Exception {
        Users user1 = users.GetUser(username);
        if (Objects.equals(user1.getType(), "admin")) {
            return service.addNewBook(book);
        } else {
            return ("You are not admin");
        }
    }






}
