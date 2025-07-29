<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Submit</title>
</head>
<body>
	<h2>폼값전송4 : @PathVariable</h2>
	<p>
		이름 : ${ name } <br>
		나이 : ${ age } 
	</p>
	
	<!-- 
	요청명이 form/삼장법사/44 와 같은 형태이므로 루트경로 하위 2Depth로
	인식한다. 따라서 이미지의 경로는 상위로 2번 이동해야 하므로 ../을 2번
	추가해야한다.
	 -->
	<h3>이미지 삽입하기</h3>
	<img src="../../images/SpringBoot.png" />
</body>
</html>