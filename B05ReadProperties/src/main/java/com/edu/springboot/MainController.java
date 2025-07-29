package com.edu.springboot;


import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	//요청명은 / 로 시작한다.
	//서비스 메서드랑 비슷한 느낌
	@RequestMapping("/")
	public String main() {
		return "main";
	}
	
	/*
	방법1 : application.properties에 설정된 값은 @Value로
	 	별도의 설정없이도 읽어올 수 있다. 읽어온 값을 멤버변수에 저장한다.*/
	@Value("${test.key1}")
	private String testKey1;
	@Value("${test.key2}")
	private String testKey2;
	
	@RequestMapping("/link1")
	public String link1(Model model, @Value("${test.key3}")
		List<String> testKey3) {
		
		/*
		방법2 : 매개변수로 선언한 변수에 @Value를 부착한다. 이 경우 해당
		 메서드의 지역에서만 사용할 수 있다. */
		
		System.out.println("testKey1="+ testKey1);
		System.out.println("testKey2="+ testKey2);
		System.out.println("testKey3="+ testKey3);
		
		//Model 객체를 통해 영역에 속성을 저장
		model.addAttribute("testKey1", testKey1);
		model.addAttribute("testKey2", testKey2);
		model.addAttribute("testKey3", testKey3);
		
		return "link1";
	}
	
	/*
	PropertyConfig 클래스에서 myprops라는 이름으로 빈을 생성했으므로
	아래와 같이 값을 읽을 수 있다. 만약 이름을 지정하지 않으면 메서드명으로
	생성된다. */
	@Value("#{myprops['my.id']}")
	private String myId;
	@Value("#{myprops['my.pass']}")
	private String myPass;
	@Value("#{myprops['my.address']}")
	private String myAddress;
	@Value("#{myprops['my.age']}")
	private String myAge;
	@RequestMapping("/link2")
	public String link2(Model model) {
		
		System.out.println("myId="+ myId);
		System.out.println("myPass="+ myPass);
		System.out.println("myAddress="+ myAddress);
		System.out.println("myAge="+ myAge);
		
		model.addAttribute("myId", myId);
		model.addAttribute("myPass", myPass);
		model.addAttribute("myAddress", myAddress);
		model.addAttribute("myAge", myAge);
		
		return "link2";
	}
	
}

