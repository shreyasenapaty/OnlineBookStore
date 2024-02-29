package com.books.service;

import com.books.model.Purchase;
import com.books.repository.PurchaseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class Purchaseimpl implements PurchaseService {
    @Autowired
    PurchaseRepo pdao;

    public Integer addNewPurchase(String username, String bookname, Date purchase_date) {
        return pdao.UpdatePurchase(username, bookname, purchase_date);
    }

    public List<Purchase> showPurchase() {
        return pdao.findAll();
    }

}
