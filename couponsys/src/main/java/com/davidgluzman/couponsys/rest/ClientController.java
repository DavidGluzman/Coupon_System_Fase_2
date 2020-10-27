package com.davidgluzman.couponsys.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.davidgluzman.couponsys.dto.EmailPasswordDTO;
import com.davidgluzman.couponsys.security.LoginManager;



public abstract class ClientController {
	
	@Autowired
	LoginManager loginManager;
//	@Autowired
//	TokenManager tokenManager;

	public abstract ResponseEntity<?> login(EmailPasswordDTO dto);
}
