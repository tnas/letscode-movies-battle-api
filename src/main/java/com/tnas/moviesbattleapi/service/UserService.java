package com.tnas.moviesbattleapi.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import com.tnas.moviesbattleapi.config.SecurityConfiguration;
import com.tnas.moviesbattleapi.model.MoviesBattleUser;


@Service
public class UserService {

	@Autowired
	private UserDetailsManager usersManager;
	
	public void addUser(MoviesBattleUser mbUser) {
		this.usersManager.createUser(new User(
			mbUser.username(), 
			PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(mbUser.password()), 
			Arrays.asList(new SimpleGrantedAuthority(SecurityConfiguration.USER_ROLE))));
	}
}
