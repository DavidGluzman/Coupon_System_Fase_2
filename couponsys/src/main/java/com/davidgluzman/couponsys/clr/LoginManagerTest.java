package com.davidgluzman.couponsys.clr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.davidgluzman.couponsys.exceptions.LoginException;
import com.davidgluzman.couponsys.security.ClientType;
import com.davidgluzman.couponsys.security.LoginManager;
import com.davidgluzman.couponsys.service.facade.AdminFacade;
import com.davidgluzman.couponsys.utils.PrintStringUtils;
@Component
@Order(7)
public class LoginManagerTest implements CommandLineRunner{
@Autowired
LoginManager loginManager;
@Autowired
AdminFacade adminFacade;
@Override
public void run(String... args) throws Exception {
	loginManager.login("admin@admin.com", "admin", ClientType.Administrator);
	loginManager.login("service@abidas.com", "pass", ClientType.Company);
	loginManager.login("moshemoshe@email.com", "pass", ClientType.Customer);
	try {
		loginManager.login("admin@admin.com", "admin", ClientType.Company);
	} catch (LoginException e) {
		PrintStringUtils.printInitiatedException(e.getMessage(), "testing admin login");
	}
	try {
		
		loginManager.login("service@abidas.com", "pass", ClientType.Customer);
	} catch (LoginException e) {
		PrintStringUtils.printInitiatedException(e.getMessage(), "testing company login");
	}
	try {
		loginManager.login("moshemoshe@email.com", "pass", ClientType.Administrator);
		
	} catch (LoginException e) {
		PrintStringUtils.printInitiatedException(e.getMessage(), "testing customer login");
	}
	
	
}


}
