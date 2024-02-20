package com.books.repository;

import com.books.model.CouponHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponsHistoryRepo extends JpaRepository<CouponHistory,Integer> {
}
