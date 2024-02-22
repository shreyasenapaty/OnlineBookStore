package com.books.controller;

import com.books.model.Books;
import com.books.model.Coupons;
import com.books.model.Purchase;
import com.books.service.MyBooks;
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
    MyBooks service;

    @RequestMapping(value="/purchases",method= RequestMethod.GET)
    public List<Purchase> getPurchases(){
        return service.showPurchase();
    }

    @RequestMapping(value="/purchase/{username}/{bookname}", method= RequestMethod.GET)
    public Object BuyBook(@PathVariable String username, @PathVariable String bookname) throws Exception {
        Optional<Books> opbook = service.getBookByName(bookname);
        Books book = opbook.get();
        List<Coupons> coup = service.selectcoupon(username);
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
                    service.updateStatus("active", coup.get(i).getCoupon_no());
                    value += coup.get(i).getLeftover_price();
                } else {
                    service.updateStatus("expired", coup.get(i).getCoupon_no());
                }
                System.out.println(value);
            }

            if (value < price) {
                return ("Not enough coupons");
            } else {
                service.addNewPurchase(username, bookname, date);
                Integer inventory = book.getInventory() - 1;
                service.updateInventory(inventory, bookname);
                int i = 0;
                do {
                    if (price < coup.get(i).getLeftover_price()) {
                        leftover_price = coup.get(i).getLeftover_price() - price;
                        price = 0.00;
                    } else if (price > coup.get(i).getLeftover_price()) {
                        price = price - coup.get(i).getLeftover_price();
                        leftover_price = 0.00;
                    }
                    service.updatePrice(leftover_price, coup.get(i).getCoupon_no());
                    service.addCouponHistory(coup.get(i).getCoupon_no(), coup.get(i).getPrice()-leftover_price, date);
                    i++;
                } while (price > 0);

                return ("Book has been purchased");

            }
        }


    }
}
