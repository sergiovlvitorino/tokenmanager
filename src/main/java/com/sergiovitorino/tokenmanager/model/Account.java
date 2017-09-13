package com.sergiovitorino.tokenmanager.model;

import java.util.Calendar;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Account {
	
	@Id
	@NotNull
	private String token;
	
	@NotNull
	private Calendar createdAt;
	
	private Calendar modifiedAt;
	
	private Calendar destroyedAt;

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
	
	public Calendar getDestroyedAt() {
		return destroyedAt;
	}

	public void setDestroyedAt(Calendar destroyedAt) {
		this.destroyedAt = destroyedAt;
	}
	
}
