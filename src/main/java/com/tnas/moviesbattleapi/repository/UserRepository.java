package com.tnas.moviesbattleapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tnas.moviesbattleapi.model.User;

public interface UserRepository extends JpaRepository<User, String> {

}
