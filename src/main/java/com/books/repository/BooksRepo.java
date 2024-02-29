package com.books.repository;

import com.books.model.Books;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BooksRepo extends JpaRepository<Books, String> {
    @Transactional
    @Modifying
    @Query(value = "update books set inventory= ? where bookname= ?", nativeQuery = true)
    int UpdateInventory(Integer inventory, String bookname);

    @Query(value = "select * from books u where u.bookname = ? ",
            nativeQuery = true)
    Books BookbyName(String bookname);


}
