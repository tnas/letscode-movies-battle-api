package com.tnas.moviesbattleapi;

import java.util.Arrays;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.tnas.moviesbattleapi.model.OMDbSearch;
import com.tnas.moviesbattleapi.service.MovieService;

@Component
public class OMDbLoader implements ApplicationRunner {

	private static final Integer OMDB_MOVIES_PAGES = 5;
	private static final String OMBD_URL_FORMAT = "http://www.omdbapi.com/?s=\"and\"&page=%d&type=movie&apikey=dad01f0b";
	
	@Autowired
	private MovieService movieService;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		var restTemplate = new RestTemplate();
		
		IntStream.rangeClosed(1, OMDB_MOVIES_PAGES).forEach(p -> {
			var omdbResult = restTemplate.getForObject(String.format(OMBD_URL_FORMAT, p), OMDbSearch.class);
			this.movieService.saveMovies(Arrays.asList(omdbResult.getMovies()));
		});
	}
}
