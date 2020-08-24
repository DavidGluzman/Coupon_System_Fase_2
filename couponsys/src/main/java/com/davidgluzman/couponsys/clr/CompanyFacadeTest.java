package com.davidgluzman.couponsys.clr;

import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.davidgluzman.couponsys.beans.Category;
import com.davidgluzman.couponsys.beans.Coupon;
import com.davidgluzman.couponsys.exceptions.AlreadyExistException;
import com.davidgluzman.couponsys.exceptions.InvalidActionException;
import com.davidgluzman.couponsys.exceptions.LoginException;
import com.davidgluzman.couponsys.service.facade.CompanyFacade;
import com.davidgluzman.couponsys.utils.DateUtils;
import com.davidgluzman.couponsys.utils.HeadersArtUtils;
import com.davidgluzman.couponsys.utils.PrintStringUtils;
import com.davidgluzman.couponsys.utils.TablesAndLinesUtils;

@Component
@Order(5)
public class CompanyFacadeTest implements CommandLineRunner {

	@Autowired
	CompanyFacade companyFacade;

	@Override
	public void run(String... args) throws Exception {
		HeadersArtUtils.printCompanyFacadeHeader();
		TablesAndLinesUtils.printLine();

//testing login

		System.out.println("testing exceptions - login():");
		System.out.println();
		try {
			companyFacade.login("prohacker@hacking.com", "pass");
		} catch (LoginException e) {
			PrintStringUtils.printInitiatedException(e.getMessage(), "trying to login with wrong email");
		}
		try {
			companyFacade.login("service@abidas.com", "wrong pass");
		} catch (LoginException e) {
			PrintStringUtils.printInitiatedException(e.getMessage(), "trying to login with wrong password");
		}
		TablesAndLinesUtils.printLine();
		companyFacade.login("service@abidas.com", "pass");

		companyFacade.setCompanyID(4);

// getting all company's coupons before testing	

		TablesAndLinesUtils.printCouponsTable(companyFacade.getAllCoupons(),
				"getting all companys coupons before testing");

//adding coupon

		Coupon coupon = new Coupon();
		coupon.setAmount(500);
		coupon.setCategory(Category.Apparel);
		coupon.setCompanyID(4);
		coupon.setDescription("25% off on tshirt");
		coupon.setImage("image");
		coupon.setPrice(150);
		coupon.setTitle("Sale");
		coupon.setStartDate(DateUtils.convertDate(new Date(2020, 1, 1)));
		coupon.setEndDate(DateUtils.convertDate(new Date(2020, 8, 1)));

		Coupon coupon2 = new Coupon();
		coupon2.setAmount(200);
		coupon2.setCategory(Category.Apparel);
		coupon2.setCompanyID(4);
		coupon2.setDescription("50% off on tshirt");
		coupon2.setImage("image");
		coupon2.setPrice(100);
		coupon2.setTitle("Sale");
		coupon2.setStartDate(DateUtils.convertDate(new Date(2020, 2, 1)));
		coupon2.setEndDate(DateUtils.convertDate(new Date(2020, 9, 1)));

		Coupon coupon3 = new Coupon();
		coupon3.setAmount(450);
		coupon3.setCategory(Category.Sport);
		coupon3.setCompanyID(4);
		coupon3.setDescription("10% off ticket");
		coupon3.setImage("image");
		coupon3.setPrice(90);
		coupon3.setTitle("Game");
		coupon3.setStartDate(DateUtils.convertDate(new Date(2020, 4, 1)));
		coupon3.setEndDate(DateUtils.convertDate(new Date(2020, 10, 1)));

		
		companyFacade.addCoupon(coupon);
		companyFacade.addCoupon(coupon3);

		TablesAndLinesUtils.printLine();
		System.out.println("testing exceptions - addCoupon():");
		System.out.println();

		try {
			companyFacade.addCoupon(coupon2);
		} catch (AlreadyExistException e) {
			PrintStringUtils.printInitiatedException(e.getMessage(),
					"trying to add another coupon with the same title");
		}

		TablesAndLinesUtils.printCouponsTable(companyFacade.getAllCoupons(),
				"testing addCoupon method (SALE and GAME have been added)");
		TablesAndLinesUtils.printLine();

//testing updateCoupon()
		
		System.out.println("testing exceptions - updateCoupon():");
		System.out.println();

		coupon.setCompanyID(5);
		try {
			companyFacade.updateCoupon(coupon);
		} catch (InvalidActionException e) {
			PrintStringUtils.printInitiatedException(e.getMessage(), "trying to update SALE companyID");
		}
		coupon.setCompanyID(4);
		
		coupon.setDescription("updated");
		
		companyFacade.updateCoupon(coupon);
		
		TablesAndLinesUtils.printCouponsTable(companyFacade.getAllCoupons(), "testing updateCoupon method (sale description updated)");
		
//testing getAllCouponsByCategory()
		
		TablesAndLinesUtils.printCouponsTable(companyFacade.getAllCouponsByCategory(Category.Apparel),"testing getAllCouponsByCategory method (apparel)");
		TablesAndLinesUtils.printCouponsTable(companyFacade.getAllCouponsByCategory(Category.Sport), "testing getAllCouponsByCategory method (sport)");
	
//testing getAllCouponsByPriceLessThan()
		
		TablesAndLinesUtils.printCouponsTable(companyFacade.getAllCoupons(), "all Abidas coupons before testing getAllCouponsByPriceLessThan method");
		TablesAndLinesUtils.printCouponsTable(companyFacade.getAllCouponsByPriceLessThan(100), "testing getAllCouponsByPriceLessThan method (coupons which price is less than 100)");
	
//testing getCompanyDetails()
		
		TablesAndLinesUtils.printCompaniesTable(Arrays.asList(companyFacade.getCompanyDetails()), "testing getCompanyDetails method (getting Abidas details)");
	
//testing deleteCoupon()
		
		TablesAndLinesUtils.printCouponsTable(companyFacade.getAllCoupons(), "testing deleteCoupon method (all Abidas coupons before deleting)");
		companyFacade.deleteCoupon(6);
		TablesAndLinesUtils.printCouponsTable(companyFacade.getAllCoupons(), "testing deleteCoupon method (all Abidas coupons after deleting SALE)");
		companyFacade.deleteCoupon(7);
		TablesAndLinesUtils.printCouponsTable(companyFacade.getAllCoupons(), "testing deleteCoupon method (all Abidas coupons after deleting GAME)");

		
		
	}
}
