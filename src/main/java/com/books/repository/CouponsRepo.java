package com.books.repository;


import com.books.model.Coupons;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CouponsRepo extends JpaRepository<Coupons,Integer> {

    @Query(value = "select * from coupons u where u.username = ?",
            nativeQuery = true)
    List<Coupons> selectCoupon(@Param("username") String username);
}
