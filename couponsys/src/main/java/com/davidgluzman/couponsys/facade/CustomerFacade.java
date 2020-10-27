package com.davidgluzman.couponsys.facade;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.davidgluzman.couponsys.DBDAO.CompanyDBDAO;
import com.davidgluzman.couponsys.DBDAO.CouponDBDAO;
import com.davidgluzman.couponsys.DBDAO.CustomerDBDAO;
import com.davidgluzman.couponsys.beans.Category;
import com.davidgluzman.couponsys.beans.Company;
import com.davidgluzman.couponsys.beans.Coupon;
import com.davidgluzman.couponsys.beans.Customer;
import com.davidgluzman.couponsys.exceptions.InvalidActionException;
import com.davidgluzman.couponsys.exceptions.LoginException;

import lombok.Getter;
import lombok.Setter;

@Service
@Scope("prototype")
public class CustomerFacade extends ClientFacade {

	@Getter
	@Setter
	private int customerID;

	@Override
	public boolean login(String email, String password) throws LoginException {
		if (!customerDBDAO.isCustomerExist(email, password)) {
			throw new LoginException("Customer login denied - wrong email or password");
		}
		this.customerID = customerDBDAO.getOneCustomerByEmailAndPassword(email, password).getId();
		System.out.println("Customer - successful login");
		return true;
	}

	public List<Coupon> getAllCoupons() {
		Customer customer = customerDBDAO.getOneCustomer(this.customerID);
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

	public List<Coupon> getAllCouponsByPriceLessThan(double price) {
		List<Coupon> coupons = getAllCoupons();
		List<Coupon> filterCoupons = new ArrayList<Coupon>();
		for (Coupon c : coupons) {
			if (c.getPrice() <= price) {
				filterCoupons.add(c);
			}
		}
		return filterCoupons;
	}

	public void addCouponPurchase(int couponID) throws InvalidActionException {
		List<Coupon> coupons = customerDBDAO.getOneCustomer(this.customerID).getCoupons();
		for (Coupon c : coupons) {
			if (couponID == c.getId()) {
				throw new InvalidActionException("a customer can only purchase a specific coupon once");
			}
		}

		if (couponDBDAO.getOneCoupon(couponID).getAmount() == 0) {
			throw new InvalidActionException("there are no coupons left to purchase");
		}
		if (couponDBDAO.getOneCoupon(couponID).getEndDate().compareTo(java.sql.Date.valueOf(LocalDate.now())) < 0) {

			throw new InvalidActionException("this coupon is expired");
		}

		couponDBDAO.addCouponPurchase(this.customerID, couponID);
		Coupon coupon = couponDBDAO.getOneCoupon(couponID);
		coupon.setAmount(coupon.getAmount() - 1);
		couponDBDAO.updateCoupon(coupon);

	}

	public Customer getCustomerDetails() {
		Customer customer = customerDBDAO.getOneCustomer(this.customerID);
		return customer;
	}

}
