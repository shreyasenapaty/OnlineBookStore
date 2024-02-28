package com.books.service;

import com.books.model.*;
import com.books.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.*;

@Service
public class Couponsimpl implements CouponsService {

    @Autowired
    CouponsRepo cdao;

    @Autowired
    CouponsHistoryRepo chdao;

    public List<Coupons> showCoupons() {
        return cdao.findAll();
    }

    public List<CouponHistory> showHistory() {
        return chdao.findAll();
    }

    public Coupons getCouponByNo(int coupon_no) {
        Optional<Coupons> coupon = cdao.findById(coupon_no);
        return coupon.get();
    }

    public Coupons addNewCoupon(Coupons coupon) {
        return cdao.save(coupon);
    }

    public Integer updatePrice(Double leftover_price, Integer coupon_no) {
        return cdao.UpdatePrice(leftover_price, coupon_no);
    }

    public Integer addCouponHistory(Integer coupon_no, Double value, Date date) {
        return chdao.UpdateCoupHistory(coupon_no, value, date);
    }

    public Integer updateCoupon(String user, Integer coupon_no) {
        return cdao.UpdateCoupon(user, coupon_no);
    }

    public Integer updateStatus(String status, Integer coupon_no) {
        return cdao.UpdateStatus(status, coupon_no);
    }

    public List<Coupons> selectcoupon(String user) {
        return cdao.selectCoupon(user);
    }

    public List<CouponHistory> selectcouponhistory(int coupon_no) {
        return chdao.selectCouponHistory(coupon_no);
    }
}
