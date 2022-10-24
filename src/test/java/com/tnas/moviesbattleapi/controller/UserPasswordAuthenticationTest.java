package com.tnas.moviesbattleapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class UserPasswordAuthenticationTest {

	private static final String HOST = "http://localhost:";
	private static final String APP = "/movies-battle";
	private static final String TEST_USER = "shiva";
	private static final String TEST_PASS = "shiva@shiva";
	
	private TestRestTemplate restTemplate;
	
	private String baseUrl;
	
	@LocalServerPort
	private int port;
	
	@BeforeEach
	public void init() throws MalformedURLException {
		this.restTemplate = new TestRestTemplate(TEST_USER, TEST_PASS);
		this.baseUrl = new URL(HOST + String.valueOf(this.port) + APP).toString();
	}
	
    @Test
    void whenLoggedUserRequestsContextPath_ThenSuccess() throws IllegalStateException, IOException {
    	ResponseEntity<String> response = restTemplate.getForEntity(baseUrl, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().contains("shiva"));
    }
    
    @Test
    void whenUserWithWrongPassword_ThenUnauthorized() throws IllegalStateException, IOException {
    	restTemplate = new TestRestTemplate(TEST_USER, "wrongPassword");
    	ResponseEntity<String> response = restTemplate.getForEntity(baseUrl, String.class);
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }
}
