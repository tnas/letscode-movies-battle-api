package com.tnas.moviesbattleapi.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tnas.moviesbattleapi.dto.MatchDTO;
import com.tnas.moviesbattleapi.dto.RankDTO;
import com.tnas.moviesbattleapi.model.Match;
import com.tnas.moviesbattleapi.model.Quiz;
import com.tnas.moviesbattleapi.repository.QuizRepository;
import com.tnas.moviesbattleapi.repository.UserRepository;

@Service
public class QuizService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private QuizRepository quizRepository;
	
	public MatchDTO getNewQuizMatch(String username) {
		
		var quizzes = this.quizRepository.findByUsuarioAndErrosLessThanEqual(username, Quiz.MAX_ERRORS_ALLOWED);
		
		if (Objects.nonNull(quizzes)) {
			
			var quiz = quizzes.get(0);
			Match match = quiz.getRodadas().stream().filter(m -> Objects.isNull(m.getRespostaCerta()))
				.findFirst().orElse(null);
			
			var matchDto = new MatchDTO();
			matchDto.setUsuario(username);
			matchDto.setQuizId(quiz.getId());
			matchDto.setIdFilme1(match.getPrimeiroFilme().getId());
			matchDto.setTituloFilme1(match.getPrimeiroFilme().getTitulo());
			matchDto.setIdFilme2(match.getSegundoFilme().getId());
			matchDto.setTituloFilme2(match.getSegundoFilme().getTitulo());
			
			return matchDto;
		}
		else {
			return null;
		}
	}
	
	public List<RankDTO> getRanking() {

		var ranking = new ArrayList<RankDTO>();
		
//		this.userRepository.findAll().stream().forEach(u -> {
//			
//				var answeredQuizzes = this.quizRepository.findByUsuario(u);
//				
//				var numQuizzes = answeredQuizzes.size();
//				
//				var numMatches = answeredQuizzes.stream()
//						.map(Quiz::getRodadas)
//						.flatMap(Collection::stream)
//						.count();
//				
//				var hitMatches = answeredQuizzes.stream()
//						.map(Quiz::getRodadas)
//						.flatMap(Collection::stream)
//						.filter(m -> m.getRespostaCerta())
//						.count();
//				
//				ranking.add(new RankDTO(u.getUsername(),
//						(double)(numQuizzes * (hitMatches / numMatches))));
//			});
		
		return ranking;
		
	}
	

}
