package com.books.controller;

import com.books.model.Users;
import com.books.service.MyBooks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    MyBooks service;

    @RequestMapping(value="/users",method= RequestMethod.GET)
    public List<Users> getUsers(){
        return service.showUsers();
    }

    @RequestMapping(value="/{username}", method=RequestMethod.GET)
    public Users GetUser(@PathVariable String username) throws Exception {
        Optional<Users> user = service.getUserByName(username);
        return user.get();
    }

}
