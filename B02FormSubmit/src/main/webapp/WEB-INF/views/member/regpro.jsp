<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>회원가입폼에서 전송된 값</h2>
	<li>아이디 : ${ memberDTO.id }</li>
	<li>비밀번호 : ${ memberDTO.pass1 }</li>
	<li>이름 : ${ memberDTO.name }</li>
	<li>성별 : ${ memberDTO.sex }</li>
	<li>이메일 : ${ memberDTO.email1 }@${ memberDTO.email2 }</li>
	<li>이메일 수신여부 : ${ memberDTO.mailing }</li>
	<li>우편번호 : ${ memberDTO.zipcode }</li>
	<li>주소 : ${ memberDTO.addr1 }&nbsp;${ memberDTO.addr2 }</li>
	<li>핸드폰 : ${ memberDTO.mobile1 }-${ memberDTO.mobile2 }-${ memberDTO.mobile3 }</li>
	<li>SMS 수신여부 : ${ memberDTO.sms }</li>
	<li>관심분야 : ${ memberDTO.etc_no1 }</li>
	<li>가입경로 : ${ memberDTO.etc_no2 }</li>
</body>
</html>