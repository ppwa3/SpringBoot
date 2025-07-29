package com.edu.springboot.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.servlet.DispatcherType;

@Configuration
public class WebSecurityConfig {
	
	/*
	인증 핸들러를 제작했다면 사용을 위해 빈을 자동주입 받는다.
	그리고 시큐리티 설정 부분에 failureHandler()를 추가한다.
	 */
	@Autowired
	public MyAuthFailureHandler myAuthFailureHandler;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http)
		throws Exception{
		http.csrf((csrf) -> csrf.disable())
			.cors((cors) -> cors.disable())
			.authorizeHttpRequests((request) -> request
					.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
					.requestMatchers("/").permitAll()
					.requestMatchers("/css/**","/js/**", "/images/**").permitAll()
					.requestMatchers("/guest/**").permitAll()
					.requestMatchers("/member/**").hasAnyRole("USER", "ADMIN")
					.requestMatchers("/admin/**").hasRole("ADMIN")
					.anyRequest().authenticated()
			);
		
		/*
		로그인 페이지에 대한 디자인 커스터마이징 설정
		loginPage : 커스텀 로그인 페이지의 요청명
		loginProcessingUrl : 폼값을 전송하여 로그인 처리할 요청명
		failureUrl : 로그인에 실패한 경우 이동할 요청명
		failureHandler : 별도의 헨들러 인스턴스를 등록 후 에러처리
		usernameParameter : 아이디 입력상자의 name 속성값
		passwordParameter : 패스워드 입력상자의 name 속성값
		 */
		http.formLogin((formLogin) ->
				formLogin.loginPage("/myLogin.do")
						 .loginProcessingUrl("/myLoginAction.do")
//						 .failureUrl("/myError.do")
						 .failureHandler(myAuthFailureHandler)
						 .usernameParameter("my_id")
						 .passwordParameter("my_pass")
						 .permitAll());
		/*
		만약 failureUrl, failureHandler를 둘다 지정하지 않는다면
		로그인에 실패한 경우 로그인 페이지로 error라는 파라미터가 디폴트로 전달된다.
		이를 통해 로그인 실패 메세지를 표시할 수 있다.
		 */
		
		/*
		로그아웃에 대한 커스터마이징
		logoutUrl : 로그아웃을 위한 요청명
		logoutSuccessUrl : 로그아웃 이후 이동할 페이지의 요청명
		 */
		http.logout((logout) ->
			logout.logoutUrl("/myLogout.do")
				  .logoutSuccessUrl("/")
				  .permitAll());
		
		/*
		권한이 부족한 경우 이동할 페이지 지정(가령 user로 로그인 했는데, admin 권한이
		필요한 페이지에 접근하는 경우)
		 */
		http.exceptionHandling((expHandling) -> expHandling
					.accessDeniedPage("/denied.do"));
		
		return http.build();
	}
	@Bean
	public UserDetailsService users() {
		System.out.println("패스워드 : " + passwordEncoder().encode("1234"));
		UserDetails user = User.builder()
					.username("user")
					.password(passwordEncoder().encode("1234"))
					.roles("USER")
					.build();
		UserDetails admin = User.builder()
					.username("admin")
					.password(passwordEncoder().encode("1234"))
					.roles("USER", "ADMIN")
					.build();
		
		return new InMemoryUserDetailsManager(user, admin);
	}
	
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
}
