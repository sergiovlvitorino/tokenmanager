package com.sergiovitorino.tokenmanager;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sergiovitorino.tokenmanager.factory.AccountDestructor;

@EnableAutoConfiguration
@SpringBootApplication
public class Start {

	@Autowired
	private AccountDestructor accountDestructor;
	
	public static void main(String[] args) {
		SpringApplication.run(Start.class, args);
	}
	
	@PostConstruct
	public void init(){
		Thread thread = new Thread(accountDestructor);
		thread.start();
	}

}
