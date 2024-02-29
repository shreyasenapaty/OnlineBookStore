package com.books.service;

import com.books.model.Users;
import com.books.repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Usersimpl implements UsersService {
    @Autowired
    UsersRepo udao;

    public Users addNewUser(Users user) {
        return udao.save(user);
    }

    public List<Users> showUsers() {
        return udao.findAll();
    }

    public Users getUserByName(String username) {
        Optional<Users> user = udao.findById(username);
        return user.get();
    }

    public Users deleteUserByName(String User) {
        return null;
    }


}
