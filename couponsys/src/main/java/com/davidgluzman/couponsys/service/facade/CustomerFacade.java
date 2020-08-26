package com.davidgluzman.couponsys.service.facade;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.davidgluzman.couponsys.beans.Category;
import com.davidgluzman.couponsys.beans.Company;
import com.davidgluzman.couponsys.beans.Coupon;
import com.davidgluzman.couponsys.beans.Customer;
import com.davidgluzman.couponsys.exceptions.InvalidActionException;
import com.davidgluzman.couponsys.exceptions.LoginException;
import com.davidgluzman.couponsys.service.services.CompanyService;
import com.davidgluzman.couponsys.service.services.CouponService;
import com.davidgluzman.couponsys.service.services.CustomerService;

import lombok.Getter;
import lombok.Setter;

@Service
public class CustomerFacade extends ClientFacade {

	@Autowired
	private CompanyService companyService;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private CouponService couponService;
	@Getter
	@Setter
	private int customerID;

	@Override
	public boolean login(String email, String password) throws LoginException {
		if (!customerService.isCustomerExist(email, password)) {
			throw new LoginException("Customer login denied - wrong email or password");
		}
		this.customerID = customerService.getOneCustomerByEmailAndPassword(email, password).getId();
		System.out.println("Customer - successful login");
		return true;
	}

	public List<Coupon> getAllCoupons() {
		Customer customer = customerService.getOneCustomer(this.customerID).get();
		List<Coupon> coupons = customer.getCoupons();
		return coupons;
	}
	public List<Coupon> getAllCouponsByCategory(Category category) {
		List<Coupon> coupons = getAllCoupons();
		List<Coupon> filteredCoupons = new ArrayList<Coupon>();
		for (Coupon c : coupons) {
			if (c.getCategory() == category) {
				filteredCoupons.add(c);
			}
		}
		return filteredCoupons;
	}
	public List<Coupon> getAllCouponsByPriceLessThan(double price){
		List<Coupon> coupons=getAllCoupons();
		List<Coupon> filterCoupons=new ArrayList<Coupon>();
		for (Coupon c : coupons) {
			if (c.getPrice()<=price) {
				filterCoupons.add(c);
			}
		}
		return filterCoupons;
	}

	public void addCouponPurchase(int couponID) throws InvalidActionException {
		List<Coupon> coupons = customerService.getOneCustomer(this.customerID).get().getCoupons();
		for (Coupon c : coupons) {
			if (couponID == c.getId()) {
				throw new InvalidActionException("a customer can only purchase a specific coupon once");
			}
		}

		if (couponService.getOneCoupon(couponID).get().getAmount() == 0) {
			throw new InvalidActionException("there are no coupons left to purchase");
		}
		if (couponService.getOneCoupon(couponID).get().getEndDate()
				.compareTo(java.sql.Date.valueOf(LocalDate.now())) < 0) {

			throw new InvalidActionException("this coupon is expired");
		}

		couponService.addCouponPurchase(this.customerID, couponID);
		Coupon coupon = couponService.getOneCoupon(couponID).get();
		coupon.setAmount(coupon.getAmount() - 1);
		couponService.updateCoupon(coupon);

	}
	public Customer getCustomerDetails() {
		Customer customer = customerService.getOneCustomer(this.customerID).get();
		return customer;
	}

}
