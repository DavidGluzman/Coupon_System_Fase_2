package com.davidgluzman.couponsys.service.facade;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.davidgluzman.couponsys.beans.Company;
import com.davidgluzman.couponsys.beans.Coupon;
import com.davidgluzman.couponsys.beans.Customer;
import com.davidgluzman.couponsys.exceptions.AlreadyExistException;
import com.davidgluzman.couponsys.exceptions.InvalidActionException;
import com.davidgluzman.couponsys.exceptions.LoginException;
import com.davidgluzman.couponsys.service.services.CompanyService;
import com.davidgluzman.couponsys.service.services.CouponService;
import com.davidgluzman.couponsys.service.services.CustomerService;

@Service
public class AdminFacade extends ClientFacade {

	@Autowired
	private CompanyService companyService;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private CouponService couponService;

	@Override
	public boolean login(String email, String password) throws LoginException {
		if (email.equals("admin@admin.com") && password.equals("admin")) {
			return true;
		}
		throw new LoginException("Admin login denied - wrong email or password");
	}

	public List<Company> getAllCompanies() {
		return companyService.getAllCompanies();
	}

	public List<Customer> getAllCustomers() {
		return customerService.getAllCustomers();
	}

	public Optional<Company> getOneCompany(int companyID) {
		return companyService.getOneCompany(companyID);
	}

	public Optional<Customer> getOneCustomer(int customerID) {
		return customerService.getOneCustomer(customerID);
	}

	public void addCompany(Company company) throws AlreadyExistException {
		List<Company> companies = companyService.getAllCompanies();
		for (Company c : companies) {
			if (c.getName().equals(company.getName()) || c.getEmail().equals(company.getEmail())) {
				throw new AlreadyExistException("Company with this email or name is already exists");
			}
		}
		companyService.addCompany(company);
	}

	public void updateCompany(Company company) throws InvalidActionException {
		if (!companyService.isCompanyExist(companyService.getOneCompany(company.getId()).get().getEmail(),
				companyService.getOneCompany(company.getId()).get().getPassword())) {
			throw new InvalidActionException("company doesn't exist");
		}
		String companyName = companyService.getOneCompany(company.getId()).get().getName();
		if (!companyName.equalsIgnoreCase(company.getName())) {
			throw new InvalidActionException("changing companys name is not allowed");
		}
		companyService.updateCompany(company);
	}

	public void deleteCompany(int companyID) throws InvalidActionException {
		if (!companyService.isCompanyExist(companyService.getOneCompany(companyID).get().getEmail(),
				companyService.getOneCompany(companyID).get().getPassword())) {
			throw new InvalidActionException("company doesn't exist");
		}
		List<Coupon> coupons = couponService.getAllCoupons();

		for (Coupon c : coupons) {
			if (c.getCompanyID() == companyID) {
				couponService.deleteCoupon(c.getId());
			}
		}
		companyService.deleteCompany(companyID);

	}
	public void deleteCustomer(int customerID) {
		customerService.deleteCustomer(customerID);
	}
	public void addCustomer(Customer customer) throws AlreadyExistException {
		List<Customer> customers = customerService.getAllCustomers();
		for (Customer c : customers) {
			if (c.getEmail().equals(customer.getEmail())) {
				throw new AlreadyExistException("customer with this email is already exists");
			}
		}
		customerService.addCustomer(customer);
	}

	public void updateCustomer(Customer customer) throws InvalidActionException {
		if (!customerService.isCustomerExist(customerService.getOneCustomer(customer.getId()).get().getEmail(),
				customerService.getOneCustomer(customer.getId()).get().getPassword())) {
			throw new InvalidActionException("customer doesn't exist");
		}
		customerService.updateCustomer(customer);
	}

}
