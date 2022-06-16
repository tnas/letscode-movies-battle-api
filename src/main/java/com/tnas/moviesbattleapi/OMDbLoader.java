package com.tnas.moviesbattleapi;

import java.util.ArrayList;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.tnas.moviesbattleapi.model.Movie;
import com.tnas.moviesbattleapi.model.OMDbSearch;
import com.tnas.moviesbattleapi.service.MovieService;

@Component
public class OMDbLoader implements ApplicationRunner {

	private static final Integer OMDB_MOVIES_PAGES = 5;
	private static final String OMBD_FIND_BY_PATTERN_QUERY = "http://www.omdbapi.com/?s=\"and\"&page=%d&type=movie&apikey=dad01f0b";
	private static final String OMBD_FIND_BY_ID_QUERY = "http://www.omdbapi.com/?i=%s&apikey=dad01f0b";
	
	@Autowired
	private MovieService movieService;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		var restTemplate = new RestTemplate();
		var moviesCache = new ArrayList<Movie>();
		
		IntStream.rangeClosed(1, OMDB_MOVIES_PAGES).forEach(p -> {
			
			moviesCache.clear();
			var omdbResult = restTemplate.getForObject(String.format(OMBD_FIND_BY_PATTERN_QUERY, p), OMDbSearch.class);
			
			Stream.of(omdbResult.getMovies()).forEach(r -> {
				var movie = restTemplate.getForObject(String.format(OMBD_FIND_BY_ID_QUERY, r.getId()), Movie.class);
				movie.calcularPontuacao();
				moviesCache.add(movie);
			});
			
			this.movieService.saveMovies(moviesCache);
		});
	}
}

