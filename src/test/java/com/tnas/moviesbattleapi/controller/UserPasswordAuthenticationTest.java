package com.tnas.moviesbattleapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class UserPasswordAuthenticationTest {

	@Autowired
	private TestRestTemplate restTemplate;
	
	private URL base;
	
	@LocalServerPort
	private int port;
	
	@BeforeEach
	public void setUp() throws MalformedURLException {
		this.base = new URL("http://localhost:".concat(String.valueOf(this.port).concat("/movies-battle/start")));
	}
	
    @Test
    void whenLoggedUserRequestsContextPath_ThenSuccess() throws IllegalStateException, IOException {
    	
    	ResponseEntity<String> response = restTemplate.
    			withBasicAuth("shiva", "shiva@shiva").getForEntity(base.toString(), String.class);
 
        assertEquals(HttpStatus.OK, response.getStatusCode(), base.toString());
        System.out.println(response);
        assertTrue(response.getBody().contains("shiva"));
    }
}
