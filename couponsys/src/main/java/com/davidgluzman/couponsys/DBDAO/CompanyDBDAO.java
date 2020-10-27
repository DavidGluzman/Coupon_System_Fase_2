package com.davidgluzman.couponsys.DBDAO;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.davidgluzman.couponsys.beans.Company;
import com.davidgluzman.couponsys.repository.CompanyRepository;

@Service
public class CompanyDBDAO {
	@Autowired
	CompanyRepository companyRepository;

	public void addCompany(Company company) {
		companyRepository.save(company);
	}

	public void updateCompany(Company company) {
		companyRepository.saveAndFlush(company);

	}

	public void deleteCompany(int companyId) {
		companyRepository.deleteById(companyId);
	}

	public Company getOneCompany(int companyId) {
		return companyRepository.findById(companyId).get();
	}
	public Company getOneCompanyByEmailAndPassword(String email,String password) {
		return companyRepository.findByEmailAndPassword(email, password);
	}

	public List<Company> getAllCompanies() {
		return companyRepository.findAll();
	}

	public boolean isCompanyExist(String email, String password) {
		return (companyRepository.findByEmailAndPassword(email, password) != null);

	}
}
