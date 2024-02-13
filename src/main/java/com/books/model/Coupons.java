package com.books.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="coupons")
public class Coupons {
    @Id
    private int book_no;

    @Column
    private String bookname;

    @Column
    private float price;




}
