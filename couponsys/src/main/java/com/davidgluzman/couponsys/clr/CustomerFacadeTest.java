//package com.davidgluzman.couponsys.clr;
//
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.ArrayList;
//import java.util.Arrays;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//import com.davidgluzman.couponsys.beans.Category;
//import com.davidgluzman.couponsys.exceptions.InvalidActionException;
//import com.davidgluzman.couponsys.exceptions.LoginException;
//import com.davidgluzman.couponsys.facade.CustomerFacade;
//import com.davidgluzman.couponsys.utils.HeadersArtUtils;
//import com.davidgluzman.couponsys.utils.PrintStringUtils;
//import com.davidgluzman.couponsys.utils.TablesAndLinesUtils;
//
//@Component
//@Order(6)
//public class CustomerFacadeTest implements CommandLineRunner {
//
//	@Autowired
//	CustomerFacade customerFacade;
//
//	@Override
//	public void run(String... args) throws Exception {
//
//		HeadersArtUtils.printCustomerFacadeHeader();
//		TablesAndLinesUtils.printLine();
//
////testing login
//
//		System.out.println("testing exceptions - login():");
//		System.out.println();
//		try {
//			customerFacade.login("prohacker@hacking.com", "pass");
//		} catch (LoginException e) {
//			PrintStringUtils.printInitiatedException(e.getMessage(), "trying to login with wrong email");
//		}
//		try {
//			customerFacade.login("moshemoshe@email.com", "wrong pass");
//		} catch (LoginException e) {
//			PrintStringUtils.printInitiatedException(e.getMessage(), "trying to login with wrong password");
//		}
//		TablesAndLinesUtils.printLine();
//		customerFacade.login("moshemoshe@email.com", "pass");
//
//		customerFacade.setCustomerID(3);
//
//// getting all company's coupons before testing	
//
//		TablesAndLinesUtils.printCouponsTable(customerFacade.getAllCoupons(),
//				"getting all customers coupon purchases before testing");
//
//// testing addCouponPurchase()
//
//		customerFacade.addCouponPurchase(10);
//		customerFacade.addCouponPurchase(11);
//		TablesAndLinesUtils.printLine();
//		System.out.println("testing exceptions - addCouponPurchase():");
//		System.out.println();
//		try {
//			customerFacade.addCouponPurchase(10);
//		} catch (InvalidActionException e) {
//			PrintStringUtils.printInitiatedException(e.getMessage(), "trying to buy a coupon for the second time");
//		}
//		try {
//			customerFacade.addCouponPurchase(8);
//		} catch (InvalidActionException e) {
//			PrintStringUtils.printInitiatedException(e.getMessage(), "trying to buy a coupon with no amount");
//		}
//		try {
//			customerFacade.addCouponPurchase(9);
//		} catch (InvalidActionException e) {
//			PrintStringUtils.printInitiatedException(e.getMessage(), "trying to buy an expired coupon");
//		}
//
//		TablesAndLinesUtils.printCouponsTable(customerFacade.getAllCoupons(),
//				"testing addCouponPurchase method (75% (qty. was 1) and 15% (qty. was 100) have been purchased)");
//
////testing getAllCouponsByCategory()
//
//		TablesAndLinesUtils.printCouponsTable(customerFacade.getAllCouponsByCategory(Category.Sport),
//				"testing getAllCouponsByCategory method (getting sport coupons) ");
//
////testing getAllCouponsByPriceLessThan()
//
//		TablesAndLinesUtils.printCouponsTable(customerFacade.getAllCouponsByPriceLessThan(200),
//				"testing getAllCouponsByPriceLessThan method (getting coupons that cost less than 200)");
//
////testing getCustomerDetails()
//
//		TablesAndLinesUtils.printCustomersTable(Arrays.asList(customerFacade.getCustomerDetails()),
//				"testing getCustomerDetails method (getting Moshes details)");
//
//	}
//
//}
