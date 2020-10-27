package com.davidgluzman.couponsys.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.davidgluzman.couponsys.exceptions.InvalidActionException;
import com.davidgluzman.couponsys.facade.ClientFacade;

@Component
public class TokenManager {
	private Map<String, CustomSession> tokenMap = new HashMap<>();

	private final long deleteTime = 1000 * 60 * 30;

	public String addToken(ClientFacade clientFacade) {
		String token = UUID.randomUUID().toString();
		CustomSession customSession = new CustomSession();
		customSession.setDate(new Date());
		customSession.setClientFacade(clientFacade);
		tokenMap.put(token, customSession);
		return token;
	}

	public void deleteToken(String token) {
		tokenMap.remove(token);
	}

	public boolean isTokenExist(String token) throws InvalidActionException {
		if (tokenMap.get(token) != null) {
			return true;
		}
		throw new InvalidActionException("Token doesnt exist");
	}

	public void deleteExpiredTokens() {
		for (Map.Entry<String, CustomSession> entry : tokenMap.entrySet()) {
			if (System.currentTimeMillis() - entry.getValue().getDate().getTime() > deleteTime)
				tokenMap.remove(entry.getKey());

		}
	}
	public ClientFacade getClientFacadeByToken(String token) {
		return tokenMap.get(token).getClientFacade(); 
		

		
	}
	
	

}
