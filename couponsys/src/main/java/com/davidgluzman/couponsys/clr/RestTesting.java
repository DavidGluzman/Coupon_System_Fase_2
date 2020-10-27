package com.davidgluzman.couponsys.clr;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.davidgluzman.couponsys.beans.Company;
import com.davidgluzman.couponsys.beans.Customer;
import com.davidgluzman.couponsys.facade.AdminFacade;

@Component
public class RestTesting implements CommandLineRunner{

	@Autowired
	AdminFacade adminFacade;
	@Override
	public void run(String... args) throws Exception {
	
	Customer customer = new Customer();
	customer.setFirstName("David");
	customer.setLastName("Gluzman");
	customer.setEmail("gluzman@email.com");
	customer.setPassword("password");
	
	Customer customer2 = new Customer();
	customer2.setFirstName("Yossi");
	customer2.setLastName("Shemi");
	customer2.setEmail("shemi@email.com");
	customer2.setPassword("password");
	
	Customer customer3 = new Customer();
	customer3.setFirstName("Noam");
	customer3.setLastName("Marciano");
	customer3.setEmail("marciano@email.com");
	customer3.setPassword("password");
	
	Company company = new Company();
	company.setName("Company Test");
	company.setEmail("company@email.com");
	company.setPassword("password");
	
	Company company2 = new Company();
	company2.setName("Company Test2");
	company2.setEmail("company2@email.com");
	company2.setPassword("password");
	
	Company company3 = new Company();
	company3.setName("Company Test3");
	company3.setEmail("company3@email.com");
	company3.setPassword("password");
	
	adminFacade.addCustomer(customer);
	adminFacade.addCustomer(customer2);
	adminFacade.addCustomer(customer3);
	adminFacade.addCompany(company);
	adminFacade.addCompany(company2);
	adminFacade.addCompany(company3);
	}

}
