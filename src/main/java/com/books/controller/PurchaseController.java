package com.books.controller;

import com.books.model.Coupons;
import com.books.model.Purchase;
import com.books.service.BooksService;
import com.books.service.CouponsService;
import com.books.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

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
        Optional<com.books.model.Books> opbook = bookservice.getBookByName(bookname);
        com.books.model.Books book = opbook.get();
        List<Coupons> coup = couponservice.selectcoupon(username);
        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        System.out.println(date);
        if (book.getInventory() == 0) {
            return ("Book is out of stock");
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
                return ("Not enough coupons");
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
