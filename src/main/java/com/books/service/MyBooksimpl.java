package com.books.service;

import com.books.model.Books;
import com.books.model.Users;
import com.books.repository.BooksRepo;
import java.util.*;

import com.books.repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyBooksimpl implements MyBooks{
    @Autowired
    BooksRepo dao;
    @Autowired
    UsersRepo udao;
    public List<Books> showBooks() {
        return dao.findAll();
    }


    public List<Users> showUsers() {
        return udao.findAll();
    }
}
