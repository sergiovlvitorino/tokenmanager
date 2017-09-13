package com.sergiovitorino.tokenmanager.core;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sergiovitorino.tokenmanager.factory.TokenFactory;
import com.sergiovitorino.tokenmanager.model.Account;
import com.sergiovitorino.tokenmanager.repository.AccountRepository;

@Component
public class AccountCore {

	@Autowired
	private AccountRepository repository;
	
	@Autowired
	private TokenFactory tokenFactory;
	
	public Account newAccount(){
		Account account = new Account();
		account.setToken(tokenFactory.newToken());
		account.setCreatedAt(Calendar.getInstance());
		account.setModifiedAt(Calendar.getInstance());
		repository.save(account);
		return account;
	}
	
	public Boolean refresh(String token){
		Account account = repository.findByTokenAndDestroyedAtIsNull(token);
		if(account != null){
			account.setModifiedAt(Calendar.getInstance());
			repository.save(account);
			return true;
		}
		return false;
	}

	public Boolean destroy(String token) {
		Account account = repository.findOne(token);
		if(account != null && account.getDestroyedAt() == null){
			account.setDestroyedAt(Calendar.getInstance());
			repository.save(account);
			return true;
		}
		return false;
	}

	public void invalidateAccounts(long interval) {
		Calendar modifiedAt = Calendar.getInstance();
		modifiedAt.setTimeInMillis(modifiedAt.getTimeInMillis() - interval);
		List<Account> accounts = repository.findByDestroyedAtIsNull();
		for (Account account : accounts) {
			if(account.getModifiedAt().before(modifiedAt)) {
				account.setDestroyedAt(Calendar.getInstance());
				repository.save(account);
			}
		}
	}

	public Boolean check(String token) {
		return repository.findByTokenAndDestroyedAtIsNull(token) != null;
	}
	
	public Account findByToken(String token) {
		return repository.findOne(token);
	}
	
}

