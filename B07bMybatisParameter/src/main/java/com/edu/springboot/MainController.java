package com.edu.springboot;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.edu.springboot.jdbc.IMemberService;
import com.edu.springboot.jdbc.MemberDTO;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class MainController {
	
	
	@RequestMapping("/")
	public String main() {
		return "main";
	}
	
	/*
	Mapper 인터페이스를 통해 XML파일에 정의된 메서드를 호출하게 되므로
	자동주입 받아서 준비한다. 해당 인터페이스는 @Mapper가 부착되어 있으므로
	컨테이너가 시작될때 자동으로 빈이 생성된다. */
	@Autowired
	IMemberService dao;
	
	//회원목록
	@RequestMapping("/list.do")
	//컨트롤러에서는 메서드명을 굳이 신경써서 안만들어줘도된다.
	public String member2(Model model, MemberDTO memberDTO) {
		
		System.out.println(memberDTO.getSearchField());
		System.out.println(memberDTO.getSearchKeyword());
		/*
		dao.select()를 통해 인터페이스의 추상메서드를 호출한다.
		그러면 인터페이스와 연결된 Mapper에 정의된 특정 엘리먼트가 호출되어
		쿼리문이 실행되고 결과를 반환한다. */
		model.addAttribute("memberList", dao.select(memberDTO));
		return "list";
	}
	//글쓰기 페이지 진입하기
	@RequestMapping(value="/regist.do", method=RequestMethod.GET)
	public String member1() {
		return "regist";
	}
	//글쓰기 처리하기
	@RequestMapping(value="/regist.do", method=RequestMethod.POST)
	public String member6(HttpServletRequest req) {
		/*
		전송된 폼값은 request 내장객체를 통해 개별적으로 전달 받는다.
		*/
		String id = req.getParameter("id");
		String pass = req.getParameter("pass");
		String name = req.getParameter("name");
		
		//함수 호출시에도 개별적으로 전달해야한다.
		int result = dao.insert(id, pass, name);
		if(result==1) System.out.println("입력되었습니다.");
		return "redirect:list.do";
	}
	@RequestMapping(value="/edit.do", method=RequestMethod.GET)
	public String member3(HttpServletRequest req, MemberDTO memberDTO,
			Model model) {
		//request내장객체로 id를 받는다.
		memberDTO = dao.selectOne(req.getParameter("id"));
		model.addAttribute("dto", memberDTO);
		return "edit";
	}
	@RequestMapping(value="/edit.do", method=RequestMethod.POST)
	public String member7(HttpServletRequest req) {
		//request 내장객체를 통해 폼값 받기
		String id = req.getParameter("id");
		String pass = req.getParameter("pass");
		String name = req.getParameter("name");
		
		//받은 폼값은 Map에 저장
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("m_id", id);
		paramMap.put("m_pass", pass);
		paramMap.put("m_name", name);
		
		//함수 호출시 Map을 인수로 전달
		int result = dao.update(paramMap);
		if(result==1) System.out.println("수정되었습니다.");
		return "redirect:list.do";
	}
	@RequestMapping("/delete.do")
	public String member4(HttpServletRequest req){
		//request 내장객체로 받은 후 전달
		String id = req.getParameter("id");
		int result = dao.delete(id);
		if(result==1) System.out.println("삭제되었습니다.");
		return "redirect:list.do";
	}
	
}
