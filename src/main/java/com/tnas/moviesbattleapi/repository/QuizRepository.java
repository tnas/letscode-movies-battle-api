package com.tnas.moviesbattleapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tnas.moviesbattleapi.model.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Long> {

	List<Quiz> findByUsuarioAndErrosLessThanEqual(String usuario, Integer erros);
}
