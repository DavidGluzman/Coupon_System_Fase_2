package com.davidgluzman.couponsys.DBDAO;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.davidgluzman.couponsys.beans.Category;
import com.davidgluzman.couponsys.beans.Coupon;
import com.davidgluzman.couponsys.repository.CouponRepository;

@Service
public class CouponDBDAO {
	@Autowired
	private CouponRepository couponRepository;

	public void addCoupon(Coupon coupon) {
		couponRepository.save(coupon);
		connectCoupon(coupon);
	}
	public void connectCoupon(Coupon coupon) {
		couponRepository.connectCoupon(coupon.getCompanyID(), coupon.getId());;
	}
	

	public void updateCoupon(Coupon coupon) {
		couponRepository.saveAndFlush(coupon);
	}

	public void deleteCoupon(int couponId) {
		couponRepository.deleteById(couponId);
	}

	public Coupon getOneCoupon(int couponId) {
		return couponRepository.findById(couponId).get();
	}

	public List<Coupon> getAllCoupons() {
		return couponRepository.findAll();

	}
	public List<Coupon> getAllCouponsByCompanyID(int companyID){
		return couponRepository.findByCompanyID(companyID);
	
	}

	public void addCouponPurchase(int customerID, int couponID) {
		couponRepository.addCouponPurchase(customerID, couponID);
	}

	public void deleteCouponPurchase(int customerID, int couponID) {
		couponRepository.deleteCouponPurchase(customerID, couponID);
	}
	

}
