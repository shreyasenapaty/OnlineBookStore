package com.books.service;
import com.books.model.Books;
import com.books.model.Coupons;
import com.books.model.Users;

import java.util.*;

public interface MyBooks {
    public List<Books> showBooks();
    public List<Users> showUsers();
    public List<Coupons> showCoupons();
}
