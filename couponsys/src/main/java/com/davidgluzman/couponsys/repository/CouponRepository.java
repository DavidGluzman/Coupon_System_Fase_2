package com.davidgluzman.couponsys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.davidgluzman.couponsys.beans.Coupon;

public interface CouponRepository extends JpaRepository<Coupon, Integer> {
	@Transactional
	@Modifying
	@Query(value = "INSERT INTO coupon_system_v2.customer_coupons (customer_id, coupons_id) VALUES (:customerID, :couponID)", nativeQuery = true)
	void addCouponPurchase(int customerID, int couponID);

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM coupon_system_v2.customer_coupons WHERE customer_id=:customerID and coupons_id=:couponID", nativeQuery = true)
	void deleteCouponPurchase(int customerID, int couponID);
}
