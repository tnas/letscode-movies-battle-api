package com.tnas.moviesbattleapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.tnas.moviesbattleapi.model.Match;
import com.tnas.moviesbattleapi.model.Quiz;

public interface MatchRepository extends JpaRepository<Match, Long> {

	public List<Match> findByPartida(Quiz partida);
	
	@Modifying
	@Transactional
	@Query("delete from Match m where m.respostaCerta is null and m.partida in (select q from Quiz q where q.usuario = :username)")
	void deleteUnsolvedMatches(String username);
	
	@Query("from Match m where m.respostaCerta is null and m.partida in (select q from Quiz q where q.usuario = :username)")
	Match findUnsolvedMatch(String username);
}
