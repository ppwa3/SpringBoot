package com.edu.springboot;

import java.lang.management.PlatformManagedObject;

import org.apache.ibatis.transaction.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.edu.springboot.jdbc.AddMember;
import com.edu.springboot.jdbc.ITicketService;
import com.edu.springboot.jdbc.PayDTO;
import com.edu.springboot.jdbc.TicketDTO;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class MainController {
   
   //Mybatis 사용을 위한 인터페이스 자동 주입
   @Autowired
   ITicketService dao;
   
   @Autowired
   TransactionTemplate transactionTemplate;
   /*
   트랜젝션 처리를 위한 빈 자동주입. 별도의 설정없이 스프링 컨테이너가
   미리 생성해둔 것을 즉시 주입받아 사용할 수 있다.
   */
   @Autowired
   PlatformTransactionManager transactionManager;
   @Autowired
   TransactionDefinition definition;
   
   /*
    추가작업 클래스 : 회원테이블에 구매한 회원의 이력을 입력한다.
       정의시 @Service 어노테이션을 부착한 상태이므로 자동주입이 가능하다. ㄴ*/
   @Autowired
   AddMember addMember;

   
   @RequestMapping("/")
   public String main() {
      return "main";
   }
   
   //티켓 구매 페이지 매핑
   @RequestMapping(value="/buyTicket.do", method = RequestMethod.GET)
   public String buy1() {
      return "buy";
   }
   
   //구매 처리
   @RequestMapping(value="/buyTicket.do", method=RequestMethod.POST)
   public String buy2(TicketDTO ticketDTO, PayDTO payDTO,
                      HttpServletRequest req, Model model) {
      /* 폼값을 받기 위한 DTO객체와 request 내장객체, Model객체로
       매개변수 선언 */
      
      //구매에 성공한 경우 포워드 할 View의 경로
       String viewPath = "success";
       
       /*
       템플릿을 사용하면 기존의 Status 빈은 필요없으므로 삭제한다. */
       try {
          /*
          템플릿 내에 익명클래스를 통해 오버라이딩 된 메서드로 모든 로직을
          옮겨주면된다. 템플릿을 사용하면 commit, rollback을 별도로
          실행하지 않아도 자동으로 트랜젝션 처리가 된다.
          */
          transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            
            @Override
            protected void doInTransactionWithoutResult(
                  TransactionStatus status) {
               
               //1. 회원정보입력(추가작업. 클래스 외부에서 처리되는 부분)
               String errFlag = req.getParameter("err_flag");
               addMember.memberInsert(ticketDTO, errFlag);
               
               //2. 구매금액입력(DB처리1) - 내부처리
               payDTO.setAmount(ticketDTO.getT_count() * 10000);
               int result1 = dao.payInsert(payDTO);
               if (result1 == 1) System.out.println("transaction_pay 입력성공");
               
               // 3. 비즈니스로직 처리(의도적인 에러 발생 지점)
               if (errFlag != null && errFlag.equals("1")) {
                  int money = Integer.parseInt("100원");
               }
               
               // 4. 티켓 매수 입력 (DB처리2) - 내부처리
               int result2 = dao.ticketInsert(ticketDTO);
               if (result2 == 1)
                  System.out.println("transaction_ticket 입력성공");
               
               model.addAttribute("ticketDTO", ticketDTO);
               model.addAttribute("payDTO", payDTO);
               
               //템플릿에서는 마지막에 commit()을 실행하지 않아도 된다.
            }
          });
       }
      catch (Exception e) {
           e.printStackTrace();
           //3개의 작업중 1개라도 문제가 생기면 error페이지로 포워드
           System.out.println("rollback됨");
           viewPath = "error";
           //작업에 오류가 있는 경우에도 별도의 rollback 실행이 필요하지 않다.
       }

       return viewPath;
   }

   
   
}
