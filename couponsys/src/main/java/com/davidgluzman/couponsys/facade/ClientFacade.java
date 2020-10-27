package com.davidgluzman.couponsys.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.davidgluzman.couponsys.DBDAO.CompanyDBDAO;
import com.davidgluzman.couponsys.DBDAO.CouponDBDAO;
import com.davidgluzman.couponsys.DBDAO.CustomerDBDAO;
import com.davidgluzman.couponsys.exceptions.LoginException;

import lombok.Data;

@Data
public abstract class ClientFacade {
@Autowired
protected CompanyDBDAO companyDBDAO;
@Autowired
protected CustomerDBDAO customerDBDAO;
@Autowired
protected CouponDBDAO couponDBDAO;

public abstract boolean login(String email,String password) throws LoginException;
}
