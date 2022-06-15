package com.tnas.moviesbattleapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.tnas.moviesbattleapi.model.User;

@Service
public class UserService {

	@Autowired
	private JpaRepository<User, String> userRepository;
	
	public User getUserByUsername(String username) {
		return this.userRepository.findById(username).orElse(null);
	}
}
