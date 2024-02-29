package com.books.controller;

import com.books.exceptions.UserNotAdminException;
import com.books.model.*;
import com.books.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class PurchaseController {
    @Autowired
    PurchaseService purchaseservice;
    @Autowired
    CouponsService couponservice;
    @Autowired
    BooksService bookservice;

    @RequestMapping(value = "/purchases", method = RequestMethod.GET)
    public List<Purchase> getPurchases() {
        return purchaseservice.showPurchase();
    }

    @RequestMapping(value = "/purchase/{username}/{bookname}", method = RequestMethod.GET)
    public Object buyBook(@PathVariable String username, @PathVariable String bookname) throws Exception {
        Books book = bookservice.getBookByName(bookname);
        List<Coupons> coup = couponservice.selectcoupon(username);
        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        System.out.println(date);
        if (book.getInventory() == 0) {
            throw new UserNotAdminException("Book is out of stock");
        } else {
            Double price = book.getPrice();
            int len = coup.size();
            Double value = 0.00;
            Double leftover_price = 0.00;
            for (int i = 0; i < len; i++) {
                if (date.before(coup.get(i).getExpiry_date())) {
                    couponservice.updateStatus("active", coup.get(i).getCoupon_no());
                    value += coup.get(i).getLeftover_price();
                } else {
                    couponservice.updateStatus("expired", coup.get(i).getCoupon_no());
                }
                System.out.println(value);
            }

            if (value < price) {
                throw new UserNotAdminException("Not enough coupons");
            } else {
                purchaseservice.addNewPurchase(username, bookname, date);
                Integer inventory = book.getInventory() - 1;
                bookservice.updateInventory(inventory, bookname);
                int i = 0;
                do {
                    if (price < coup.get(i).getLeftover_price()) {
                        leftover_price = coup.get(i).getLeftover_price() - price;
                        price = 0.00;
                    } else if (price > coup.get(i).getLeftover_price()) {
                        price = price - coup.get(i).getLeftover_price();
                        leftover_price = 0.00;
                    }
                    couponservice.updatePrice(leftover_price, coup.get(i).getCoupon_no());
                    couponservice.addCouponHistory(coup.get(i).getCoupon_no(), coup.get(i).getPrice() - leftover_price, date);
                    i++;
                } while (price > 0);

                return ("Book has been purchased");
            }
        }
    }
}
