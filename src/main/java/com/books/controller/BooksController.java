package com.books.controller;

import com.books.model.Books;
import com.books.model.Coupons;
import com.books.model.Users;
import com.books.service.MyBooks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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

    @RequestMapping(value="/books/{book_no}", method=RequestMethod.GET)
    public Books GetBookbyBookNo(@PathVariable int book_no) throws Exception{
        Books book= service.getBookByNo(book_no);
        return book;
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
    public Object AddBook(@PathVariable String username, @RequestBody Books book) throws Exception{
        Users user1=GetUser(username);
        if (Objects.equals(user1.getType(), "admin")){
            return service.addNewBook(book);
        }
        else {
            return("You are not admin");
        }




}
