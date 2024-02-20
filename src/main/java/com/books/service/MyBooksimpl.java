package com.books.service;

import com.books.model.Books;
import com.books.model.Coupons;
import com.books.model.Users;
import com.books.repository.BooksRepo;
import com.books.repository.CouponsRepo;
import com.books.repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<Users> getUserByName(String username) {
        return udao.findById(username);
    }


    public Coupons getCouponByNo(int coupon_no) {
        Optional<Coupons> coupon=cdao.findById(coupon_no);

        return coupon.get();
    }


    public Books addNewBook(Books book) {
        return dao.save(book);
    }


    public Users addNewUser(Users user) {
        return udao.save(user);
    }


    public Coupons addNewCoupon(Coupons coupon) {
        return cdao.save(coupon);
    }


    public Users deleteUserByName(String User) {
        return null;
    }


    public Integer updateCoupon(String user, int coupon_no) {
        return null;
    }

    @Override
    public List<Coupons> selectcoupon(String user) {
        return cdao.selectCoupon(user);
    }


}
