package com.tnas.moviesbattleapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tnas.moviesbattleapi.model.Movie;

public interface MovieRepository extends JpaRepository<Movie, String> {

}
