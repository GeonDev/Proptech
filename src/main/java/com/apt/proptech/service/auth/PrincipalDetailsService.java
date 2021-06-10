package com.apt.proptech.service.auth;

import com.apt.proptech.domain.User;
import com.apt.proptech.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class PrincipalDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		return new PrincipalDetails(userRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException(username)));
	}

}