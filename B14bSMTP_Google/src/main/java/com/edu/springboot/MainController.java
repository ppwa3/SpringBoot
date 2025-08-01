package com.edu.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.edu.springboot.smtp.EmailSending;
import com.edu.springboot.smtp.InfoDTO;

@Controller
public class MainController {
	
	//root 경로 매핑
	@RequestMapping("/")
	public String main() {
		return "main";
	}
	
	//이메일 작성 폼 매핑
	@GetMapping("/emailSendMain.do")
	public String emailSendMain() {
		return "emailSendMain";
	}
	
	//이메일 발송처리를 위한 클래스 자동주입(개발자 직접 작성)
	@Autowired
	EmailSending email;
	
	//이메일 전송 처리
	@PostMapping("/emailSendProcess.do")
	public String emailSendProcess(InfoDTO infoDTO) {
		//작성폼에서 전송된 폼값을 DTO객체로 한번에 받아서 사용
		email.myEmailSender(infoDTO);
		return "main";
	}
}
