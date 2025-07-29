package com.edu.springboot.jdbc;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ITicketService {

	public int ticketInsert(TicketDTO ticketDTO);
	public int payInsert(PayDTO payDTO);
}
