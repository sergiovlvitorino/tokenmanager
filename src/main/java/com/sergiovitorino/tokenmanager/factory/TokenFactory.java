package com.sergiovitorino.tokenmanager.factory;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sergiovitorino.tokenmanager.repository.AccountRepository;

@Component
public class TokenFactory {
	
	@Autowired
	private AccountRepository repository;

	public static final String[] VALUES = "ABCDEFGHIJLMNOPQRSTUVXYZ0123456789".split("");
	public static final Integer TOTAL_VALUES = 35;
	
	private Random random = new Random();
	
	public String generate(){
		StringBuilder token = new StringBuilder();
		while(token.length() != TOTAL_VALUES)
			token.append(VALUES[random.nextInt(VALUES.length)]);
		return token.toString();
	}
	
	public String newToken(){
		String token = generate();
		while(repository.getByToken(token) != null){
			token = generate();
		}
		return token;
	}
	
}
