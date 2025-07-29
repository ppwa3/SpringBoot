package com.edu.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	//요청명은 / 로 시작한다.
	//서비스 메서드랑 비슷한 느낌
	@RequestMapping("/")
	public String main() {
		return "main";
	}
	//글쓰기가 페이지에 대한 매핑
	@RequestMapping("/write.do")
	public String insert1() {
		return "write";
	}
	
	/*
	Validator 인터페이스를 통한 폼값으 유효성 검증
	Spring에서 사용하는 커맨드객체를 전송된 폼값을 한꺼번에 받아 Model에
	저장해준다. 만약 저장되는 속성명을 변경하고 싶다면 @ModelAttribute를
	사용하면된다.*/
	@RequestMapping("/writeAction1.do")
	public String writeAction1(@ModelAttribute("dto") BoardDTO boardDTO,
			BindingResult result) {
		
		//폼값 검증에 성공한 경우 포워드 할 View의 경로 설정
		String page = "result";
		System.out.println(boardDTO);
		
		//폼값 검증을 위한 인스턴스 생성
		BoardValidator validator = new BoardValidator();
		/*
		폼값 전체를 저장한 DTO 및 검증결과 전달을 위한 객체를 인수로 전달한다.
		여기서 validate()를 호출하여 검증을 진행한다.
		*/
		validator.validate(boardDTO, result);
		
		//폼값 검증에 실패한 경우 if문의 블럭이 실행됨
		if(result.hasErrors()) {
			//실패한 경우 재입력을 받기위해 쓰기페이지로 포워드
			page = "write";
			//폼값 검증에 대한 전체 내용 출력
			System.out.println("검증 실패 반환값1:"+result.toString());
			
			//제목 검증에 실패한 경우 개별 메세지 출력
			if(result.getFieldError("title")!=null) {
				//getCode() : 우리가 지정한 에러객체명을 반환
				System.out.println("제목 검증1(에러코드):"
						+result.getFieldError("title").getCode());
			}
			//내용 검증에 실패한 경우 개별 메세지 출력
			if(result.getFieldError("content")!=null) {
			//getDefaultMessage() : 우리가 지정한 디폴트 메세지 반환
				System.out.println("내용 검증1(디폴트메세지):"
						+result.getFieldError("content").getDefaultMessage());
			}
		}
		
		return page;
	}
	
	/*
	어노테이션을 통한 검증이므로 폼값 저장을 위한 VO객체에 @Validated를 
	추가해야한다. */
	@RequestMapping("/writeAction2.do")
	public String writeAction2(@ModelAttribute("dto") @Validated BoardVO boardVO,
			BindingResult result) {
		String page = "result";
		System.out.println(boardVO);
		
		//검증을 위한 클래스를 별도로 정의할 필요가 없으므로 주석처리
		//BoardValidator validator = new BoardValidator();
		// Validator.validate(boardDTO, result);
		
		//폼값 검증에 문제가 생길경우 if문의 블럭이 실행된다.
		if(result.hasErrors()) {
			page = "write";
			System.out.println("검증 실패 반환값2:"+result.toString());
			
			if(result.getFieldError("title")!=null) {
				System.out.println("제목 검증2(에러코드):"
						+result.getFieldError("title").getCode());
			}
			if(result.getFieldError("content")!=null) {
				System.out.println("내용 검증2(디폴트메세지):"
						+result.getFieldError("content").getDefaultMessage());
			}
		}
		
		return page;
	}
}

