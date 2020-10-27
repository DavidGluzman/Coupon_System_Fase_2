package com.davidgluzman.couponsys.facade;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.davidgluzman.couponsys.DBDAO.CompanyDBDAO;
import com.davidgluzman.couponsys.DBDAO.CouponDBDAO;
import com.davidgluzman.couponsys.DBDAO.CustomerDBDAO;
import com.davidgluzman.couponsys.beans.Company;
import com.davidgluzman.couponsys.beans.Coupon;
import com.davidgluzman.couponsys.beans.Customer;
import com.davidgluzman.couponsys.exceptions.AlreadyExistException;
import com.davidgluzman.couponsys.exceptions.InvalidActionException;
import com.davidgluzman.couponsys.exceptions.LoginException;

@Service
public class AdminFacade extends ClientFacade {

	@Override
	public boolean login(String email, String password) throws LoginException {
		if (email.equals("admin@admin.com") && password.equals("admin")) {
			System.out.println("Admin - successful login");
			return true;
		}
		throw new LoginException("Admin login denied - wrong email or password");
	}

	public List<Company> getAllCompanies() {
		return companyDBDAO.getAllCompanies();
	}

	public List<Customer> getAllCustomers() {
		return customerDBDAO.getAllCustomers();
	}

	public Company getOneCompany(int companyID) {
		return companyDBDAO.getOneCompany(companyID);
	}

	public Customer getOneCustomer(int customerID) {
		return customerDBDAO.getOneCustomer(customerID);
	}

	public void addCompany(Company company) throws AlreadyExistException {
		List<Company> companies = companyDBDAO.getAllCompanies();
		for (Company c : companies) {
			if (c.getName().equals(company.getName()) || c.getEmail().equals(company.getEmail())) {
				throw new AlreadyExistException("Company with this email or name is already exists");
			}
		}
		companyDBDAO.addCompany(company);
	}

	public void updateCompany(Company company) throws InvalidActionException {
		if (!companyDBDAO.isCompanyExist(companyDBDAO.getOneCompany(company.getId()).getEmail(),
				companyDBDAO.getOneCompany(company.getId()).getPassword())) {
			throw new InvalidActionException("company doesn't exist");
		}
		String companyName = companyDBDAO.getOneCompany(company.getId()).getName();
		if (!companyName.equalsIgnoreCase(company.getName())) {
			throw new InvalidActionException("changing companys name is not allowed");
		}
		companyDBDAO.updateCompany(company);
	}

	public void deleteCompany(int companyID) throws InvalidActionException {
		if (!companyDBDAO.isCompanyExist(companyDBDAO.getOneCompany(companyID).getEmail(),
				companyDBDAO.getOneCompany(companyID).getPassword())) {
			throw new InvalidActionException("company doesn't exist");
		}

		companyDBDAO.deleteCompany(companyID);
		List<Coupon> coupons = couponDBDAO.getAllCoupons();
		for (Coupon c : coupons) {
			if (c.getCompanyID() == companyID) {
				couponDBDAO.deleteCoupon(c.getId());
			}
		}

	}

	public void deleteCustomer(int customerID) {
		customerDBDAO.deleteCustomer(customerID);
	}

	public void addCustomer(Customer customer) throws AlreadyExistException {
		List<Customer> customers = customerDBDAO.getAllCustomers();
		for (Customer c : customers) {
			if (c.getEmail().equals(customer.getEmail())) {
				throw new AlreadyExistException("customer with this email is already exists");
			}
		}
		customerDBDAO.addCustomer(customer);
	}

	public void updateCustomer(Customer customer) throws InvalidActionException {
		if (!customerDBDAO.isCustomerExist(customerDBDAO.getOneCustomer(customer.getId()).getEmail(),
				customerDBDAO.getOneCustomer(customer.getId()).getPassword())) {
			throw new InvalidActionException("customer doesn't exist");
		}
		customerDBDAO.updateCustomer(customer);
	}

}
