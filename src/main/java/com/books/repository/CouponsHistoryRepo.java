package com.books.repository;

import com.books.model.CouponHistory;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;

@Repository
public interface CouponsHistoryRepo extends JpaRepository<CouponHistory,Integer> {
    @Transactional
    @Modifying
    @Query(value="insert into coupon_history (coupon_no,value,date_used) values (?,?,?) ", nativeQuery = true)
    int UpdateCoupHistory(Integer coupon_no, Double value, Date date_used);

    @Transactional
    @Modifying
    @Query(value="update coupon_history set p_id= ? where coupon_no= ?", nativeQuery = true)
    int SetPID(Integer p_id,Integer coupon_no);
}
