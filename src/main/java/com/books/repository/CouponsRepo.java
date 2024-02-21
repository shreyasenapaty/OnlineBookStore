package com.books.repository;


import com.books.model.Coupons;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CouponsRepo extends JpaRepository<Coupons,Integer> {

    @Transactional
    @Modifying
    @Query(value="update coupons set username= ? where coupon_no= ?", nativeQuery = true)
    int UpdateCoupon(String username,Integer coupon_no);


    @Query(value = "select * from coupons u where u.username = ? order by expiry_date asc",
            nativeQuery = true)
    List<Coupons> selectCoupon(@Param("username") String username);

    @Transactional
    @Modifying
    @Query(value="update coupons set leftover_price= ? where coupon_no= ?", nativeQuery = true)
    int UpdatePrice(Double leftover_price, int coupon_no);

}
