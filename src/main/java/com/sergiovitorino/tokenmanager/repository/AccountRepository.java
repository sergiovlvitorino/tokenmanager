package com.sergiovitorino.tokenmanager.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sergiovitorino.tokenmanager.model.Account;

public interface AccountRepository extends MongoRepository<Account, String>{

	List<Account> findByDestroyedAtIsNull();

	Account findByTokenAndDestroyedAtIsNull(String token);

}
