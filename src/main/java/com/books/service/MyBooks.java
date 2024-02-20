package com.books.service;
import com.books.model.Books;
import com.books.model.Coupons;
import com.books.model.Users;

import java.util.*;

public interface MyBooks {
    public List<Books> showBooks();
    public List<Users> showUsers();
    public List<Coupons> showCoupons();

    public Optional<Books> getBookByName(String bookname);
    public Optional<Users> getUserByName(String username);

    public Coupons getCouponByNo(int coupon_no);
    public Books addNewBook(Books book);
    public Users addNewUser(Users user);
    public Coupons addNewCoupon(Coupons coupon);
    public Users deleteUserByName(String User);

    public Integer updateCoupon(String user, Integer coupon_no);
    public List<Coupons> selectcoupon(String user);
}
