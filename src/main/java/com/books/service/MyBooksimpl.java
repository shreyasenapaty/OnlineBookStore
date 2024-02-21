package com.books.service;

import com.books.model.Books;
import com.books.model.Coupons;
import com.books.model.Purchase;
import com.books.model.Users;
import com.books.repository.BooksRepo;
import com.books.repository.CouponsRepo;
import com.books.repository.PurchaseRepo;
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

    @Autowired
    PurchaseRepo pdao;
    public List<Books> showBooks() {
        return dao.findAll();
    }


    public List<Users> showUsers() {
        return udao.findAll();
    }


    public List<Coupons> showCoupons() {
        return cdao.findAll();
    }

    @Override
    public List<Purchase> showPurchase() {
        return pdao.findAll();
    }


    public Optional<Books> getBookByName(String bookname) {
        return dao.findById(bookname);
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


    public Integer updatePrice(Double leftover_price, Integer coupon_no) {
        return cdao.UpdatePrice(leftover_price, coupon_no);
    }

    @Override
    public Integer addNewPurchase(String username, String bookname) {
        return pdao.UpdatePurchase(username, bookname);
    }


    public Integer updateCoupon(String user, Integer coupon_no) {
        return cdao.UpdateCoupon(user, coupon_no);
    }

    @Override
    public Integer updateInventory(Integer inventory, String bookname) {
        return dao.UpdateInventory(inventory, bookname);
    }


    public List<Coupons> selectcoupon(String user) {
        return cdao.selectCoupon(user);
    }


}
