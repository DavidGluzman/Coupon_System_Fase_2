package com.davidgluzman.couponsys.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.davidgluzman.couponsys.beans.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
	Company findByEmailAndPassword(String email, String password);
}
