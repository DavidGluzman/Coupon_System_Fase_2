package com.davidgluzman.couponsys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.davidgluzman.couponsys.beans.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	Customer findByEmailAndPassword(String email, String password);

}
