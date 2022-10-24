package com.tnas.moviesbattleapi.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tnas.moviesbattleapi.dto.MatchDTO;
import com.tnas.moviesbattleapi.dto.SolutionMatchDTO;
import com.tnas.moviesbattleapi.model.Rank;
import com.tnas.moviesbattleapi.service.MatchService;
import com.tnas.moviesbattleapi.service.QuizService;
import com.tnas.moviesbattleapi.service.RankService;

@RestController
public class MoviesBattleController {
	
	@Autowired
	private QuizService quizService;
	
	@Autowired
	private MatchService matchService;
	
	@Autowired
	private RankService rankService;

	@GetMapping("/")
    public String login(Principal principal) {
        return principal.getName();
    }
	
    @GetMapping("/start")
    public MatchDTO startQuiz(Principal principal) {
    	this.rankService.createRank(principal.getName());
        return this.quizService.getNewQuizMatch(principal.getName());
    }
    
    @PostMapping("/solve")
    public String solveMatch(Principal principal, @RequestBody SolutionMatchDTO solution) {
    	
    	var solutionMessage = this.matchService.solveMatch(solution) ?
    			"Parabéns! Você acerto o filme com maior pontuação." :
    				"Infelizmente você não acertou o filme com maior pontuação";
    	
    	this.rankService.updateRank(principal.getName());
    	
    	return solutionMessage;
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
    public List<Rank> getRanking() {
        return this.rankService.getRanking();
    }

}
