<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- build.gradle에 JSP 사용을 위한 4개의 의존성을 통해 JSTL을 사용할
수 있다. taglib 지시어를 통해 설정한다. -->
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>application.properties에서 가져오기</h2>
	<!-- 단일값인 경우 EL을 통해 즉시 출력 -->
	<ul>
		<li>testKey1 : ${testKey1 }</li>
		<li>testKey2 : ${testKey2 }</li>
	</ul>
	<ol>
	<!--  2개 이상의 값은 List로 저장되므로 반복문을 통해 출력한다. -->
	<c:forEach items="${testKey3 }" var="item">
		<li>testKey3 : ${item }</li>
	</c:forEach>
	</ol>
</body>
</html>