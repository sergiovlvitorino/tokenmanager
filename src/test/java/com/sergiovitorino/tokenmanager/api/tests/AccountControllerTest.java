package com.sergiovitorino.tokenmanager.api.tests;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sergiovitorino.tokenmanager.model.Account;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AccountControllerTest {

	@Autowired
	private ObjectMapper mapper;

	@Autowired
	private TestRestTemplate restTemplete;

	@LocalServerPort
	private Integer port;

	@Test
	public void testIfCreateIsOk() {
		String result = this.restTemplete.getForObject("http://localhost:" + port + "/api/create", String.class);
		assertNotNull(result);
	}

	@Test
	public void testIfCheckReturnsOkWhenAccessTokenIsOk() throws Exception {
		ResponseEntity<?> responseEntityExpected = new ResponseEntity<String>(HttpStatus.OK);
		String accountString = this.restTemplete.getForObject("http://localhost:" + port + "/api/create", String.class);
		Account account = mapper.readValue(accountString, Account.class);
		ResponseEntity<?> responseEntityActual = this.restTemplete
				.getForEntity("http://localhost:" + port + "/api/check/" + account.getToken(), ResponseEntity.class);
		assertEquals(responseEntityExpected.getStatusCode(), responseEntityActual.getStatusCode());
	}

	@Test
	public void testIfCheckReturnsForbiddenWhenAccessTokenHasBeenDestroyed() throws Exception {
		ResponseEntity<?> responseEntityExpected = new ResponseEntity<String>(HttpStatus.FORBIDDEN);
		
		String accountString = this.restTemplete.getForObject("http://localhost:" + port + "/api/create", String.class);
		Account account = mapper.readValue(accountString, Account.class);
		
		this.restTemplete.delete("http://localhost:" + port + "/api/destroy/" + account.getToken());
		
		ResponseEntity<?> responseEntityActual = this.restTemplete.getForEntity("http://localhost:" + port + "/api/check/" + account.getToken(), ResponseEntity.class);
		assertEquals(responseEntityExpected.getStatusCode(), responseEntityActual.getStatusCode());
	}
	
	@Test
	public void testIfDestroyIsOk() throws Exception {
		
		String accountString = this.restTemplete.getForObject("http://localhost:" + port + "/api/create", String.class);
		Account account = mapper.readValue(accountString, Account.class);
		
		this.restTemplete.delete("http://localhost:" + port + "/api/destroy/" + account.getToken());
		
		String accountUpdatedString = this.restTemplete.getForObject("http://localhost:" + port + "/api/find/" + account.getToken(), String.class);
		Account accountUpdated = mapper.readValue(accountUpdatedString, Account.class);
		
		assertNotNull(accountUpdated.getDestroyedAt());
		
	}

	@Test
	public void testIfCheckReturnsForbiddenWhenAccessTokenIsNotOk() throws Exception {
		ResponseEntity<?> responseEntityExpected = new ResponseEntity<String>(HttpStatus.FORBIDDEN);
		ResponseEntity<?> responseEntityActual = this.restTemplete
				.getForEntity("http://localhost:" + port + "/api/check/AAAAA", ResponseEntity.class);
		assertEquals(responseEntityExpected.getStatusCode(), responseEntityActual.getStatusCode());
	}

	@Test
	public void testIfRefreshIsOk() throws Exception {
		String accountString = this.restTemplete.getForObject("http://localhost:" + port + "/api/create", String.class);
		Account account = mapper.readValue(accountString, Account.class);
				
		this.restTemplete.put("http://localhost:" + port + "/api/refresh/" + account.getToken(), null);
		
		String accountUpdatedString = this.restTemplete.getForObject("http://localhost:" + port + "/api/find/" + account.getToken(), String.class);
		Account accountUpdated = mapper.readValue(accountUpdatedString, Account.class);

		assertNotEquals(account.getModifiedAt().getTimeInMillis(), accountUpdated.getModifiedAt().getTimeInMillis());
		assertNull(accountUpdated.getDestroyedAt());
	}

}
