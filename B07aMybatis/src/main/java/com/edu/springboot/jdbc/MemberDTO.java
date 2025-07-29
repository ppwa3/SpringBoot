package com.edu.springboot.jdbc;

import org.springframework.stereotype.Repository;

import lombok.Data;

@Data
public class MemberDTO {
	private String id;
	private String pass;
	private String name;
	private String regidate;
}

