package com.sergiovitorino.tokenmanager.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.sergiovitorino.tokenmanager.core.AccountCore;

@Async
@Component
public class AccountDestructor implements Runnable{

	@Autowired
	private AccountCore core;
	
	@Value("${tokenmanager.interval}")
	public Long interval;
	
	public void run() {
		while(true){
			core.invalidateAccounts(interval);
			try {
				Thread.sleep(interval);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}
