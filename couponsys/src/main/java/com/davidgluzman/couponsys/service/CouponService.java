package com.davidgluzman.couponsys.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.davidgluzman.couponsys.beans.Coupon;
import com.davidgluzman.couponsys.repository.CouponRepository;

@Service
public class CouponService {
	@Autowired
	private CouponRepository couponRepository;

	public void addCoupon(Coupon coupon) {
		couponRepository.save(coupon);
	}

	public void updateCoupon(Coupon coupon) {
		couponRepository.saveAndFlush(coupon);
	}

	public void deleteCoupon(int couponId) {
		couponRepository.deleteById(couponId);
	}

	public Optional<Coupon> getOneCoupon(int couponId) {
		return couponRepository.findById(couponId);
	}

	public List<Coupon> getAllCoupons() {
		return couponRepository.findAll();
	}
	public void addCouponPurchase(int customerID,int couponID) {
		couponRepository.addCouponPurchase(customerID, couponID);
	}
	public void deleteCouponPurchase(int customerID,int couponID) {
		couponRepository.deleteCouponPurchase(customerID, couponID);
	}
		
}
