package com.books.controller;


import com.books.exceptions.UserNotAdminException;
import com.books.model.Coupons;
import com.books.model.Users;
import com.books.service.CouponsService;
import com.books.service.UsersService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public Coupons addCoupon(@PathVariable String username, @Valid @RequestBody Coupons coupon) throws Exception {
        Users user = userservice.getUserByName(username);
        if (user.getType().equals("admin")) {
            coupon.setLeftover_price(coupon.getPrice());
            return couponservice.addNewCoupon(coupon);
        } else {
            throw new UserNotAdminException("Username does not have admin credentials");
        }

    }

    @RequestMapping(value = "/coupons/transfer/{username}/{couponno}", method = RequestMethod.PUT)
    public Coupons transfer(@PathVariable String username, @PathVariable Integer couponno) throws Exception {
        Users user = userservice.getUserByName(username);
        couponservice.updateCoupon(username, couponno);
        return couponservice.getCouponByNo(couponno);
    }
}
