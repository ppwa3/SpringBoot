package com.edu.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	//요청명은 / 로 시작한다.
	//서비스 메서드랑 비슷한 느낌
	@RequestMapping("/")
	public String main() {
		return "main";
	}
}
