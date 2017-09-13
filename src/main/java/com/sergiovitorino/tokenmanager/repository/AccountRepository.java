package com.sergiovitorino.tokenmanager.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sergiovitorino.tokenmanager.model.Account;

public interface AccountRepository extends CrudRepository<Account, String>{

	List<Account> findByDestroyedAtIsNull();

	Account findByTokenAndDestroyedAtIsNull(String token);

}
