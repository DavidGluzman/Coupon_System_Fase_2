package com.davidgluzman.couponsys.service.facade;

import com.davidgluzman.couponsys.exceptions.LoginException;
import com.davidgluzman.couponsys.service.services.CompanyService;
import com.davidgluzman.couponsys.service.services.CouponService;
import com.davidgluzman.couponsys.service.services.CustomerService;

import lombok.Data;

@Data
public abstract class ClientFacade {
protected CompanyService companyService;
protected CustomerService customerService;
protected CouponService couponService;

public abstract boolean login(String email,String password) throws LoginException;
}
