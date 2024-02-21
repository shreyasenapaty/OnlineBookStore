package com.books.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="books")
public class Books {
    @Id
    private String bookname;

    @Column
    private float price;

    @Column
    private Integer inventory;

    @Column
    private String authorname;

    public double getPrice() {
        return price;
    }

    public Integer getInventory() {
        return inventory;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }

    public void setPrice(float price) {
        this.price = price;
    }



    public String getBookname() {
        return bookname;
    }

    public String getAuthorname() {
        return authorname;
    }

    public void setAuthorname(String authorname) {
        this.authorname = authorname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }


}
