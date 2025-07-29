package com.edu.springboot.auth;

import java.io.IOException;

import javax.security.auth.login.CredentialExpiredException;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// 자동으로 생성되는 빈임을 명시하는 어노테이션
@Configuration
public class MyAuthFailureHandler 
	implements AuthenticationFailureHandler {
	
	/*
	핸들러 제작을 위해 인터페이스를 구현한 후 추상메서드를 오버라이딩한다.
	 */
	@Override
	public void onAuthenticationFailure(
			HttpServletRequest request, 
			HttpServletResponse response,
			AuthenticationException exception) 
					throws IOException, ServletException {
		String errorMsg = "";
		/*
		instanceof 연산자를 이용해서 전달된 예외객체의 종류를 파악한 후 적절한
		에러메세지를 지정한다. 단, 인증관련 메세지는 너무 자세히 기술하지 않는 것이
		좋다.
		 */
		if(exception instanceof BadCredentialsException) {
			loginFailureCnt(request.getParameter("my_id"));
			errorMsg = "아이디나 비밀번호가 맞지 않습니다." 
					+ "다시 확인해주세요.(1)";
		}
		else if (exception instanceof 
					InternalAuthenticationServiceException) {
			loginFailureCnt(request.getParameter("my_id"));
			errorMsg = "아이디나 비밀번호가 맞지 않습니다." 
					+ "다시 확인해주세요.(2)";
		}
		else if (exception instanceof DisabledException) {
			errorMsg = "계정이 비활성화되었습니다. 관리자에게 문의하세요.(3)";
		}
		else if (exception instanceof CredentialsExpiredException) {
			errorMsg = "비밀번호 유효기간이 만료 되었습니다."
					+ "관리자에게 문의하세요.(4)";
		}
		// 에러메세지를 request 영역에 저장한 후 커스텀 로그인 페이지로 포워드한다.
		request.setAttribute("errorMsg", errorMsg);
		request.getRequestDispatcher("/myLogin.do?error")
			.forward(request, response);
	}
	public void loginFailureCnt(String username) {
		System.out.println("요청 아이디:" + username);
	}
}
