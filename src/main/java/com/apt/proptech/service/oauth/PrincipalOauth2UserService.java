package com.apt.proptech.service.oauth;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

import com.apt.proptech.domain.LoginHistory;
import com.apt.proptech.domain.User;
import com.apt.proptech.domain.enums.UserRole;
import com.apt.proptech.domain.oauth.FaceBookUserInfo;
import com.apt.proptech.domain.oauth.GoogleUserInfo;
import com.apt.proptech.domain.oauth.NaverUserInfo;
import com.apt.proptech.domain.oauth.OAuth2UserInfo;
import com.apt.proptech.repository.LoginHistoryRepository;
import com.apt.proptech.repository.UserRepository;
import com.apt.proptech.domain.oauth.PrincipalDetails;

import com.apt.proptech.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;


@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private LoginHistoryRepository loginHistoryRepository;


	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	static final Logger LOGGER = LoggerFactory.getLogger(PrincipalOauth2UserService.class);


	// userRequest 는 code를 받아서 accessToken을 응답 받은 객체
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		// google의 회원 프로필 조회
		OAuth2User oAuth2User = super.loadUser(userRequest);

		return processOAuth2User(userRequest, oAuth2User);
	}

	private OAuth2User processOAuth2User(OAuth2UserRequest userRequest, OAuth2User oAuth2User) {

		// Attribute를 파싱해서 공통 객체로 묶는다. 관리가 편함.
		OAuth2UserInfo oAuth2UserInfo = null;
		if (userRequest.getClientRegistration().getRegistrationId().equals("google")) {

			oAuth2UserInfo = new GoogleUserInfo(oAuth2User.getAttributes());
		} else if (userRequest.getClientRegistration().getRegistrationId().equals("facebook")) {

			oAuth2UserInfo = new FaceBookUserInfo(oAuth2User.getAttributes());
		} else if (userRequest.getClientRegistration().getRegistrationId().equals("naver")){

			oAuth2UserInfo = new NaverUserInfo((Map)oAuth2User.getAttributes().get("response"));
		} else {
			LOGGER.info(" 지원하지 않는 소셜 로그인 입니다.");
		}

		User user  = userRepository.findByProviderAndProviderId(oAuth2UserInfo.getProvider(), oAuth2UserInfo.getProviderId());

		if (user!=null) {

			//로그인 히스토리 기록
			LoginHistory loginHistory = LoginHistory.builder()
					.loginIp(CommonUtil.getUserIp())
					.user(user)
					.isLogin(true)
					.loginDate(LocalDateTime.now())
					.build();

			loginHistoryRepository.save(loginHistory);

		} else {
			// user의 패스워드가 null이기 때문에 OAuth 유저는 일반적인 로그인을 할 수 없음.
			user = User.builder()
					.username(oAuth2UserInfo.getProvider() + "_" + oAuth2UserInfo.getProviderId())
					.password(encoder.encode(oAuth2UserInfo.getProviderId()) )
					.email(oAuth2UserInfo.getEmail())
					.userRole(UserRole.ROLE_USER)
					.provider(oAuth2UserInfo.getProvider())
					.providerId(oAuth2UserInfo.getProviderId())
					.build();
			userRepository.save(user);
		}



		return new PrincipalDetails(user, oAuth2User.getAttributes());
	}
}
