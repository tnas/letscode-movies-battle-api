package com.tnas.moviesbattleapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tnas.moviesbattleapi.model.Rank;

public interface RankRepository extends JpaRepository<Rank, String> {

	List<Rank> findAllByOrderByPontuacaoDesc();
}
