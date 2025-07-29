package com.edu.springboot;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	
	// root경로 : 인증없이 누구든 접속 가능
	@RequestMapping("/")
	public String main() {
		return "main";
	}
	
	// 누구든 접속 가능
	@RequestMapping("/guest/index.do")
	public String guest() {
		return "guest";
	}
	// ADMIN, USER 중 하나의 권한만 있으면 접속 가능
	@RequestMapping("/member/index.do")
	public String member() {
		return "member";
	}
	// ADMIN 권한만 접속 가능
	@RequestMapping("/admin/index.do")
	public String admin() {
		return "admin";
	}
	
	// 커스텀 로그인 페이지 매핑
	@RequestMapping("/myLogin.do")
	public String login1(Principal principal, Model model) {
		/*
		시큐리티는 세션을 사용해서 로그인 정보를 저장하지만, 개발자가 직접
		얻어올 수 없으므로, Principal 객체를 통해 로그인 아이디를 얻어올 수 있다.
		 */
		try {
			String user_id = principal.getName();
			model.addAttribute("user_id", user_id);
		}
		catch (Exception e) {
			/*
			최초 접근시 로그인 정보가 없으므로 NullPointerException이
			발생된다. 따라서 예외처리 해야한다.
			 */
			System.out.println("로그인 전입니다");
		}
		return "auth/login";
	}
	
	// 로그인 시도 중 에러가 발생하는 경우
	@RequestMapping("/myError.do")
	public String login2() {
		return "auth/error";
	}
	
	// 권한이 부족한 경우
	@RequestMapping("/denied.do")
	public String login3() {
		return "auth/denied";
	}
}
