package com.apt.proptech.core.config;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;


@Configuration // IoC 빈(bean)을 등록
@EnableWebSecurity // 필터 체인 관리 시작 어노테이션
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter{


	@Autowired
	private CorsConfig corsConfig;

	//로그인 실패시 이후 처리를 담당하는 핸들러
	private final AuthenticationSuccessHandler customSuccessHandler;

	//로그인 실패시 이후 처리를 담당하는 핸들러
	private final AuthenticationFailureHandler customFailureHandler;

	@Bean
	public BCryptPasswordEncoder encodePwd() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//로그아웃은 디폴트 값으로 /logout 을 사용한다
		//CORS 필터는 인증이 필요한 접근이 있을때 사용한다.
		http.addFilter(corsConfig.corsFilter()).
				csrf().disable().headers().frameOptions().disable();
		http.authorizeRequests()
			//.antMatchers("/user/**").authenticated()
			.antMatchers("/login/**","/register","/forgot" ,"/css/**", "/js/**", "/img/**", "/vendor/**" , "/error/**").permitAll()
			.antMatchers("/dashboard/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_STAFF') or hasRole('ROLE_MANAGER') or hasRole('ROLE_PARTNER')")
				.antMatchers("/table/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_STAFF') or hasRole('ROLE_MANAGER') or hasRole('ROLE_PARTNER')")
				.antMatchers("/chart/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_STAFF') or hasRole('ROLE_MANAGER') or hasRole('ROLE_PARTNER')")
				.antMatchers("/error/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_STAFF') or hasRole('ROLE_MANAGER') or hasRole('ROLE_PARTNER') or hasRole('ROLE_USER')")
		.and()
			.formLogin()
			.loginPage("/login")
			.loginProcessingUrl("/loginProc")
			.defaultSuccessUrl("/")
			.successHandler(customSuccessHandler)
			.failureHandler(customFailureHandler)
		.and()
			.oauth2Login()
			.loginPage("/login")
		.and()
			.logout()
			.logoutSuccessUrl("/login")
			.invalidateHttpSession(true);
	}
}





