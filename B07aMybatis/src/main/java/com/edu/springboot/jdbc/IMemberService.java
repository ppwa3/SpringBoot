package com.edu.springboot.jdbc;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
/*
컨트롤러와 DAO(XML파일) 사이에서 매개 역할을 하는 인터페이스
JdbcTemplate에서는 @Service를 사용했지만 mybatis에서는 @Mapper를
사용한다.
컨트롤러에서 인터페이스에 정의된 추상메서드를 호출하면 연결된 Mapper의 특정
엘리먼트가 호출되어 쿼리문이 실행되는 구조이다. */
@Mapper
public interface IMemberService {
	
	public List<MemberDTO> select();
	public int insert(MemberDTO memberDTO);
	public MemberDTO selectOne(MemberDTO memberDTO);
	public int update(MemberDTO memberDTO);
	public int delete(MemberDTO memberDTO);
	
}