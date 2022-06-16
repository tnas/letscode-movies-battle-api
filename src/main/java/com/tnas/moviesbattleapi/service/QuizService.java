package com.tnas.moviesbattleapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.tnas.moviesbattleapi.converter.MatchConverter;
import com.tnas.moviesbattleapi.dto.MatchDTO;
import com.tnas.moviesbattleapi.dto.RankDTO;
import com.tnas.moviesbattleapi.model.Quiz;
import com.tnas.moviesbattleapi.repository.QuizRepository;
import com.tnas.moviesbattleapi.repository.UserRepository;

@Service
public class QuizService {

	@Autowired
	private MatchService matchService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private QuizRepository quizRepository;
	
	public MatchDTO getNewQuizMatch(String username) {
		
		var quizzes = this.quizRepository.findByUsuarioAndErrosLessThanEqual(username, Quiz.MAX_ERRORS_ALLOWED);
		
		if (CollectionUtils.isEmpty(quizzes)) { 
			return this.instantiateQuiz(username);
		}
		else { // Encontrado um quiz cujo número máximo de erros não foi atingido.
			var quiz = CollectionUtils.firstElement(quizzes); // Só é permitido haver um único quiz nessa condição
			
			var newQuizMatch = this.matchService.getNewQuizMatch(quiz);
			
			if (Objects.isNull(newQuizMatch)) { // Não foi possível gerar um novo par de filmes
				return this.instantiateQuiz(username);
			}
			else {
				return MatchConverter.getDto(newQuizMatch);
			}
		}
	}
	
	/**
	 * Um novo {@link Quiz} precisa ser gerado quando: (1) Não é encontrado um quiz
	 * cujo número máximo de erros não tenha sido atingido, ou (2) Não foi possível 
	 * gerar um novo par de filmes sem repetições (conforme regras de negócio) para um quiz.
	 * 
	 * @param username - Usuário 
	 * 
	 * @return {@link MatchDTO}
	 */
	private MatchDTO instantiateQuiz(String username) {
		
		var newQuiz = new Quiz();
		
		newQuiz.setUsuario(username);
		newQuiz = this.quizRepository.save(newQuiz);
		
		return MatchConverter.getDto(this.matchService.getNewQuizMatch(newQuiz));
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
