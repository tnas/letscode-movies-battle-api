package com.tnas.moviesbattleapi.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class MoviesBattleControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	@WithMockUser(username = "shiva", password = "shiva@shiva")
	public void testStartQuiz() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/movies-battle/start")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
}
