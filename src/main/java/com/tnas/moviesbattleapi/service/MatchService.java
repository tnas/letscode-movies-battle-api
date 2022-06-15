package com.tnas.moviesbattleapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tnas.moviesbattleapi.model.Match;
import com.tnas.moviesbattleapi.model.Movie;
import com.tnas.moviesbattleapi.model.Quiz;
import com.tnas.moviesbattleapi.repository.MatchRepository;
import com.tnas.moviesbattleapi.repository.MovieRepository;

@Service
public class MatchService {

	@Autowired
	private MatchRepository matchRepository;
	
	@Autowired
	private MovieRepository movieRepository;
	
	public Match getNewQuizMatch(Quiz quiz) {
		
		var availableMovies = this.movieRepository.findAll().toArray(new Movie[0]);
		
		for (int stIdx = 0; stIdx < availableMovies.length; ++ stIdx) {
			
			for (int ndIdx = 0; ndIdx < availableMovies.length; ++ ndIdx) {
				
				if (stIdx == ndIdx) continue;
				
				
			}
		}
		
		
		
		return null;
	}
}
