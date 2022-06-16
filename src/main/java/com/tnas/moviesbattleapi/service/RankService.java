package com.tnas.moviesbattleapi.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tnas.moviesbattleapi.model.Quiz;
import com.tnas.moviesbattleapi.model.Rank;
import com.tnas.moviesbattleapi.repository.RankRepository;

@Service
public class RankService {

	@Autowired
	private QuizService quizService;
	
	@Autowired
	private RankRepository rankRepository;
	
	
	public List<Rank> getRanking() {
		return this.rankRepository.findAllByOrderByPontuacaoDesc();
	}
	
	public void createRank(String username) {
		var rank = new Rank();
		rank.setUsername(username);
		rank.setPontuacao(0d);
		this.rankRepository.save(rank);
	}

	public void updateRank(String username) {
		
		var userQuizzes = this.quizService.getUserQuizzes(username);
		
		var numQuizzes = userQuizzes.size();
			
		var numMatches = userQuizzes.stream()
				.map(Quiz::getRodadas)
				.flatMap(Collection::stream)
				.count();
			
		var hitMatches = userQuizzes.stream()
				.map(Quiz::getRodadas)
				.flatMap(Collection::stream)
				.filter(m -> m.getRespostaCerta())
				.count();
		
		var rank = this.rankRepository.findById(username).orElse(null);
		rank.setPontuacao((double)(numQuizzes * (hitMatches / numMatches)));
		this.rankRepository.save(rank);
	}
}
