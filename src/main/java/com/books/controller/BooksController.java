package com.books.controller;

import com.books.model.Books;
import com.books.model.Users;
import com.books.service.MyBooks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

@RestController
public class BooksController {
    @Autowired
    MyBooks service;
    @RequestMapping(value="/hi",method= RequestMethod.GET)
    public String Hello(){
        return ("Hello");
    }

    @RequestMapping(value="/books",method= RequestMethod.GET)
    public List<Books> getBooks(){
        return service.showBooks();
    }
    @RequestMapping(value="/users",method= RequestMethod.GET)
    public List<Users> getUsers(){
        return service.showUsers();
    }

}
