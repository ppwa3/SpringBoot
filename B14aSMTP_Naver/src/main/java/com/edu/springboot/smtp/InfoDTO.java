package com.edu.springboot.smtp;

import lombok.Data;

@Data
public class InfoDTO {
	private String mailServer;
	private String from;
	private String to;
	private String subject;
	private String format;
	private String content;
}
