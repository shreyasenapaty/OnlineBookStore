package com.books.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.sql.Date;

@Entity
@Table(name="coupon-history")
public class CouponHistory {
    @Id
    private int c_id;
    @Column
    private int coupon_no;
    @Column
    private Integer p_id;
    @Column
    private float value;
    @Column
    private Date date_used;

    public int getC_id() {
        return c_id;
    }

    public void setC_id(int c_id) {
        this.c_id = c_id;
    }

    public int getCoupon_no() {
        return coupon_no;
    }

    public void setCoupon_no(int coupon_no) {
        this.coupon_no = coupon_no;
    }

    public Integer getP_id() {
        return p_id;
    }

    public void setP_id(Integer p_id) {
        this.p_id = p_id;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public Date getDate_used() {
        return date_used;
    }

    public void setDate_used(Date date_used) {
        this.date_used = date_used;
    }
}
