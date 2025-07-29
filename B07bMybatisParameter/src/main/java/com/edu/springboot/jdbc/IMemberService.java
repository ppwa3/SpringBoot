package com.edu.springboot.jdbc;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
/*
컨트롤러와 DAO(XML파일) 사이에서 매개 역할을 하는 인터페이스
JdbcTemplate에서는 @Service를 사용했지만 mybatis에서는 @Mapper를
사용한다.
컨트롤러에서 인터페이스에 정의된 추상메서드를 호출하면 연결된 Mapper의 특정
엘리먼트가 호출되어 쿼리문이 실행되는 구조이다. */
@Mapper
public interface IMemberService {
	
	//public List<MemberDTO> select();
	public List<MemberDTO> select(MemberDTO memberDTO);
	//회원등록 : request내장객체를 통해 받은 파라미터를 개별적으로 전달
	public int insert(String id, String pass, String name);
	
	/*
	회원조회 : 파라미터를 받은 후 @Param 어노테이션으로 Mapper에서 사용할
		변수명을 지정한다. 즉 id로 받은 후 id로 이름을 변경해서 전달한다. */
	public MemberDTO selectOne(@Param("_id") String id);
	
	//회원정보수정 : 파라미터가 저장된 Map을 인수로 전달한다.
	public int update(Map<String, String> paramMap);
	
	//회원탈퇴(삭제)
	public int delete(String id);
	
}