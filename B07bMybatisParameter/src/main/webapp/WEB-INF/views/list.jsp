<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>

function validateForm(fm) {
	let sFieldCnt = 0;
	for(let i=0 ; i<fm.searchField.length ; i++) {
		if(fm.searchField[i].checked==true)
			sFieldCnt++;
	}
	if(sFieldCnt==0){
		alert("한개 이상의 항목을 체크하셔야 합니다.");
		return false;
	}
}
</script>
</head>
<body>
	<h2>회원리스트</h2>
	<form onsubmit="retrun validateForm(fm)">
	<table>
	<tr>
		<td>
			<!-- 
			검색을 위한 필드(컬럼)을 2개이상 선택하기 위해 체크박스로
			구성한다. 폼값은 List로 전송된다. -->
			<input type="checkbox" name="searchField" value="id" />아이디
			<input type="checkbox" name="searchField" value="name" />이름
			<input type="checkbox" name="searchField" value="pass" />패스워드
			<!-- 검색어는 일반적인 String으로 전송된다. -->
			<input type="text" name="searchKeyword" />		
			<input type="submit" value="검색" />
		</td>
	</tr>
	</table>
	</form>
	<table border="1">
		<tr>
			<th>아이디</th>
			<th>패스워드</th>
			<th>이름</th>
			<th>가입일</th>
			<th></th>
		</tr>
	<c:forEach items="${memberList }" var="row" varStatus="loop">
	<tr>
		<td>${row.id }</td>
		<td>${row.pass }</td>
		<td>${row.name }</td>
		<td>${row.regidate }</td>
		<td>
			<a href="edit.do?id=${row.id }">수정</a>
			<a href="delete.do?id=${row.id }">삭제</a>
		</td>
	</tr>
	</c:forEach>
	</table>
	<a href="regist.do">회원등록</a>
</body>
</html>