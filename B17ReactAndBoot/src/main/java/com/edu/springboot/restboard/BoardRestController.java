package com.edu.springboot.restboard;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class BoardRestController {

	@Autowired
	IBoardService dao;
	
	//게시판 목록 API 생성
	@GetMapping("/restBoardList.do")
	public List<BoardDTO> restBoardList(ParameterDTO parameterDTO) {
		//한 페이지에 출력할 게시물의 갯수 설정
		int pageSize = 10;
		//매개변수로 전달받은 페이지번호
		int pageNum = parameterDTO.getPageNum()==null ? 1 :
			Integer.parseInt(parameterDTO.getPageNum());
		//DB에서 인출할 게시물의 구간 계산
		int start = (pageNum-1) * pageSize + 1;
		int end = pageNum * pageSize;
		parameterDTO.setStart(start);
		parameterDTO.setEnd(end);
		//목록에 해당하는 레코드 인출
		List<BoardDTO> boardList = dao.list(parameterDTO);
	
		//반환타입이 List이므로 JSON배열 형식으로 웹브라우저에 출력된다.
		return boardList;
	}
	
	//게시판에서 2개 이상의 단어를 검색한 결과API 생성 
	@GetMapping("/restBoardSearch.do")
	public List<BoardDTO> restBoardSearch(HttpServletRequest req,
			ParameterDTO parameterDTO) {
		//검색어는 스페이스로 구분해서 2개이상 전송되므로 별도로 처리해야한다.
		if(req.getParameter("searchWord")!=null) {
			//전송된 검색어를 스페이스를 통해 split한다.
			String[] sTxtArray = req.getParameter("searchWord")
					.split(" ");
			//clear() 함수로 저장된 모든 데이터 삭제
			parameterDTO.getSearchWord().clear();
			//앞에서 반환된 String배열의 크기만큼 반복
			for(String str : sTxtArray) {
				System.out.println(str);
				//각 검색어를 하나씩 추가한다. ArrayList이므로 add()함수로
				//추가할 수 있다.
				parameterDTO.getSearchWord().add(str);
			}
		}
		//Mapper의 search메서드를 호출한다.
		List<BoardDTO> searchList = dao.search(parameterDTO);
		return searchList;
}
	@PostMapping("/restBoardWrite.do")
	public Map<String, Integer> restBoardWrite(BoardDTO boardDTO){
		Map<String, Integer> map = new HashMap<>();
		try {
			int result = dao.write(boardDTO);
			map.put("result", result);
		}
		catch (Exception e) {}
		return map;
	}

	@GetMapping("/restBoardView.do")
	public BoardDTO restBoardView(ParameterDTO parameterDTO) {
		BoardDTO boardDTO = dao.view(parameterDTO);
		return boardDTO;
	}
}
