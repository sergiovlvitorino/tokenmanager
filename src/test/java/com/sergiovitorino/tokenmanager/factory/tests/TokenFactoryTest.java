package com.sergiovitorino.tokenmanager.factory.tests;

import org.junit.Assert;
import org.junit.Test;

import com.sergiovitorino.tokenmanager.factory.TokenFactory;

public class TokenFactoryTest {

	@Test
	public void testIfGeneratedTokenIsNotEmpty(){
		String token = new TokenFactory().generate();
		Assert.assertNotNull(token);
	}
	
}
