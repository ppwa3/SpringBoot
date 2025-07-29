package com.edu.springboot.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

@Service
public class AddMember {
	
	//DB작업을 위한 자동주입
	@Autowired
	ITicketService dao;
	
	//트랜젝션 처리를 위한 자동주입
	@Autowired
	TransactionTemplate transactionTemplate;
	
	/*
	전파속성
	REQUIRED : 기존 트랜젝션에 의존한다. 즉 포함된(AddMember 클래스) 메서드나 포함시킨 메서드
	 	어느쪽이든 오류가 발생되면 모든 작업을 Rollback 처리한다.
	 	즉 모든 메서드로 전파된다.
	REQUIRES_NEW : 각각의 트랜젝션을 처리한다. 즉 포함시킨(Main컨트롤러) 메서드에서
	 	오류가 발생하더라도 포함된 메서드에서는 정상처리된다.
	 	포함된 메서드로 전파되지 않는다. */
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	//@Transactional(propagation=Propagation.REQUIRED)
	public void memberInsert(TicketDTO ticketDTO, String errFlag) {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				
				@Override
				protected void doInTransactionWithoutResult(
						TransactionStatus status) {
					//체크박스2를 체크하면 여기서 의도적 에러가 발생됨
					if(errFlag!=null && errFlag.equals("2")) {
						int money = Integer.parseInt("200원");
					}
					//구매한 회원의 내역을 member테이블에 추가
					int result3 = dao.memberRegist(ticketDTO);
					if(result3==1)
						System.out.println("member 테이블 입력성공");
				}
			});
		}
		catch (Exception e) {
			System.out.println("member 테이블 Rollback됨");
		}
	}
}
