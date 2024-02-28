package com.books.service;

import com.books.model.Users;

import java.util.List;
import java.util.Optional;

public interface UsersService {
    public List<Users> showUsers();

    public Optional<Users> getUserByName(String username);

    public Users addNewUser(Users user);

    public Users deleteUserByName(String User);
}
