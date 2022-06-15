package com.tnas.moviesbattleapi.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tnas.moviesbattleapi.dto.RankDTO;
import com.tnas.moviesbattleapi.service.QuizService;

@RestController
public class MoviesBattleController {
	
	@Autowired
	private QuizService quizService;

//    @GetMapping("/saudacao/{nome}")
//    public String saudacao(@PathVariable String nome, ModelMap model) {
//        model.addAttribute("nome", nome);
//
//        return "Olá, " +  nome;
//    }
    
    @GetMapping("/start")
    public String startQuiz(Principal principal) {
        return "Hello! " + principal.getName();
    }

    @GetMapping("/stop")
    public String stopQuiz() {
        return "Hello Admin";
    }
    
    @GetMapping("/ranking")
    public List<RankDTO> getRanking() {
        return this.quizService.getRanking();
    }

}
