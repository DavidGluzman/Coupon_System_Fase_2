package com.davidgluzman.couponsys.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.davidgluzman.couponsys.beans.Company;
import com.davidgluzman.couponsys.beans.Customer;
import com.davidgluzman.couponsys.dto.EmailPasswordDTO;
import com.davidgluzman.couponsys.dto.LoginResponseDTO;
import com.davidgluzman.couponsys.exceptions.AlreadyExistException;
import com.davidgluzman.couponsys.exceptions.DoesntExistException;
import com.davidgluzman.couponsys.exceptions.InvalidActionException;
import com.davidgluzman.couponsys.facade.AdminFacade;
import com.davidgluzman.couponsys.security.ClientType;
import com.davidgluzman.couponsys.security.LoginManager;
import com.davidgluzman.couponsys.security.TokenManager;

@RestController
@RequestMapping("admin")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class AdminController extends ClientController {

	@Autowired
	private TokenManager tokenManager;

	@Override
	@PostMapping("login")
	public ResponseEntity<?> login(@RequestBody EmailPasswordDTO dto) {
		HttpHeaders httpHeaders = new HttpHeaders();
		try {
			String token = loginManager.login2(dto.getEmail(), dto.getPassword(), ClientType.Administrator);
			return ResponseEntity.ok().body(new LoginResponseDTO("logged as admin",token));
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
	}

	@PostMapping("addCompany")
	public ResponseEntity<?> addCompany(@RequestBody Company company,
			@RequestHeader(name = "Token", required = false) String token) {
		try {
			tokenManager.isTokenExist(token);
			((AdminFacade) (tokenManager.getClientFacadeByToken(token))).addCompany(company);
			return ResponseEntity.ok().build();
		} catch (InvalidActionException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		} catch (AlreadyExistException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

	@PostMapping("addCustomer")
	public ResponseEntity<?> addCustomer(@RequestBody Customer customer,
			@RequestHeader(name = "Token", required = false) String token) {
		try {
			tokenManager.isTokenExist(token);
			((AdminFacade) (tokenManager.getClientFacadeByToken(token))).addCustomer(customer);
			return ResponseEntity.ok().build();
		} catch (InvalidActionException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (AlreadyExistException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping("getAllCompanies")
	public ResponseEntity<?> getAllCompanies(@RequestHeader(name = "Token", required = false) String token) {
		try {
			tokenManager.isTokenExist(token);
			return new ResponseEntity<List<Company>>(
					((AdminFacade) (tokenManager.getClientFacadeByToken(token))).getAllCompanies(), HttpStatus.OK);
		} catch (InvalidActionException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}

	}

	@GetMapping("getAllCustomers")
	public ResponseEntity<?> getAllCustomers(@RequestHeader(name = "Token", required = false) String token) {
		try {
			tokenManager.isTokenExist(token);
			return new ResponseEntity<List<Customer>>(
					((AdminFacade) (tokenManager.getClientFacadeByToken(token))).getAllCustomers(), HttpStatus.OK);
		} catch (InvalidActionException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}

	}

	@GetMapping("getOneCompanyById")
	public ResponseEntity<?> getOneCompany(@RequestParam int id,
			@RequestHeader(name = "Token", required = false) String token) {

		try {
			tokenManager.isTokenExist(token);
			return new ResponseEntity<Company>(
					((AdminFacade) (tokenManager.getClientFacadeByToken(token))).getOneCompany(id), HttpStatus.OK);

		} catch (InvalidActionException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("getOneCustomerById")
	public ResponseEntity<?> getOneCustomer(@RequestParam int id,
			@RequestHeader(name = "Token", required = false) String token) {

		try {
			tokenManager.isTokenExist(token);
			return new ResponseEntity<Customer>(
					((AdminFacade) (tokenManager.getClientFacadeByToken(token))).getOneCustomer(id), HttpStatus.OK);

		} catch (InvalidActionException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("updateCompany")
	public ResponseEntity<?> updateCompany(@RequestBody Company company,
			@RequestHeader(name = "Token", required = false) String token) {
		try {
			tokenManager.isTokenExist(token);
			((AdminFacade) (tokenManager.getClientFacadeByToken(token))).updateCompany(company);
			return ResponseEntity.ok().build();
		} catch (InvalidActionException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("updateCustomer")
	public ResponseEntity<?> updateCustomer(@RequestBody Customer customer,
			@RequestHeader(name = "Token", required = false) String token) {
		try {
			tokenManager.isTokenExist(token);
			((AdminFacade) (tokenManager.getClientFacadeByToken(token))).updateCustomer(customer);
			return ResponseEntity.ok().build();
		} catch (InvalidActionException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("deleteCompany")
	public ResponseEntity<?> deleteCompany(@RequestParam int id,
			@RequestHeader(name = "Token", required = false) String token) {
		try {
			tokenManager.isTokenExist(token);
			((AdminFacade) (tokenManager.getClientFacadeByToken(token))).deleteCompany(id);
			return ResponseEntity.ok().build();
		} catch (InvalidActionException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("deleteCustomer")
	public ResponseEntity<?> deleteCustomer(@RequestParam int id,
			@RequestHeader(name = "Token", required = false) String token) {
		try {
			tokenManager.isTokenExist(token);
			((AdminFacade) (tokenManager.getClientFacadeByToken(token))).deleteCustomer(id);
			return ResponseEntity.ok().build();
		} catch (InvalidActionException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}
