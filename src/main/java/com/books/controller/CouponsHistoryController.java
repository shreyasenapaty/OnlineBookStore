package com.books.controller;

import com.books.model.CouponHistory;
import com.books.service.MyBooks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CouponsHistoryController {
    @Autowired
    MyBooks service;

    @RequestMapping(value="/couponhistory",method= RequestMethod.GET)
    public List<CouponHistory> getUsers(){
        return service.showHistory();
    }

    @RequestMapping(value="/couponhistory/{coupon_no}", method=RequestMethod.GET)
    public List<CouponHistory> GetCouponHistory(@PathVariable int coupon_no) throws Exception {
        return service.selectcouponhistory(coupon_no);
    }

}
