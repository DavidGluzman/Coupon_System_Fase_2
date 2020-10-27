//package com.davidgluzman.couponsys.clr;
//
//import java.util.Date;
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//import com.davidgluzman.couponsys.DBDAO.CouponDBDAO;
//import com.davidgluzman.couponsys.DBDAO.CustomerDBDAO;
//import com.davidgluzman.couponsys.beans.Category;
//import com.davidgluzman.couponsys.beans.Coupon;
//import com.davidgluzman.couponsys.beans.Customer;
//import com.davidgluzman.couponsys.utils.DateUtils;
//import com.davidgluzman.couponsys.utils.HeadersArtUtils;
//import com.davidgluzman.couponsys.utils.TablesAndLinesUtils;
//
//@Component
//@Order(3)
//public class CouponDBDAOTest implements CommandLineRunner {
//	@Autowired
//	private CouponDBDAO couponService;
//	@Autowired
//	private CustomerDBDAO customerService;
//
//	@Override
//	public void run(String... args) throws Exception {
//
//		String string;
//
//		HeadersArtUtils.printCouponDBDAOHeader();
//
//// creating coupons
//
//		Coupon coupon = new Coupon();
//		coupon.setCompanyID(2);
//		coupon.setCategory(Category.Food);
//		coupon.setTitle("50%");
//		coupon.setDescription("50% on all drinks");
//		coupon.setStartDate(DateUtils.convertDate(new Date(2020, 8, 1)));
//		coupon.setEndDate(DateUtils.convertDate(new Date(2020, 12, 1)));
//		coupon.setAmount(500);
//		coupon.setPrice(2.5);
//		coupon.setImage("image");
//
//		Coupon coupon2 = new Coupon();
//		coupon2.setCompanyID(2);
//		coupon2.setCategory(Category.Food);
//		coupon2.setTitle("1+1");
//		coupon2.setDescription("1+1 on all drinks");
//		coupon2.setStartDate(DateUtils.convertDate(new Date(2020, 6, 1)));
//		coupon2.setEndDate(DateUtils.convertDate(new Date(2020, 12, 1)));
//		coupon2.setAmount(1000);
//		coupon2.setPrice(5);
//		coupon2.setImage("image");
//
//// confirming coupons table is empty
//
//		string = "confirming coupons table is empty";
//		TablesAndLinesUtils.printCouponsTable(couponService.getAllCoupons(), string);
//
//// adding coupons to DB
//
//		couponService.addCoupon(coupon);
//		couponService.addCoupon(coupon2);
//		string = "checking addCoupon method (50% and 1+1 has been added)";
//		TablesAndLinesUtils.printCouponsTable(couponService.getAllCoupons(), string);
//
//// updating coupon in DB
//
//		coupon.setDescription("updated");
//		couponService.updateCoupon(coupon);
//		string = "checking updateCoupon method (50% - description updated)";
//		TablesAndLinesUtils.printCouponsTable(couponService.getAllCoupons(), string);
//
//// checking deleteCoupon method
//
//		couponService.deleteCoupon(coupon.getId());
//		string = "checking deleteCoupon method (50% has been deleted)";
//		TablesAndLinesUtils.printCouponsTable(couponService.getAllCoupons(), string);
//
//// checking getOneCoupon method
//
//		TablesAndLinesUtils.printLine();
//		System.out.println("checking getOneCoupon method (getting 1+1)");
//		System.out.println();
//		System.out.println(couponService.getOneCoupon(coupon2.getId()).get());
//
//// adding another customer for testing purposes
//
//		Customer customer = new Customer();
//		customer.setFirstName("Moshe");
//		customer.setLastName("Moshe");
//		customer.setEmail("moshemoshe@email.com");
//		customer.setPassword("pass");
//
//		customerService.addCustomer(customer);
//
//// confirming customers in customers table has no purchases
//
//		string = "confirming customers in customers table has no purchases";
//		TablesAndLinesUtils.printCustomersTable(customerService.getAllCustomers(), string);
//
//// checking addCouponPurchase method
//
//		couponService.addCouponPurchase(customer.getId(), coupon2.getId());
//
//		string = "checking addCouponPurchase method (Moshe purchased 1+1 coupon)";
//		TablesAndLinesUtils.printCustomersTable(customerService.getAllCustomers(), string);
//
//// checking deleteCouponPurchase method
//
//		couponService.deleteCouponPurchase(customer.getId(), coupon2.getId());
//
//		string = "checking deleteCouponPurchase method (Moshes 1+1 coupon purchase has been deleted)";
//		TablesAndLinesUtils.printCustomersTable(customerService.getAllCustomers(), string);
//		TablesAndLinesUtils.printLine();
//
//		
//		
//	}
//
//}
