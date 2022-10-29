package com.tnas.moviesbattleapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
	
	@Order(1)
    @Test
    void whenLoggedUserRequestsContextPath_ThenSuccess() throws IllegalStateException, IOException {
    	ResponseEntity<String> response = restTemplate.getForEntity(baseUrl, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().contains("shiva"));
    }
    
	@Order(2)
    @Test
    void whenUserWithWrongPassword_ThenUnauthorized() throws IllegalStateException, IOException {
    	restTemplate = new TestRestTemplate(TEST_USER, "wrongPassword");
    	ResponseEntity<String> response = restTemplate.getForEntity(baseUrl, String.class);
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }
    
    @Order(3)
    @Test
    void whenCreateNewUser_ThenAuthorizedSuccess() throws IllegalStateException, IOException, JSONException {
    	
    	var httpHeaders = new HttpHeaders();
    	httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    	
    	var jsonUser = new JSONObject();
    	jsonUser.put("username", "tnas");
    	jsonUser.put("password", "tnas@tnas");
    	
    	var request = new HttpEntity<String>(jsonUser.toString(), httpHeaders);
    	
    	restTemplate = new TestRestTemplate();
    	var response = restTemplate.postForEntity(baseUrl.concat("/signup"), request, String.class);
    	
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().contains("tnas"));
        
        restTemplate = new TestRestTemplate("tnas", "tnas@tnas");
    	var getResponse = restTemplate.getForEntity(baseUrl, String.class);
        assertEquals(HttpStatus.OK, getResponse.getStatusCode());
    }
}
