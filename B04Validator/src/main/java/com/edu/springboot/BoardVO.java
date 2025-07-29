package com.edu.springboot;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

/*
어노테이션을 통한 폼값 검증을 위해 디펜던시 항목을 추가한다.
build.gradle에 starter-validation을 추가한 후 refresh한다. */
@Data
public class BoardVO {
	
	private int idx;
	
	/*
	@NotNull : 폼값이 null일때 메세지 출력
	@NotEmpty : 빈값인지 검증
	@Size : 입력값의 길이를 지정. 해당 범위를 벗어나면 메세지 출력 */
	@NotNull(message = "아이디가 null입니다.")
	@NotEmpty(message = "아이디를 입력해주세요.")
	@Size(min = 5, max = 12, message = "아이디는 5~12자로 입력해주세요.")
	private String userid;
	
	@NotNull(message = "제목이 null입니다.")
	@NotEmpty(message = "제목을 입력해주세요.")
	private String title;
	
	@NotNull(message = "내용이 null입니다.")
	@NotEmpty(message = "내용을 입력해주세요.")
	private String content;
}
