package com.apt.proptech.domain.oauth;

import com.apt.proptech.domain.User;
import com.apt.proptech.domain.enums.UserState;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;


@Data
public class PrincipalDetails implements UserDetails, OAuth2User {

	private User user;
	private Map<String, Object> attributes;

	//일반 로그인시 사용
	public PrincipalDetails(User user) {
		super();
		this.user = user;
	}

	// OAuth2.0 로그인시 사용
	public PrincipalDetails(User user, Map<String, Object> attributes) {
		this.user = user;
		this.attributes = attributes;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		if(user.getUserState() == UserState.RETIRED){
			return false;
		}
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	//계정 비밀번호가 만료되지 않았는지 반환
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	// 계정이 활성화(사용가능) 상태 인지 반환
	@Override
	public boolean isEnabled() {
		if(user.getFailLoginCount() >= 5){
			return  false;
		}

		return true;
	}

	@Override
	public Map<String, Object> getAttributes() {
		return attributes;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collection = new ArrayList<GrantedAuthority>();
		collection.add(()->{ return user.getUserRole().name();});
		return collection;
	}


	@Override
	public String getName() {
		return null;
	}
}
