package com.sergiovitorino.tokenmanager.repository;

import java.util.Calendar;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.sergiovitorino.tokenmanager.model.Account;

public interface AccountRepository extends CrudRepository<Account, Long>{
	
	Account findByToken(String token);
	
	Account getByTokenAndDestroyedAt(String token, Calendar destroyedAt);
	
	Long countByDestroyedAtNull();
	
	@Query("select a from Account a where modifiedAt<:modifiedAt and destroyedAt is null")
	List<Account> getToDestroy(@Param("modifiedAt") Calendar modifiedAt);

	@Modifying
	@Transactional
	@Query("update Account a set destroyedAt=CURRENT_TIMESTAMP where a.modifiedAt<:modifiedAt and destroyedAt is null")
	Integer invalidateAccount(@Param("modifiedAt") Calendar modifiedAt);
}
