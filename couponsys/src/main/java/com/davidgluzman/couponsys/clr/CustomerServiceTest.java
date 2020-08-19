package com.davidgluzman.couponsys.clr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.davidgluzman.couponsys.beans.Customer;
import com.davidgluzman.couponsys.service.CustomerService;
import com.davidgluzman.couponsys.utils.HeadersArtUtils;
import com.davidgluzman.couponsys.utils.TablesAndLinesUtils;

@Component
@Order(2)
public class CustomerServiceTest implements CommandLineRunner {

	@Autowired
	private CustomerService customerService;

	@Override
	public void run(String... args) throws Exception {

		String string;

		HeadersArtUtils.printCustomerServiceHeader();

// creating customers

		Customer customer = new Customer();
		customer.setFirstName("David");
		customer.setLastName("Gluzman");
		customer.setEmail("davidgluzman@email.com");
		customer.setPassword("pass");

		Customer customer2 = new Customer();
		customer2.setFirstName("Kobi");
		customer2.setLastName("Shasha");
		customer2.setEmail("kobishasha@email.com");
		customer2.setPassword("pass");

// confirming customers table is empty

		string = "confirming customers table is empty";
		TablesAndLinesUtils.printCustomersTable(customerService.getAllCustomers(), string);

// adding customers to DB

		customerService.addCustomer(customer);
		customerService.addCustomer(customer2);
		string = "checking addCustomer Method (David Gluzman and Kobi Shasha has been added)";
		TablesAndLinesUtils.printCustomersTable(customerService.getAllCustomers(), string);

// updating company in DB

		customer.setPassword("updated");
		customerService.updateCustomer(customer);
		string = "checking updateCustomer Method ( David Gluzman - password updated)";
		TablesAndLinesUtils.printCustomersTable(customerService.getAllCustomers(), string);

// checking deleteCustomer method

		customerService.deleteCustomer(2);
		string = "checking deleteCustomer method (Kobi Shasha has been deleted)";
		TablesAndLinesUtils.printCustomersTable(customerService.getAllCustomers(), string);

// checking getOneCustomer method

		TablesAndLinesUtils.printLine();
		System.out.println("checking getOneCustomer method (getting David Gluzman)");
		System.out.println();
		System.out.println(customerService.getOneCustomer(1));

// checking isCustomerExist method

		TablesAndLinesUtils.printLine();
		System.out.println("checking isCustomerExist method (checking if David Gluzman Exists)");
		System.out.println();
		System.out.print("David Gluzman Exists? - ");
		System.out.println(customerService.isCustomerExist("davidgluzman@email.com", "updated"));
		TablesAndLinesUtils.printLine();

	}

}
