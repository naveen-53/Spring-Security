package com.example.websecurity.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.websecurity.model.UserPrincipal;
import com.example.websecurity.model.Users;
import com.example.websecurity.repo.UserRepo;

@Service
public class MyUserDetailsService implements UserDetailsService {
	
	Logger log = LoggerFactory.getLogger(MyUserDetailsService.class);
	
	@Autowired
	private UserRepo repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = repo.findByUsername(username);
		
		 
		if(user == null) {
			log.error("User Not Found");
			throw new UsernameNotFoundException("User not found");
		}
		
		return new UserPrincipal(user);
	}

}
