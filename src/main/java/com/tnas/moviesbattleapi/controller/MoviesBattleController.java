package com.tnas.moviesbattleapi.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tnas.moviesbattleapi.dto.MatchDTO;
import com.tnas.moviesbattleapi.dto.RankDTO;
import com.tnas.moviesbattleapi.service.MatchService;
import com.tnas.moviesbattleapi.service.QuizService;

@RestController
public class MoviesBattleController {
	
	@Autowired
	private QuizService quizService;
	
	@Autowired
	private MatchService matchService;

    @GetMapping("/start")
    public MatchDTO startQuiz(Principal principal) {
        return this.quizService.getNewQuizMatch(principal.getName());
    }
    
    @GetMapping("/next")
    public MatchDTO getNextQuizMatch(Principal principal) {
        return this.quizService.getNewQuizMatch(principal.getName());
    }

    @GetMapping("/stop")
    public String stopQuiz(Principal principal) {
    	this.matchService.removeUnsolvedMatches(principal.getName());
    	SecurityContextHolder.getContext().setAuthentication(null);
        return String.format("Quiz do usuário '%s' foi finalizado com sucesso.", principal.getName());
    }
    
    @GetMapping("/ranking")
    public List<RankDTO> getRanking() {
        return this.quizService.getRanking();
    }

}
