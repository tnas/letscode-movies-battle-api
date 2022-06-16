package com.tnas.moviesbattleapi.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tnas.moviesbattleapi.model.Match;
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
		
		var unsolvedMatch = this.matchRepository.findUnsolvedMatch(quiz.getUsuario());
		
		if (Objects.nonNull(unsolvedMatch)) {
			return unsolvedMatch;
		}
		
		var movies = this.movieRepository.findAll();
		
		for (int stIdx = 0; stIdx < movies.size(); ++ stIdx) {
			
			for (int ndIdx = 0; ndIdx < movies.size(); ++ ndIdx) {
				
				if (stIdx == ndIdx) continue;
				
				final int st = stIdx;
				final int nd = ndIdx;
				
				if (quiz.getRodadas().stream()
						.noneMatch(m -> m.isEqualPairMovies(movies.get(st), movies.get(nd)))) {
					
					var newMatch = new Match();
					newMatch.setPartida(quiz);
					newMatch.setPrimeiroFilme(movies.get(st));
					newMatch.setSegundoFilme(movies.get(nd));
					
					return this.matchRepository.save(newMatch);
				}
			}
		}
		
		return null;
	}
	
	public void removeUnsolvedMatches(String username) {
		this.matchRepository.deleteUnsolvedMatches(username);
	}
}
