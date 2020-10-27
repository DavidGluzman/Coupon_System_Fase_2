package com.davidgluzman.couponsys.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.davidgluzman.couponsys.exceptions.LoginException;
import com.davidgluzman.couponsys.facade.AdminFacade;
import com.davidgluzman.couponsys.facade.ClientFacade;
import com.davidgluzman.couponsys.facade.CompanyFacade;
import com.davidgluzman.couponsys.facade.CustomerFacade;


@Service
public class LoginManager {
@Autowired
AdminFacade adminFacade;
@Autowired
CustomerFacade customerFacade;
@Autowired
CompanyFacade companyFacade;
@Autowired
TokenManager tokenManager;
	
	

	public ClientFacade login(String email, String password, ClientType clientType) throws LoginException {
		if (clientType == ClientType.Administrator && adminFacade.login(email, password) == true) {
			return adminFacade;
		} else if (clientType == ClientType.Customer && customerFacade.login(email, password) == true) {
			return customerFacade;
		} else if (clientType == ClientType.Company && companyFacade.login(email, password) == true) {
			return companyFacade;
		}else {
		return null;
		}
	}
	public String login2(String email, String password, ClientType clientType) throws LoginException {
		if (clientType == ClientType.Administrator && adminFacade.login(email, password) == true) {
			return tokenManager.addToken(adminFacade);
		} else if (clientType == ClientType.Customer && customerFacade.login(email, password) == true) {
			return tokenManager.addToken(customerFacade);
		} else if (clientType == ClientType.Company && companyFacade.login(email, password) == true) {
			return tokenManager.addToken(companyFacade);
		}else {
		return null;
		}
	}
	
}


