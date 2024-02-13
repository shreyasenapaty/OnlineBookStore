package com.books.repository;



import com.books.model.Coupons;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponsRepo extends JpaRepository<Coupons,Integer> {
}
