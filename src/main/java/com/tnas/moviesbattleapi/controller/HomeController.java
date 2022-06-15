package com.tnas.moviesbattleapi.controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies-battle")
public class HomeController {

    @GetMapping("/saudacao/{nome}")
    public String saudacao(@PathVariable String nome, ModelMap model) {
        model.addAttribute("nome", nome);

        return "Olá, " +  nome;
    }

}
