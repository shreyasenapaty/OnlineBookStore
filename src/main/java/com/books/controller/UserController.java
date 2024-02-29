package com.books.controller;

import com.books.model.Users;
import com.books.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UsersService userservice;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<Users> getUsers() {
        return userservice.showUsers();
    }

    @RequestMapping(value = "/users/{username}", method = RequestMethod.GET)
    public Users getUser(@PathVariable String username) throws Exception {
        return userservice.getUserByName(username);
    }

}
