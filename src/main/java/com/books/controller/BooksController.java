package com.books.controller;

import com.books.model.Books;
import com.books.model.Coupons;
import com.books.model.Purchase;
import com.books.model.Users;
import com.books.service.MyBooks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
public class BooksController {
    @Autowired
    MyBooks service;
    @RequestMapping(value="/hi",method= RequestMethod.GET)
    public String Hello(){
        return ("Hello");
    }

    @RequestMapping(value="/books",method= RequestMethod.GET)
    public List<Books> getBooks(){
        return service.showBooks();
    }
    @RequestMapping(value="/users",method= RequestMethod.GET)
    public List<Users> getUsers(){
        return service.showUsers();
    }
    @RequestMapping(value="/coupons",method= RequestMethod.GET)
    public List<Coupons> getCoupons(){
        return service.showCoupons();
    }
    @RequestMapping(value="/purchases",method= RequestMethod.GET)
    public List<Purchase> getPurchases(){
        return service.showPurchase();
    }

    @RequestMapping(value="/books/{book_name}", method=RequestMethod.GET)
    public Books GetBookbyName(@PathVariable String book_name) throws Exception{
        Optional<Books> book= service.getBookByName(book_name);
        return book.get();
    }

    @RequestMapping(value="/{username}", method=RequestMethod.GET)
    public Users GetUser(@PathVariable String username) throws Exception {
        Optional<Users> user = service.getUserByName(username);
        return user.get();
    }

    @RequestMapping(value="/{username}/add/coupon", method=RequestMethod.POST)
    public Object AddCoupon(@PathVariable String username, @RequestBody Coupons coupon) throws Exception{
        Users user1=GetUser(username);
        if (Objects.equals(user1.getType(), "admin")){
            return service.addNewCoupon(coupon);
        }
        else {
            return("You are not admin");
        }

    }

    @RequestMapping(value="/{username}/add/book", method=RequestMethod.POST)
    public Object AddBook(@PathVariable String username, @RequestBody Books book) throws Exception {
        Users user1 = GetUser(username);
        if (Objects.equals(user1.getType(), "admin")) {
            return service.addNewBook(book);
        } else {
            return ("You are not admin");
        }
    }
    @RequestMapping(value="/{username}/transfer/{couponno}", method=RequestMethod.PUT)
    public Object Transfer(@PathVariable String username, @PathVariable Integer couponno) throws Exception {
        if (GetUser(username)==null){
            return ("This user doesn't exist");
        }
        else{return service.updateCoupon(username, couponno);}
    }

    @RequestMapping(value="/coupon/{username}", method=RequestMethod.GET)
    public List<Coupons> SelectCoupon(@PathVariable String username) throws Exception {
        return service.selectcoupon(username);
    }

    @RequestMapping(value="/buy/{username}/{bookname}", method=RequestMethod.GET)
    public Object BuyBook(@PathVariable String username, @PathVariable String bookname) throws Exception {
        Optional<Users> user=service.getUserByName(username);
        Books book= GetBookbyName(bookname);
        List<Coupons> coup= SelectCoupon(username);
        String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        if (book.getInventory()==0){
            return ("Book is out of stock");
        }
        else{
            service.addNewPurchase(username, bookname);
            Integer inventory= book.getInventory()-1;
            service.updateInventory(inventory,bookname);
            Double price= book.getPrice();
            int len= coup.size();
            Double value= 0.00;
            Double leftover_price=0.00;
            for(int i=0; i<len; i++) {
                 value += coup.get(i).getLeftover_price();
            }
            if (value>price){
                while(price>0){
                    int i=0;
                    if (price<coup.get(i).getPrice()){
                        leftover_price= coup.get(i).getLeftover_price()- price;
                        price=0.00;
                    }
                    else{
                        price= price - coup.get(i).getLeftover_price();
                        leftover_price= 0.00;
                    }
                    service.updatePrice(leftover_price, coup.get(i).getCoupon_no());

                }
                return null;
            }
            else{
                return ("Not enough coupons");
            }
        }


    }

}
