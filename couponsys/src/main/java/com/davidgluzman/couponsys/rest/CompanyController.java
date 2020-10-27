package com.davidgluzman.couponsys.rest;

import java.util.List;

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

import com.davidgluzman.couponsys.beans.Category;
import com.davidgluzman.couponsys.beans.Company;
import com.davidgluzman.couponsys.beans.Coupon;
import com.davidgluzman.couponsys.dto.EmailPasswordDTO;
import com.davidgluzman.couponsys.dto.LoginResponseDTO;
import com.davidgluzman.couponsys.exceptions.AlreadyExistException;
import com.davidgluzman.couponsys.exceptions.InvalidActionException;
import com.davidgluzman.couponsys.facade.CompanyFacade;
import com.davidgluzman.couponsys.security.ClientType;
import com.davidgluzman.couponsys.security.TokenManager;

@RestController
@RequestMapping("company")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class CompanyController extends ClientController {

	@Autowired
	private TokenManager tokenManager;

	@Override
	@PostMapping("login")
	public ResponseEntity<?> login(@RequestBody EmailPasswordDTO dto) {
		HttpHeaders httpHeaders = new HttpHeaders();
		try {
			String token = loginManager.login2(dto.getEmail(), dto.getPassword(), ClientType.Company);
			return ResponseEntity.ok().body(new LoginResponseDTO("logged as company",token));
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
	}

	@PostMapping("addCoupon")
	public ResponseEntity<?> addCoupon(@RequestBody Coupon coupon,
			@RequestHeader(name = "Token", required = false) String token) {
		try {
			tokenManager.isTokenExist(token);
			((CompanyFacade) (tokenManager.getClientFacadeByToken(token))).addCoupon(coupon);
			return ResponseEntity.ok().build();
		} catch (InvalidActionException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (AlreadyExistException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping("getAllCoupons")
	public ResponseEntity<?> getAllCoupons(@RequestHeader(name = "Token", required = false) String token) {
		try {
			tokenManager.isTokenExist(token);
			return new ResponseEntity<List<Coupon>>(
					((CompanyFacade) (tokenManager.getClientFacadeByToken(token))).getAllCoupons(), HttpStatus.OK);
		} catch (InvalidActionException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

	@PutMapping("updateCoupon")
	public ResponseEntity<?> updateCoupon(@RequestBody Coupon coupon,
			@RequestHeader(name = "Token", required = false) String token) {
		try {
			tokenManager.isTokenExist(token);
			((CompanyFacade) (tokenManager.getClientFacadeByToken(token))).updateCoupon(coupon);
			return ResponseEntity.ok().build();
		} catch (InvalidActionException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("deleteCoupon")
	public ResponseEntity<?> deleteCoupon(@RequestParam int id,
			@RequestHeader(name = "Token", required = false) String token) {
		try {
			tokenManager.isTokenExist(token);
			((CompanyFacade) (tokenManager.getClientFacadeByToken(token))).deleteCoupon(id);
			return ResponseEntity.ok().build();
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
					((CompanyFacade) (tokenManager.getClientFacadeByToken(token))).getAllCouponsByCategory(category),
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
					((CompanyFacade) (tokenManager.getClientFacadeByToken(token))).getAllCouponsByPriceLessThan(price),
					HttpStatus.OK);

		} catch (InvalidActionException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("getCompanyDetails")
	public ResponseEntity<?> getCompanyDetails(@RequestHeader(name = "Token", required = false) String token) {
		try {
			tokenManager.isTokenExist(token);
			return new ResponseEntity<Company>(
					((CompanyFacade) (tokenManager.getClientFacadeByToken(token))).getCompanyDetails(), HttpStatus.OK);

		} catch (InvalidActionException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}
