//package com.davidgluzman.couponsys.clr;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//import com.davidgluzman.couponsys.DBDAO.CompanyDBDAO;
//import com.davidgluzman.couponsys.beans.Company;
//import com.davidgluzman.couponsys.utils.HeadersArtUtils;
//import com.davidgluzman.couponsys.utils.TablesAndLinesUtils;
//
//@Component
//@Order(1)
//public class CompanyDBDAOTest implements org.springframework.boot.CommandLineRunner {
//
//	@Autowired
//	private CompanyDBDAO companyService;
//
//	@Override
//	public void run(String... args) throws Exception {
//
//		String string;
//
//		HeadersArtUtils.printCompanyDBDAOHeader();
//
//// creating companies
//
//		Company company = new Company();
//		company.setName("Koka kola");
//		company.setEmail("service@kokakola.com");
//		company.setPassword("pass");
//
//		Company company2 = new Company();
//		company2.setName("Fefsi");
//		company2.setEmail("service@fefsi.com");
//		company2.setPassword("pass");
//
//// confirming companies table is empty
//
//		string = "confirming companies table is empty";
//		TablesAndLinesUtils.printCompaniesTable(companyService.getAllCompanies(), string);
//
//// adding companies to DB
//
//		companyService.addCompany(company);
//		companyService.addCompany(company2);
//		string = "checking addCompany method (Koka kola and Fefsi has been added)";
//		TablesAndLinesUtils.printCompaniesTable(companyService.getAllCompanies(), string);
//
//// updating company in DB
//
//		company.setEmail("updated@kokakola.com");
//		companyService.updateCompany(company);
//		string = "checking updateCompany Method ( koka kola - email updated)";
//		TablesAndLinesUtils.printCompaniesTable(companyService.getAllCompanies(), string);
//
//// checking deleteCompany method
//
//		companyService.deleteCompany(company.getId());
//		string = "checking deleteCompany method (koka kola has been deleted)";
//		TablesAndLinesUtils.printCompaniesTable(companyService.getAllCompanies(), string);
//
//// checking getOneCompany method
//
//		TablesAndLinesUtils.printLine();
//		System.out.println("checking getOneCompany method (getting Fefsi)");
//		System.out.println();
//		System.out.println(companyService.getOneCompany(company2.getId()).get());
//
//// checking isCompanyExist method
//
//		TablesAndLinesUtils.printLine();
//		System.out.println("checking isCompanyExist method (checking if Fefsi Exists)");
//		System.out.println();
//		System.out.print("Fefsi Exists? - ");
//		System.out.println(companyService.isCompanyExist("service@fefsi.com", "pass"));
//		TablesAndLinesUtils.printLine();
//	}
//
//}
