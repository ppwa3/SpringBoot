package com.edu.springboot;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/*
폼값 검증을 위한 클래스 제작을 위해 먼저 Validator 인터페이스를 구현한다.
그리고 추상메서드 2개를 필수로 오버라이딩 해야한다. */
public class BoardValidator implements Validator{
	
	/*
	검증을 위한 커맨드객체의 클래스 타입 확인을 위한 메서드. */
	@Override
	/*Class<?> clazz 오버라이딩이 이런식으로 된다까지만 알아도된다. */
	public boolean supports(Class<?> clazz) {
		System.out.println("supports() 호출됨");
		//폼값을 받을때 사용하는 커맨드객체의 타입만 명시하면된다.
		return BoardDTO.class.isAssignableFrom(clazz);
	}
	
	/*
	폼값 검증을 하기위해 3가지 방법을 사용할 수 있다.
	1. if문을 이용한 검증
	2. ValidationUtils 클래스를 이용한 검증
	3. 사용자 정의 메서드를 이용한 검증 */
	@Override
	public void validate(Object target, Errors errors) {
		
		System.out.println("validate() 호출됨");
		//폼값 전체를 저장한 커맨드객체는 Object타입으로 전달되므로 형변환
		BoardDTO boardDTO = (BoardDTO) target;
		
		//폼값을 저장한 커맨드객체가 검증에 적합한지 검사
		if(supports(boardDTO.getClass())==true) {
			System.out.println("폼값 검증에 적합한 인스턴스");
		}
		else {
			System.out.println("폼값 검증을 위한 인스턴스가 아님");
		}
		
		//1.아이디 검증
		//boardDTO는 BoardDTO라는 클래스의 객체(인스턴스)
		//getUserid()는 그 클래스 안에 있는 메서드
		//그 메서드는 보통 "userid라는 값을 꺼내줘" 라는 역할을함
		String userid = boardDTO.getUserid();
		//만약 아이디가 null이거나 빈값이라면 if문 내부 코드를 실행
		if(userid==null || userid.trim().isEmpty()) {
			//개발자가 직접 콘솔에 에러 내용 출력
			System.out.println("아아디를 입력새주세요.");
			/*
			폼값 검증시 오류가 있는 경우 처리형식
			-> Errors인스턴스.rejectValue(폼의name속성, 에러객체명,
										디폴트메세지)
			 */
			errors.rejectValue("userid", "idError111",
					"아이디 검증 실패111");
		}
		/*
		2.제목 검증 : ValudationUtiols 클래스의 정적 메서드를 호출하여 빈값이거나
			null인 경우를 검증한다. */
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,
				"title", "titleError222", "제목 검증 실패222");
		
		/*
		3.내용 검증 : 개발자가 정의한 메서드를 통해 검증한다. */
		//myEmptyOrWhitespace() : 문자열이 비었거나 공백만 있는지 확인하는 함수
		boolean contentValidate = 
				myEmptyOrWhitespace(boardDTO.getContent());
		if(contentValidate==false) {
			System.out.println("내용을 입력해주세요.");
			errors.rejectValue("content", "contemtError333",
					"내용 검증 실패333");
		}
	}
	
	//개발자 정의 메서드
	private boolean myEmptyOrWhitespace(String value) {
		//null 혹은 빈값인지 검증
		if(value==null || value.trim().length()==0) {
			return false;
		}
		else {
			return true;			
		}
	}
}
