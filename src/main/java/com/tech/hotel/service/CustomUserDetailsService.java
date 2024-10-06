package com.tech.hotel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tech.hotel.exception.OurException;
import com.tech.hotel.rep.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
@Autowired
private UserRepository userRepository;

public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
	return userRepository.findByEmail(username).orElseThrow(()-> new OurException("\"Username/Email not Found\""));
}
}
