package com.davidgluzman.couponsys.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.davidgluzman.couponsys.beans.Category;
import com.davidgluzman.couponsys.beans.Company;
import com.davidgluzman.couponsys.beans.Coupon;
import com.davidgluzman.couponsys.beans.Customer;
import com.davidgluzman.couponsys.dto.EmailPasswordDTO;
import com.davidgluzman.couponsys.dto.IdDTO;
import com.davidgluzman.couponsys.dto.LoginResponseDTO;
import com.davidgluzman.couponsys.exceptions.AlreadyExistException;
import com.davidgluzman.couponsys.exceptions.InvalidActionException;
import com.davidgluzman.couponsys.facade.CompanyFacade;
import com.davidgluzman.couponsys.facade.CustomerFacade;
import com.davidgluzman.couponsys.security.ClientType;
import com.davidgluzman.couponsys.security.TokenManager;

@RestController
@RequestMapping("customer")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class CustomerController extends ClientController {

	@Autowired
	private TokenManager tokenManager;

	@Override
	@PostMapping("login")
	public ResponseEntity<?> login(@RequestBody EmailPasswordDTO dto) {
		HttpHeaders httpHeaders = new HttpHeaders();
		try {
			String token = loginManager.login2(dto.getEmail(), dto.getPassword(), ClientType.Customer);
			return ResponseEntity.ok().body(new LoginResponseDTO("logged as customer",token));
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("addCouponPurchase")
	public ResponseEntity<?> addCouponPurchase(@RequestBody IdDTO dto,
			@RequestHeader(name = "Token", required = false) String token) {
		try {
			tokenManager.isTokenExist(token);
			((CustomerFacade) (tokenManager.getClientFacadeByToken(token))).addCouponPurchase(dto.getId());
			return ResponseEntity.ok().build();
		} catch (InvalidActionException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping("getAllCoupons")
	public ResponseEntity<?> getAllCoupons(@RequestHeader(name = "Token", required = false) String token) {
		try {
			tokenManager.isTokenExist(token);
			return new ResponseEntity<List<Coupon>>(
					((CustomerFacade) (tokenManager.getClientFacadeByToken(token))).getAllCoupons(), HttpStatus.OK);
		} catch (InvalidActionException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping("getAllCouponsByCategory")
	public ResponseEntity<?> getAllCouponsByCategory(@RequestParam Category category,
			@RequestHeader(name = "Token", required = false) String token) {
		try {
			tokenManager.isTokenExist(token);
			return new ResponseEntity<List<Coupon>>(
					((CustomerFacade) (tokenManager.getClientFacadeByToken(token))).getAllCouponsByCategory(category),
					HttpStatus.OK);

		} catch (InvalidActionException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("getAllCouponsByPriceLessThan")
	public ResponseEntity<?> getAllCouponsByPriceLessThan(@RequestParam int price,
			@RequestHeader(name = "Token", required = false) String token) {
		try {
			tokenManager.isTokenExist(token);
			return new ResponseEntity<List<Coupon>>(
					((CustomerFacade) (tokenManager.getClientFacadeByToken(token))).getAllCouponsByPriceLessThan(price),
					HttpStatus.OK);

		} catch (InvalidActionException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("getCustomerDetails")
	public ResponseEntity<?> getCustomerDetails(@RequestHeader(name = "Token", required = false) String token) {
		try {
			tokenManager.isTokenExist(token);
			return new ResponseEntity<Customer>(
					((CustomerFacade) (tokenManager.getClientFacadeByToken(token))).getCustomerDetails(),
					HttpStatus.OK);

		} catch (InvalidActionException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}
