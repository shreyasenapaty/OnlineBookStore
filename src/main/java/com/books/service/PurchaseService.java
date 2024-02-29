package com.books.service;

import com.books.model.Purchase;

import java.sql.Date;
import java.util.List;

public interface PurchaseService {

    List<Purchase> showPurchase();

    public Integer addNewPurchase(String username, String bookname, Date purchase_date);

    Purchase findPurchase(String username, String bookname, Date purchase_date);
}
