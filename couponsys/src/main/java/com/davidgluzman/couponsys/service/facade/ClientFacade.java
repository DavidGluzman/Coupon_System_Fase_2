package com.davidgluzman.couponsys.service.facade;

import org.springframework.beans.factory.annotation.Autowired;

import com.davidgluzman.couponsys.exceptions.LoginException;
import com.davidgluzman.couponsys.service.services.CompanyService;
import com.davidgluzman.couponsys.service.services.CouponService;
import com.davidgluzman.couponsys.service.services.CustomerService;

import lombok.Data;

@Data
public abstract class ClientFacade {
@Autowired
protected CompanyService companyService;
@Autowired
protected CustomerService customerService;
@Autowired
protected CouponService couponService;

public abstract boolean login(String email,String password) throws LoginException;
}
