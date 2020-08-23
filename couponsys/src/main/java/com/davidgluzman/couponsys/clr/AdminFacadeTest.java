package com.davidgluzman.couponsys.clr;

import java.util.Arrays;
import java.util.Date;

import javax.swing.text.ChangedCharSetException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.davidgluzman.couponsys.beans.Category;
import com.davidgluzman.couponsys.beans.Company;
import com.davidgluzman.couponsys.beans.Coupon;
import com.davidgluzman.couponsys.beans.Customer;
import com.davidgluzman.couponsys.exceptions.AlreadyExistException;
import com.davidgluzman.couponsys.exceptions.InvalidActionException;
import com.davidgluzman.couponsys.service.facade.AdminFacade;
import com.davidgluzman.couponsys.service.services.CouponService;
import com.davidgluzman.couponsys.utils.DateUtils;
import com.davidgluzman.couponsys.utils.HeadersArtUtils;
import com.davidgluzman.couponsys.utils.PrintStringUtils;
import com.davidgluzman.couponsys.utils.TablesAndLinesUtils;


@Component
@Order(4)
public class AdminFacadeTest implements CommandLineRunner {

	@Autowired
	AdminFacade adminFacade;

	@Override
	public void run(String... args) throws Exception {

		HeadersArtUtils.printAdminFacadeHeader();

// getting all companies before testing	

		TablesAndLinesUtils.printCompaniesTable(adminFacade.getAllCompanies(), "getting all companies before testing");
		TablesAndLinesUtils.printLine();

// testing addCompany()

		System.out.println("testing exceptions - addCompany():");
		System.out.println();

		Company company = new Company();
		company.setName("Fefsi");
		company.setEmail("service@email.com");
		company.setPassword("pass");
		try {
			adminFacade.addCompany(company);

		} catch (AlreadyExistException e) {
			PrintStringUtils.printInitiatedException(e.getMessage(),
					"trying to add another company with the same name as Fefsi");
		}
		Company company2 = new Company();
		company2.setName("Company");
		company2.setEmail("service@fefsi.com");
		company2.setPassword("pass");
		try {
			adminFacade.addCompany(company2);

		} catch (AlreadyExistException e) {
			PrintStringUtils.printInitiatedException(e.getMessage(),
					"trying to add another company with the same email as Fefsi");
		}
		Company company3 = new Company();
		company3.setName("Pitta Hut");
		company3.setEmail("service@pittahut.com");
		company3.setPassword("pass");
		try {
			adminFacade.addCompany(company3);

		} catch (AlreadyExistException e) {
			PrintStringUtils.printInitiatedException(e.getMessage(), "");
		}
		TablesAndLinesUtils.printCompaniesTable(adminFacade.getAllCompanies(),
				"testing addCompany method (Pitta Hut has been added)");

// testing updateCompany()	

		TablesAndLinesUtils.printLine();
		System.out.println("testing exceptions - updateCompany():");
		System.out.println();

		company3.setName("Pizza Hut");

		try {
			adminFacade.updateCompany(company3);
		} catch (InvalidActionException e) {
			PrintStringUtils.printInitiatedException(e.getMessage(), "trying to update Pitta Huts name");
		}
		company3.setName("Pitta Hut");
		company3.setPassword("updated");
		adminFacade.updateCompany(company3);
		TablesAndLinesUtils.printCompaniesTable(adminFacade.getAllCompanies(),
				"testing updateCompany method (Pitta Hut - password updated)");

// testing getOneCompany()

		TablesAndLinesUtils.printCompaniesTable(Arrays.asList(adminFacade.getOneCompany(company3.getId()).get()),
				"testing getOneCompany method (getting Pitta Hut) ");

// testing addCustomer()

		TablesAndLinesUtils.printCustomersTable(adminFacade.getAllCustomers(), "getting all customers before testing");
		TablesAndLinesUtils.printLine();
		
		System.out.println("testing exceptions - addCustomer():");
		System.out.println();
		
		Customer customer = new Customer();
		
		customer.setFirstName("Stam");
		customer.setLastName("Customer");
		customer.setEmail("moshemoshe@email.com");
		customer.setPassword("pass");
		
		try {
			adminFacade.addCustomer(customer);
		} catch (AlreadyExistException e) {
			PrintStringUtils.printInitiatedException(e.getMessage(), "trying to add another customer with the same email");
		}
		
		Customer customer2 = new Customer();
		
		customer2.setFirstName("Barak");
		customer2.setLastName("Obama");
		customer2.setEmail("barakobama@email.com");
		customer2.setPassword("pass");
		
		adminFacade.addCustomer(customer2);
		
		TablesAndLinesUtils.printCustomersTable(adminFacade.getAllCustomers(),
				"testing addCustomer method (Barak Obama has been added)");

//testing updateCustomer()
		
		customer2.setFirstName("Donald");
		customer2.setLastName("Trump");
		
		adminFacade.updateCustomer(customer2);
		TablesAndLinesUtils.printCustomersTable(adminFacade.getAllCustomers(), "testing updateCustomer Method (Barak Obama has been changed to Donald Trump)");
		
//testing getOneCustomer()
		
		TablesAndLinesUtils.printCustomersTable(Arrays.asList(adminFacade.getOneCustomer(customer2.getId()).get()), "testing getOneCustomer method (getting Donald Trump)");

//testing deleteCompany()
		
		Coupon coupon=new Coupon();
		coupon.setTitle("1 ILS pizza");
		coupon.setDescription("buy one pizza and get another one for 1 ILS");
		coupon.setCompanyID(3);
		coupon.setAmount(100);
		coupon.setCategory(Category.Food);
		coupon.setImage("image");
		coupon.setPrice(70);
		coupon.setStartDate(DateUtils.convertDate(new Date(2020, 6, 1)));
		coupon.setEndDate(DateUtils.convertDate(new Date(2020, 11, 1)));
		
		company3.setCoupons(Arrays.asList(coupon));
		adminFacade.updateCompany(company3);
		
		customer2.setCoupons(Arrays.asList(coupon));
		adminFacade.updateCustomer(customer2);
		
		TablesAndLinesUtils.printCompaniesTable(adminFacade.getAllCompanies(), "all companies before deleteCompany method (Pitta Hut added a coupon)");
		TablesAndLinesUtils.printCustomersTable(adminFacade.getAllCustomers(), "all customers before deleteCompany method (Donald Trump purchased a coupon)");
		
		adminFacade.deleteCompany(company3.getId());
		
		TablesAndLinesUtils.printCompaniesTable(adminFacade.getAllCompanies(), "all companies after deleteCompany method (Pitta Hut has been deleted)");
		TablesAndLinesUtils.printCustomersTable(adminFacade.getAllCustomers(), "all customers after deleteCompany method (Donald Trump purchased coupon has been deleted)");

//testing deleteCustomer()
		
		Coupon coupon2=new Coupon();
		coupon2.setTitle("PROMO - free bottle");
		coupon2.setDescription("PROMOTION - get a free Fefsi bottle");
		coupon2.setCompanyID(2);
		coupon2.setAmount(500);
		coupon2.setCategory(Category.Food);
		coupon2.setImage("image");
		coupon2.setPrice(0);
		coupon2.setStartDate(DateUtils.convertDate(new Date(2020, 7, 1)));
		coupon2.setEndDate(DateUtils.convertDate(new Date(2020, 12, 1)));
		
		customer2.setCoupons(Arrays.asList(coupon2));
		adminFacade.updateCustomer(customer2);
		
		TablesAndLinesUtils.printCustomersTable(adminFacade.getAllCustomers(), "all customers before deleteCustomer method (Donald Trump purchased a coupon)");
		adminFacade.deleteCustomer(customer2.getId());
		TablesAndLinesUtils.printCustomersTable(adminFacade.getAllCustomers(), "all customers after deleteCompany method (Donald Trump has been deleted)");
		
		
		// testing deleteCompany()

//		Coupon coupon=new Coupon();
//		coupon.setTitle("1 ILS pizza");
//		coupon.setDescription("buy one pizza and get another one for 1 ILS");
//		coupon.setCompanyID(3);
//		coupon.setAmount(100);
//		coupon.setCategory(Category.Food);
//		coupon.setImage("image");
//		coupon.setPrice(70);
//		coupon.setStartDate(DateUtils.convertDate(new Date(2020, 6, 1)));
//		coupon.setEndDate(DateUtils.convertDate(new Date(2020, 11, 1)));
//		
//		company3.setCoupons(Arrays.asList(coupon));
//		adminFacade.updateCompany(company3);
//		TablesAndLinesUtils.printCompaniesTable(adminFacade.getAllCompanies(), "Pitta Hut added a coupon (1 ILS pizza)");
//		adminFacade.deleteCompany(company3.getId());
//		
//		Customer customer=new Customer();
//		customer.setFirstName("Barak");
//		customer.setLastName("Obama");
//		customer.setPassword("pass");
//		customer.setCoupons(Arrays.asList(coupon));

	}

}
