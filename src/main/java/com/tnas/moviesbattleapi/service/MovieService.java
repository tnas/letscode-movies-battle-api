package com.tnas.moviesbattleapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.tnas.moviesbattleapi.model.Movie;

@Service
public class MovieService {

	@Autowired
	private JpaRepository<Movie, String> movieRepository;
	
	public void saveMovies(List<Movie> movies) {
		this.movieRepository.saveAll(movies);
	}
	
	public List<Movie> getAllMovies() {
		return this.movieRepository.findAll();
	}

}
