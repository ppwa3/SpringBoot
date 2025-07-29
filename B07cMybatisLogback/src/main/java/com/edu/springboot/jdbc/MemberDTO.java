package com.edu.springboot.jdbc;

import java.util.List;

import org.springframework.stereotype.Repository;

import lombok.Data;

@Data
public class MemberDTO {
	private String id;
	private String pass;
	private String name;
	private String regidate;
	
	/*
	검색을 위한 멤버변수 추가
	필드명의 경우 체크박스이므로 2개 이상의 항목을 저장하기 위해 List로
	정의한다. 검색어는 단일항목이므로 String으로 정의하면된다. */
	private List<String> searchField;
	private String searchKeyword;
}

