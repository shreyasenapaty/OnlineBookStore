package com.books.service;

import com.books.model.CouponHistory;
import com.books.model.Coupons;

import java.sql.Date;
import java.util.List;

public interface CouponsService {
    public List<Coupons> showCoupons();

    List<CouponHistory> showHistory();

    public Coupons getCouponByNo(int coupon_no);

    public Coupons addNewCoupon(Coupons coupon);

    public Integer addCouponHistory(Integer coupon_no, Double value, Date date);

    public Integer updatePrice(Double leftover_price, Integer coupon_no);

    public Integer updateCoupon(String user, Integer coupon_no);

    public Integer updateStatus(String status, Integer coupon_no);

    public List<Coupons> selectcoupon(String user);

    public List<CouponHistory> selectcouponhistory(int coupon_no);
}
