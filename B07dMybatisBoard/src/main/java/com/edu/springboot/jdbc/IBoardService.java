package com.edu.springboot.jdbc;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/*
컨트롤러와 마이바티스 매퍼 사이에서 매개역할을 하는 인터페이스로 @Mapper
어노테이션을 부착한다.
컨트롤러는 인터페이스에 정의된 추상메서드를 호출하고, 이를 통해 매퍼의 특정
엘리먼트를 호출하여 쿼리문을 실행한다.
*/
@Mapper
public interface IBoardService {
	//목록 : 게시물 갯수 카운트 
	public int getTotalCount(ParameterDTO parameterDTO);
	//목록 : 한페이지에 출력할 게시물 인출
	public ArrayList<BoardDTO> listPage(ParameterDTO parameterDTO);
	//작성 : 폼값의 변수명을 어노테이션을 통해 변경한 후 매퍼에서 사용
	public int write(@Param("_name") String name,
			@Param("_title") String title,
			@Param("_content") String content);
	//열람
	public BoardDTO view(BoardDTO boardDTO);
	//수정
	public int edit(BoardDTO boardDTO);
	//삭제
	public int delete(String idx);
	
	public void visitCountPlus(BoardDTO boardDTO);
}
