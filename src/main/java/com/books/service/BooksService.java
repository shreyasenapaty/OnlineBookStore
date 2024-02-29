package com.books.service;

import com.books.model.Books;

import java.util.List;

public interface BooksService {
    public List<Books> showBooks();

    public Books getBookByName(String bookname);

    public Books addNewBook(Books book);

    public Integer updateInventory(Integer inventory, String bookname);
}