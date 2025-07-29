<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MultiUpload Result</title>
<style>
img{max-width: 300px;}
</style>
</head>
<body>
	<h2>멀티 파일업로드 성공</h2>
	<c:forEach items="${saveFileMaps }" var="row" varStatus="status">
		업로드 한 파일명${status.count } : ${row.key }<br>
		저장된 파일명 ${status.count } : ${row.value }<br>
		<img src="./uploads/${row.value }"> <br>
	</c:forEach>
	
	제목 : ${title } <br>
	카테고리 :
	<!-- 체크한 항목의 갯수만큼 반복해서 출력한다. 단 마지막 항목에서는
	,를 출력하지 않는다. -->
	<c:forEach items="${cate }" var="row" varStatus="status">
		${row }
		<c:if test="${status.last eq false }">,</c:if>
	</c:forEach>	
</body>
</html>