package com.books.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BooksController {
    @RequestMapping(value="/hi",method= RequestMethod.GET)
    public String Hello(){
        return ("Hello");
    }

}
