package com.apt.proptech.service.oauth;

import com.apt.proptech.domain.LoginHistory;
import com.apt.proptech.domain.User;
import com.apt.proptech.domain.oauth.PrincipalDetails;
import com.apt.proptech.repository.LoginHistoryRepository;
import com.apt.proptech.repository.UserRepository;
import com.apt.proptech.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class PrincipalDetailsService implements UserDetailsService{

	static final Logger LOGGER = LoggerFactory.getLogger(PrincipalDetailsService.class);

	@Autowired
	private UserRepository userRepository;



	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if(user == null) {
			LOGGER.info("Fail -> CAN NOT FIND USER INFO");
			return null;
		}else {
			LOGGER.info("SUCCESS-> FIND USER INFO");
			return new PrincipalDetails(user);
		}
	}

}
