package com.tnas.moviesbattleapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tnas.moviesbattleapi.model.Match;
import com.tnas.moviesbattleapi.model.Quiz;

public interface MatchRepository extends JpaRepository<Match, Long> {

	public List<Match> findByPartida(Quiz partida);
}
