package com.books.repository;

import com.books.model.Purchase;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface PurchaseRepo extends JpaRepository<Purchase, Integer> {
    @Transactional
    @Modifying
    @Query(value = "insert into purchase (username,bookname, purchase_date) values (?,?,?) ", nativeQuery = true)
    int UpdatePurchase(String username, String bookname, Date purchase_date);

    @Query(value = "select * from purchase u where u.username = ? AND u.bookname = ? AND u.purchase_date=? order by purchase_date desc",
            nativeQuery = true)
    List<Purchase> FindPurchase(String username, String bookname, Date date);
}
