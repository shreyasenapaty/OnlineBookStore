package com.books.service;

import com.books.model.Books;

import java.util.List;
import java.util.Optional;

public interface BooksService {
    public List<Books> showBooks();

    public Optional<Books> getBookByName(String bookname);

    public Books addNewBook(com.books.model.Books book);

    public Integer updateInventory(Integer inventory, String bookname);
}