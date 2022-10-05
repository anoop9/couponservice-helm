package com.infor.sra.couponservice.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infor.sra.couponservice.model.Coupon;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponRepo extends JpaRepository<Coupon, Long> {

	Coupon findByCode(String code);

}
