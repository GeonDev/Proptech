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

	@Autowired
	private LoginHistoryRepository loginHistoryRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if(user == null) {
			LOGGER.debug("Fail Login");
			return null;
		}else {
			LOGGER.debug("success Login");

			//로그인 히스토리 기록
			LoginHistory loginHistory = LoginHistory.builder()
					.loginIp(CommonUtil.getUserIp())
					.user(user)
					.isLogin(true)
					.loginDate(LocalDateTime.now())
					.build();

			loginHistoryRepository.save(loginHistory);


			return new PrincipalDetails(user);
		}
	}

}
