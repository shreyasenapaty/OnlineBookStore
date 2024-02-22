package com.books.service;

import com.books.model.*;
import com.books.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
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

    @Autowired
    CouponsHistoryRepo chdao;
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

    @Override
    public List<CouponHistory> showHistory() {
        return chdao.findAll();
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


    public Integer addNewPurchase(String username, String bookname, Date purchase_date) {
        return pdao.UpdatePurchase(username, bookname, purchase_date);
    }

    @Override
    public Integer addCouponHistory(Integer coupon_no, Double value, Date date) {
        return chdao.UpdateCoupHistory(coupon_no,value,date);
    }


    public Integer updateCoupon(String user, Integer coupon_no) {
        return cdao.UpdateCoupon(user, coupon_no);
    }

    @Override
    public Integer updateStatus(String status, Integer coupon_no) {
        return cdao.UpdateStatus(status, coupon_no);
    }


    public Integer updateInventory(Integer inventory, String bookname) {
        return dao.UpdateInventory(inventory, bookname);
    }


    public List<Coupons> selectcoupon(String user) {
        return cdao.selectCoupon(user);
    }

    @Override
    public List<CouponHistory> selectcouponhistory(int coupon_no) {
        return chdao.selectCouponHistory(coupon_no);
    }


}
