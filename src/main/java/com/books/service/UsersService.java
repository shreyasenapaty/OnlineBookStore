package com.books.service;

import com.books.model.Users;

import java.util.List;

public interface UsersService {
    public List<Users> showUsers();

    public Users getUserByName(String username);

    public Users addNewUser(Users user);

    public Users deleteUserByName(String User);
}
