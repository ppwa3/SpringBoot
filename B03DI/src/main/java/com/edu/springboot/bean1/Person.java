package com.edu.springboot.bean1;

//데이터 저장 기능만 있는 일반적인 DTO 클래스(VO라고도 함)
public class Person {
	//멤버변수
	private String name;
	private int age;
	private Notebook notebook;
	
	//생성자(디폴트, 인수)
	public Person() {}
	public Person(String name, int age, Notebook notebook) {
		super();
		this.name = name;
		this.age = age;
		this.notebook = notebook;
	}
	
	//게터, 세터
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Notebook getNotebook() {
		return notebook;
	}
	public void setNotebook(Notebook notebook) {
		this.notebook = notebook;
	}
	
	/*
	toString() 메서드 오버라이딩
	: 인스턴스 변수를 직접 print해서 클래스의 멤버변수를 출력할 수 있다.
	*/
	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + ", notebook=" + notebook + "]";
	}
	
	
}
