package com.books.service;

import com.books.model.Books;
import com.books.model.Coupons;
import com.books.model.Users;
import com.books.repository.BooksRepo;
import java.util.*;

import com.books.repository.CouponsRepo;
import com.books.repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyBooksimpl implements MyBooks{
    @Autowired
    BooksRepo dao;
    @Autowired
    UsersRepo udao;
    @Autowired
    CouponsRepo cdao;
    public List<Books> showBooks() {
        return dao.findAll();
    }


    public List<Users> showUsers() {
        return udao.findAll();
    }


    public List<Coupons> showCoupons() {
        return cdao.findAll();
    }


    public Books getBookByNo(int book_no) {
        Optional<Books> book=dao.findById(book_no);
        return book.get();
    }





    public Books addNewBook(Books book) {
        return null;
    }


    public Users addNewUser(Users user) {
        return null;
    }


    public Coupons addNewCoupon(Coupons coupon) {
        return null;
    }


    public Users deleteUserByName(String User) {
        return null;
    }


}
