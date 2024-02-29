package com.books.service;

import com.books.model.Books;
import com.books.repository.BooksRepo;
import com.books.repository.PurchaseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Booksimpl implements BooksService {
    @Autowired
    BooksRepo dao;
    @Autowired
    PurchaseRepo pdao;

    public List<com.books.model.Books> showBooks() {
        return dao.findAll();
    }


    public Books getBookByName(String bookname) {
        return dao.BookbyName(bookname);
    }

    public com.books.model.Books addNewBook(com.books.model.Books book) {
        return dao.save(book);
    }


    public Integer updateInventory(Integer inventory, String bookname) {
        return dao.UpdateInventory(inventory, bookname);
    }
}
