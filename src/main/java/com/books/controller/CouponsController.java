package com.books.controller;

import com.books.model.Coupons;
import com.books.model.Users;
import com.books.service.MyBooks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
public class CouponsController {
    @Autowired
    MyBooks service;

    @Autowired
    UserController users;

    @RequestMapping(value="/coupons",method= RequestMethod.GET)
    public List<Coupons> getCoupons(){
        return service.showCoupons();
    }

    @RequestMapping(value="/coupon/{username}", method=RequestMethod.GET)
    public List<Coupons> SelectCoupon(@PathVariable String username) throws Exception {
        return service.selectcoupon(username);
    }

    @RequestMapping(value="/{username}/add/coupon", method=RequestMethod.POST)
    public Object AddCoupon(@PathVariable String username, @RequestBody Coupons coupon) throws Exception{
        Users user1=users.GetUser(username);
        if (Objects.equals(user1.getType(), "admin")){
            return service.addNewCoupon(coupon);
        }
        else {
            return("You are not admin");
        }

    }

    @RequestMapping(value="/{username}/transfer/{couponno}", method=RequestMethod.PUT)
    public Object Transfer(@PathVariable String username, @PathVariable Integer couponno) throws Exception {
        if (users.GetUser(username)==null){
            return ("This user doesn't exist");
        }
        else{return service.updateCoupon(username, couponno);}
    }
}
