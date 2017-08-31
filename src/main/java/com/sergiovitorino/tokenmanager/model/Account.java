package com.sergiovitorino.tokenmanager.model;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Account {

	@JsonIgnore
	@Id
	@GeneratedValue
	private Long id;
	
	@NotNull
	private String token;
	
	@NotNull
	private Calendar createdAt;
	
	private Calendar modifiedAt;
	
	private Calendar destroyedAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Calendar getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Calendar createdAt) {
		this.createdAt = createdAt;
	}

	public Calendar getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(Calendar modifiedAt) {
		this.modifiedAt = modifiedAt;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o != null && o instanceof Account){
			Account account = (Account) o;
			return this.id.equals(account.getId());
		}
		return false;
	}

	public Calendar getDestroyedAt() {
		return destroyedAt;
	}

	public void setDestroyedAt(Calendar destroyedAt) {
		this.destroyedAt = destroyedAt;
	}
	
}
