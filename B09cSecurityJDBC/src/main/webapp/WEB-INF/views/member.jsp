<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome</title>
</head>
<body>
	<h2>Member영역</h2>
	ADMIN or USER권한이 있어야 접근할 수 있습니다.<br />
	
	<!-- 로그인 통해 '인증'되었다면 이 부분이 출력된다. -->
	<s:authorize access="isAuthenticated()">
		로그인 아이디 : <s:authentication property="name" /><br />
	</s:authorize>
	
	<!-- 인증 후 ADMIN 혹은 USER로 '인가'되었을때 출력된다. -->
	<s:authorize access="hasRole('ADMIN')">
		당신은 관리자입니다.<br />
	</s:authorize>
	<s:authorize access="hasRole('USER')">
		당신은 유저입니다.<br />
	</s:authorize>
	
	<%@ include file="./link.jsp" %>
</body>
</html>