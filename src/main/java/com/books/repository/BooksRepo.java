package com.books.repository;

import com.books.model.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BooksRepo extends JpaRepository<Books,Integer> {
}
