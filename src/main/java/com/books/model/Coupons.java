package com.books.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.sql.*;


@Entity
@Table(name="coupons")
public class Coupons {
    @Id
    private int coupon_no;

    @Column
    private float price;

    @Column
    private Date expiry_date;

    @Column
    private String username;

    @Column
    private String status;

    @Column
    private Double leftover_price;

    public Double getLeftover_price() {
        return leftover_price;
    }

    public void setLeftover_price(Double leftover_price) {
        this.leftover_price = leftover_price;
    }

    public String getUsername() {
        return username;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getCoupon_no() {
        return coupon_no;
    }

    public void setCoupon_no(int coupon_no) {
        this.coupon_no = coupon_no;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Date getExpiry_date() {
        return expiry_date;
    }

    public void setExpiry_date(Date expiry_date) {
        this.expiry_date = expiry_date;
    }
}
