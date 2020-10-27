package com.davidgluzman.couponsys.security;

import java.util.Date;

import com.davidgluzman.couponsys.facade.ClientFacade;

import lombok.Data;
@Data
public class CustomSession {
private Date date;
private ClientFacade clientFacade;
}
