 package com.edu.springboot.jsontype4;

import lombok.AllArgsConstructor;
import lombok.Data;

/*
@Data : 멤버변수 접근을 위한 getter, setter, 기본생성자 및
 	Object 클래스에서 제공하는 toString과 같은 메서드를 자동으로 생성
@AllArgsConstructor : 멤버변수의 갯수에 맞게 인자를 가진 생성자가
	추가된다. 단, 인자생성자를 추가했으므로 기본생성자는 제거된다.
*/
@Data
@AllArgsConstructor
public class PersonVO {
	private String name;
	private int age;
	private String job;
}
