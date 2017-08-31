package com.sergiovitorino.tokenmanager.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sergiovitorino.tokenmanager.core.AccountCore;
import com.sergiovitorino.tokenmanager.model.Account;

@RequestMapping("/api")
@RestController
public class AccountController {

	@Autowired
	private AccountCore core;
	
	@RequestMapping(value = { "create" }, method = RequestMethod.GET)
	public Account create(){
		return core.newAccount();
	}
	
	@RequestMapping(value = "validate", method = RequestMethod.POST)
	public ResponseEntity<?> validate(String token){
		if(core.validate(token)){
			return new ResponseEntity<String>(HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(HttpStatus.FORBIDDEN);
		}
	}
	
	@RequestMapping(value = "destroy", method = RequestMethod.GET)
	public ResponseEntity<?> destroy(String token){
		if(core.destroy(token)){
			return new ResponseEntity<String>(HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(HttpStatus.FORBIDDEN);
		}
	}
	
	@RequestMapping(value = { "countActiveAccounts" }, method = RequestMethod.GET)
	public Long countActiveAccounts(){
		return core.countActiveAccounts();
	}
	
}