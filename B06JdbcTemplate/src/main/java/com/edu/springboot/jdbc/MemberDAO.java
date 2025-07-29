package com.edu.springboot.jdbc;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

/*
스프링 컨테이너가 시작될때 자동으로 Scan되어 빈을 생성한다.
즉 @Controller, @Service, @Repository 모두 유사한 역할을 한다. */
@Repository
public class MemberDAO implements IMemberService{
	
	/*
	JDBC 작업을 위해 자동주입받는다. JdbcTemplate 빈은 개발자가 직접
	설정하지않고, build.gradle 외존성추가가 되어있으면 스프링 컨테이너가
	자동으로 빈을 생성해준다.*/
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	//회원목록
	@Override
	public List<MemberDTO> select() {
		//회원 레코드를 가입일을 내림차순 정렬해서 인출하는 쿼리문
		String sql = "select * from member order by regidate desc";
		
		/*
		query() 메서드를 통해 select쿼리문을 시행한다. 쿼리문 실행후 
		반환되는 ResultSet은 RowMapper가 자동으로 반복하여 DTO에 저장한 후
		이를 List에 추가하여 반환해준다.
		즉 레코드를 List에 저장하기 위한 반복적인 작업을 자동으로 수행해준다.*/
		return jdbcTemplate.query(sql,
				new BeanPropertyRowMapper<MemberDTO>(MemberDTO.class));
	}
	
	@Override
	public int insert(MemberDTO memberDTO) {
		/*
		insert, update, delete와 같이 행의 변화가 있는 쿼리문 실행시에는
		update() 메서드를 실행한다. 실행 후 적용된 행의 갯수가 int형으로
		반환된다. */
		int result = jdbcTemplate.update(new PreparedStatementCreator() {
			/*
			PreparedStatementCreator 인터페이스로 익명클래스를 생성한 후
			오버라이딩 된 메서드내에서 인파라미터가 있는 insert쿼리문을 실행한다.
			결과는 정수형으로 반환된다. */
			@Override
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				//인파라미터가 있는 쿼리문 작성
				String sql = "insert into member (id, pass, name) "
						+ " values (?, ?, ?)";
				//인파라미터 세팅 후 쿼리문 실행
				PreparedStatement psmt = con.prepareStatement(sql);
				psmt.setString(1, memberDTO.getId());
				psmt.setString(2, memberDTO.getPass());
				psmt.setString(3, memberDTO.getName());
				
				return psmt;
			}
		});
		//insert 쿼리문 실행 후 결과를 컨트롤러로 반환한다.
		return result;
	}
	
	//회원정보수정1 : 기존의 회원레코드 인출하기
	@Override
	public MemberDTO selectOne(MemberDTO memberDTO) {
		//인파라미터가 있는 select 쿼리문 작성
		String sql = "select * from member where id=?";
		/*
		queryForObject() : 하나의 결과를 반환하는 select를 실행할때 사용
		인수1 : 쿼리문
		인수2 : RowMapper를 통해 인출한 ResultSet을 DTO에 자동으로 입력
		인수3 : 인파라미터에 설정할 값을 Object배열로 선언. 순서대로 설정됨.
		*/
		try {
			memberDTO = jdbcTemplate.queryForObject(sql,
					new BeanPropertyRowMapper<MemberDTO>(MemberDTO.class),
					new Object[] { memberDTO.getId() });
			/*
			queryForObject 메서드는 반드시 하나의 결과가 있어야한다. 만약
			잘못된 조건으로 인해 결과가 인출되지 않는다면 예외가 발생하게된다.
			따라서 try~catch로 감싸서 예외처리 해주는것이 좋다. */
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return memberDTO;
	}
	
	//회원정보수정2 : 회원정보 수정 처리
	@Override
	public int update(MemberDTO memberDTO) {
		//인파라미터가 있는 update 쿼리문 작성
		String sql = "update member set pass=?, name=? where id=?";
		/*
		PreparedStatementSetter 인터페이스의 익명클래스를 이용해서
		인파라미터를 설정한 후 쿼리문을 실행한다. */
		int result = jdbcTemplate.update(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, memberDTO.getPass());
				ps.setString(2, memberDTO.getName());
				ps.setString(3, memberDTO.getId());
			}
		});
		return result;
	}
	//삭제
	@Override
	public int delete(MemberDTO memberDTO) {
		String sql = "delete from member where id=?";
		//update() 함수에서 인파라미터 설정시 Object클래스의 배열 사용
		int result = jdbcTemplate.update(sql,
				new Object[] {memberDTO.getId()});
		return result;
	}

}
