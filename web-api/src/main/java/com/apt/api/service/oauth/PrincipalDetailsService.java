package com.apt.api.service.oauth;

import com.apt.api.domain.User;
import com.apt.api.domain.oauth.PrincipalDetails;
import com.apt.api.repository.LoginHistoryRepository;
import com.apt.api.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class PrincipalDetailsService implements UserDetailsService{

	static final Logger LOGGER = LoggerFactory.getLogger(PrincipalDetailsService.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private LoginHistoryRepository loginHistoryRepository;


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if(user == null) {
			LOGGER.info("Fail -> CAN NOT FIND USER INFO");
			return null;
		}else {
			
			LOGGER.info("SUCCESS-> FIND USER INFO");
			//로그인 처리 이후 승인된 IP인지 확인하기 위한 데이터
			user.setLoginHistoryList(loginHistoryRepository.findByUserOrderByIdDesc(user));
			return new PrincipalDetails(user);
		}
	}

}
