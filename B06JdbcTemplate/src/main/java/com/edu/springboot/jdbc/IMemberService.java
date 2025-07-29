package com.edu.springboot.jdbc;

import java.util.List;

import org.springframework.stereotype.Service;

/*
Controller(컨트롤러)와 Model(모델) 사이에서 매개 역할을 하는 인터페이스로
DAO 클래스의 부모 역할을 담당한다.
@Service 어노테이션은 @Controller와 유사하게 스프링 컨테이너가 시작될때
자동으로 Scan하여 빈을 생성한다. 따라서 이 클래스도 기본패키지 하위에 있어야한다. */
@Service
public interface IMemberService {
	//회원정보추가
	public int insert(MemberDTO memberDTO);
	//회원목록(리스트)
	public List<MemberDTO> select();
	//회원정보조회
	public MemberDTO selectOne(MemberDTO memberDTO);
	//회원정보수정
	public int update(MemberDTO memberDTO);
	//회원정보삭제(탈퇴)
	public int delete(MemberDTO memberDTO);
	
	/* 정의된 추상메서드의 모든 매개변수는 MemberDTO 타입으로 정의하여
	전달되는 매개변수를 한번에 받아 영역에 저장할 수 있도록 정의되어있다. */
}
