package com.edu.springboot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.edu.springboot.jdbc.BoardDTO;
import com.edu.springboot.jdbc.IBoardService;
import com.edu.springboot.jdbc.ParameterDTO;

import jakarta.servlet.http.HttpServletRequest;
import oracle.jdbc.proxy.annotation.Post;


@Controller
public class MainController {
	
	@RequestMapping("/")
	public String main() {
		return "main";
	}
	
	/*
	DAO 호출을 위한 빈 자동주입. 이 인터페이스를 통해 
	Mapper를 호출한다.
	 */
	@Autowired
	IBoardService dao;
	
	
	
	@RequestMapping("/list.do")
	public String boardList(Model model, HttpServletRequest req,
			ParameterDTO parameterDTO) {
		/*
		매개변수는 view로 전달할 데이터 저장을 위한 Model, 요청을
		받아 처리하기 위한 request내장객체, 커맨드객체로 사용할
		DTO를 추가한다.
		 */
		
		/*
		게시물의 갯수 가운트(검색어가 있는 경우 파라미터는 
		DTO에 자동으로 저장됨)		 */
		int totalCount = dao.getTotalCount(parameterDTO);
		
		//페이징을 위한 설정값(하드코딩)
		int pageSize = 10; // 페이지당 출력할 게시물의 갯수 
		int blockPage = 5; // 한 블럭당 출력할 페이지번호의 갯수
		
		/*
		목록에 처음 진입할때는 페이지번호가 없으므로 1로 
		설정하고 파라미터를 통해 전달된 페이지번호가 있다면
		받은 후 정수로 변환한다. */
		int pageNum = (req.getParameter("pageNum") == null
				|| req.getParameter("pageNum").equals(""))
				? 1 : Integer.parseInt(req.getParameter("pageNum"));
		
		//현재 페이지에 출력할 게시물의 구간을 계산한다.
		//pageNum이 1이면 1,2가 나옴.
		int start = (pageNum-1) * pageSize + 1;
		int end = pageNum * pageSize;
		
		//계산의 결과는 DTO에 저장
		parameterDTO.setStart(start);
		parameterDTO.setEnd(end);
		
		//View에서 게시물의 가상번호 계산을 위한 값을 Map에 저장
		Map<String, Object> maps = new HashMap<String, Object>();
		maps.put("totalCount", totalCount);
		maps.put("pageSize", pageSize);
		maps.put("pageNum", pageNum);
		model.addAttribute("maps", maps);
		
		//DB에서 인출한 게시물의 목록을 영역에 저장
		ArrayList<BoardDTO> lists = dao.listPage(parameterDTO);
		model.addAttribute("lists", lists);
		
		//게시판 하단에 출력할 페이지번호를 String으로 저장한 후 영역에 저장
		String pagingImg = com.edu.springboot.utils.PagingUtil.pagingImg(
				totalCount, pageSize, blockPage, pageNum, 
				req.getContextPath()+"/list.do?");
		model.addAttribute("pagingImg", pagingImg);
		
		//View로 포워드
		return "list";
	}
	
	
	
	
	
	//쓰기 페이지
	
	//입력1 : 작성페이지 매핑
	@GetMapping("/write.do")
	public String boardWriteGet(Model model) {
		return "write";
	}
	
	//입력2 : 사용자가 작성한 값으로 입력 처리
	@PostMapping("/write.do")
	public String boardWritePost(Model model, HttpServletRequest req) {
		
		//request 내장객체를 통해 폼값 받음
		String name = req.getParameter("name");
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		
		//매퍼 인터페이스 호출
		int result = dao.write(name, title, content);
		System.out.println("글쓰기결과 : " + result);
		
		//페이징 확인을 위해 더미데이터 100개 입력하기(테스트용)
//		for(int i = 1 ; i<=100 ; i++) {
//			dao.write(name+"["+i+"]", title+"["+i+"]", content+"["+i+"]");
//		}
		
		
		//글쓰기 완료 후 목록으로 이동
		return "redirect:list.do";
	}
	
	
	
	
	
	
	
	//열람 : 클릭시 전달되는 일련번호는 DTO를 통해 받은 후 사용
	@RequestMapping("/view.do")
	public String boardView(Model model, BoardDTO boardDTO) {
		//DTO를 view함수로 호출시 전달
		boardDTO = dao.view(boardDTO);
		//레코드 중 내용은 줄바꿈 처리 후 다시 저장
		dao.visitCountPlus(boardDTO);
		boardDTO.setContent(boardDTO.getContent()
					.replace("\r\n", "<br/>"));
		model.addAttribute("boardDTO", boardDTO);
		return "view";
	}
	
	
	
	
	
	
	
	//수정1 : 기존 내용을 읽어와서 수정폼에 설정
	@GetMapping("/edit.do")
	public String boardEditGet(Model model, BoardDTO boardDTO) {
		//열람에서 사용했던 메서드를 그대로 사용
		boardDTO = dao.view(boardDTO);
		model.addAttribute("boardDTO", boardDTO);
		return "edit";
	}
	
	//수정2 : 사용자가 입력한 내용을 전송하여 update 처리
	@PostMapping("/edit.do")
	public String boardEditPost(BoardDTO boardDTO) {
		
		//수정 후 결과는 int형으로 반환됨
		int result = dao.edit(boardDTO);
		System.out.println("글 수정 결과 : " + result);
		//수정이 완료되면 열람페이지로 이동. 일련번호가 파라미터로 전달됨
		return "redirect:view.do?idx=" + boardDTO.getIdx();
	}
	
	
	
	
	
	
	//삭제
	@PostMapping("/delete.do")
	public String boardDeletePost(HttpServletRequest req) {
		int result = dao.delete(req.getParameter("idx"));
		System.out.println("글 삭제 결과 : " + result);
		return "redirect:list.do";
	}
	
	
}













