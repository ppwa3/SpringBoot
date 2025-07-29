<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<script>
	//매개변수 f는 <form>태그의 DOM이 전달된다.
	function sending(f, gubun){
		//gubun에 따라 서로 다른 action 속성값을 부여한다.
		if(gubun==1)
			f.action="./writeAction1.do"
		else
			f.action="./writeAction2.do"
		//폼값은 여기서 제출된다.
		f.submit();
	}
	</script>
	<h2>Validator 인터페이스를 통한 유효성검증</h2>
	<!-- 폼값 전송방식만 POST로 지정하고 나머지는 JS에서 지정한다. -->
	<form method="post">
		일련번호 : <input type="number" name="idx" value="1">
		<br />
		<!-- Model 객체에 저장된 값이 있다면 각 항목의 value로 지정한다. -->
		아이디 : <input type="text" name="userid" value="${dto.userid}">
		<br />
		제목 : <input type="text" name="title" value="${dto.title}">
		<br />
		내용 : <input type="text" name="content" value="${dto.content}">
		<input type="button" value="전송1" onclick="sending(this.form, 1);">
		<input type="button" value="전송2" onclick="sending(this.form, 2);">
	</form>
</body>
</html>