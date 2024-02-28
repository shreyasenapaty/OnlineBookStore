package com.books.service;

import com.books.model.Users;
import com.books.repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class Usersimpl implements UsersService {
    @Autowired
    UsersRepo udao;

    public Users addNewUser(Users user) {
        return udao.save(user);
    }

    public List<Users> showUsers() {
        return udao.findAll();
    }

    public Optional<Users> getUserByName(String username) {
        return udao.findById(username);
    }

    public Users deleteUserByName(String User) {
        return null;
    }


}
