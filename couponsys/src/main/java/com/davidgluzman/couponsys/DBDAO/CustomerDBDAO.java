package com.davidgluzman.couponsys.DBDAO;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.davidgluzman.couponsys.beans.Company;
import com.davidgluzman.couponsys.beans.Customer;
import com.davidgluzman.couponsys.repository.CustomerRepository;

@Service
public class CustomerDBDAO {
	@Autowired
	public CustomerRepository customerRepository;

	public void addCustomer(Customer customer) {
		customerRepository.save(customer);
	}

	public void updateCustomer(Customer customer) {
		customerRepository.saveAndFlush(customer);
	}

	public void deleteCustomer(int customerId) {
		customerRepository.deleteById(customerId);
	}

	public Customer getOneCustomer(int customerId) {
		return customerRepository.findById(customerId).get();
	}

	public Customer getOneCustomerByEmailAndPassword(String email, String password) {
		return customerRepository.findByEmailAndPassword(email, password);
	}

	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	public boolean isCustomerExist(String email, String password) {
		return (customerRepository.findByEmailAndPassword(email, password) != null);
	}
}
