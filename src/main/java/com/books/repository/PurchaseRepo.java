package com.books.repository;

import com.books.model.Purchase;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepo extends JpaRepository<Purchase,Integer> {
    @Transactional
    @Modifying
    @Query(value="insert into purchase (username,bookname) values (?,?) ", nativeQuery = true)
    int UpdatePurchase(String username, String bookname);
}
