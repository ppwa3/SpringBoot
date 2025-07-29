package com.edu.springboot.jdbc;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ITicketService {

	public int ticketInsert(TicketDTO ticketDTO);
	public int payInsert(PayDTO payDTO);
	
	//티켓 구매 시도를 한 회원이력을 남기기 위한 추상메서드 추가
	public int memberRegist(TicketDTO ticketDTO);
}
