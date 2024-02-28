package com.books.controller;


import com.books.model.*;
import com.books.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CouponsController {
    @Autowired
    CouponsService couponservice;
    @Autowired
    UsersService userservice;

    @RequestMapping(value = "/coupons", method = RequestMethod.GET)
    public List<Coupons> getCoupons() {
        return couponservice.showCoupons();
    }

    @RequestMapping(value = "/coupons/{username}", method = RequestMethod.GET)
    public List<Coupons> selectCoupon(@PathVariable String username) throws Exception {
        return couponservice.selectcoupon(username);
    }

    @RequestMapping(value = "/coupons/add/{username}", method = RequestMethod.POST)
    public Object addCoupon(@PathVariable String username, @RequestBody Coupons coupon) throws Exception {
        Optional<Users> user = userservice.getUserByName(username);
        Users user1 = user.get();
        if (user1.getType().equals("admin")) {
            coupon.setLeftover_price(coupon.getPrice());
            return couponservice.addNewCoupon(coupon);
        } else {
            return ("You are not admin");
        }

    }

    @RequestMapping(value = "/coupons/transfer/{username}/{couponno}", method = RequestMethod.PUT)
    public Coupons transfer(@PathVariable String username, @PathVariable Integer couponno) throws Exception {
        Optional<Users> user = userservice.getUserByName(username);
        Users user1 = user.get();
        couponservice.updateCoupon(username, couponno);
        return couponservice.getCouponByNo(couponno);
    }
}
