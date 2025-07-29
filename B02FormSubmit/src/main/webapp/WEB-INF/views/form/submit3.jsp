<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Submit</title>
</head>
<body>
	<h2>폼값전송3 : 커맨드객체</h2>
	<!-- 컨트롤러에서 personDTO에 저장했으므로 출력시에는 아래와
	같이 getter를 통해 출력한다. -->
	<p>
		이름 : ${ personDTO.name } <br>
		나이 : ${ personDTO.age } 
	</p>
	
	<!-- 요청명이 /form3.do 이므로 최상위 경로에서 실행되는
	형태이다. 따라서 아래와 같이 상대경로를 설정할 수 있다. -->
	<h3>이미지 삽입하기</h3>
	<img src="./images/SpringBoot.png" />
</body>
</html>