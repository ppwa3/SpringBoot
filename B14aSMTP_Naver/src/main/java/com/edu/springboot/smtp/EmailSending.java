package com.edu.springboot.smtp;

import java.io.BufferedReader;
import java.io.FileReader;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;


/*
스프링 컨테이너 시작시 자동으로 생성되는 빈으로 설정하기 위해 @Component 추가.
생성자 설정을 위한 어노테이션 추가.
*/
@Component
@RequiredArgsConstructor
public class EmailSending {
	
	/*
	멤버변수로 메일센더 선언. 프로젝트 생성시 디펜던시 추가를 했으므로
	사용가능함. */
	private final JavaMailSender mailSender;
	
	/*
	보내는 사람의 이메일주소를 프로퍼티 파일로부터 얻어온 후 설정.
	Naver SMTP를 사용하므로 네이버 메일로 설정해야한다. */
	@Value("${spring.mail.username}")
	private String from;
	
	public void myEmailSender(InfoDTO infoDTO) {
		
		try {
			//메일을 보내기 위한 설정을 담당
			MimeMessage m = mailSender.createMimeMessage();
			//인코딩 설정 
			MimeMessageHelper h = new MimeMessageHelper(m,"UTF-8");
			//보내는 사람
			h.setFrom(from);
			//받는사람
			h.setTo(infoDTO.getTo());
			//제목
			h.setSubject(infoDTO.getSubject());
			//이메일 형식
			if(infoDTO.getFormat().equals("text")) {
				//메일을 Text형식으로 발송할때는 순수 문자열만 설정
				h.setText(infoDTO.getContent());
			}
			else {
				/*
				HTML 형식으로 발송하는 경우 미리 만들어둔 메일템플릿(HTML)의 내용을
				읽어온 후 우리가 입력한 내용으로 변경하는 작업이 필요하다. */
				//메서드를 통해 템플릿 HTML소스를 읽어온다.
				String emailTpl = readHTMLFile();
				//발송할 내용을 줄바꿈 처리한다.
				String contents = infoDTO.getContent()
					.replace("\r\n","<br/>");
				//HTML 소스에 발송할 내용을 삽입한다.
				emailTpl = emailTpl.replace("__CONTENT__", contents);
				//HTML형식인 경우 두번째 인자를 true로 설정해야한다.
				h.setText(emailTpl, true);
			}
			//메일 발송
			mailSender.send(m);
			System.out.println("메일 전송 완료..!!");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//HTML 문서를 읽어 그 내용을 반환
	public String readHTMLFile() {
		//HTML문서의 내용을 읽어 저장하기 위한 스트링버퍼 인스턴스 생성
		StringBuffer htmlContents = new StringBuffer();
		try {
			//템플릿이 있는 디렉토리의 물리적경로 얻기
			String uploadDir = ResourceUtils
					.getFile("classpath:static/").toPath().toString();
			//파일명 지정
			String templatePath = uploadDir+"/MailTemplate.html";
			//템플릿의 내용을 IO스트림을 통해 한줄씩 읽어서 지정
			BufferedReader br = new BufferedReader(
					new FileReader(templatePath));
			String oneLine;
			while ((oneLine = br.readLine()) != null) {
				htmlContents.append(oneLine + "\n");
			}
			br.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		//읽은 내용을 String으로 변환 후 반환
		return htmlContents.toString();
	}
}
