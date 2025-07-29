package com.edu.springboot;

import lombok.Data;

/*
lombok(롬복)에서 제공하는 @Data어노테이션
멤버변수에 대한 getter, setter, toString과 Set컬렉션에서
중복제거를 위한 hashCode, equals 그리고 기본생성자까지 자동으로
생성된다. */
@Data
public class BoardDTO {
	
	private int idx;
	private String userid;
	private String title;
	private String content;
}
