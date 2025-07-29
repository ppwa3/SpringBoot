<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>멀티파일업로드</title>
</head>
<body>
   <h2>멀티파일업로드</h2>
   <!-- 싱글파일업로드에서 action만 수정 -->
   <form action="multiUploadProcess.do" name="fileForm" method="post"
      enctype="multipart/form-data" onsubmit="return validateForm(this);" >
   제목 : <input type="text" name="title" /> <br />
   카테고리(선택사항) :
      <input type="checkbox" name="cate" value="사진" />사진
      <input type="checkbox" name="cate" value="과제" />과제   
      <input type="checkbox" name="cate" value="워드" />워드
      <input type="checkbox" name="cate" value="음원" />음원 <br />   
   <!-- 
   파일 선택상자에서 multiple 속성을 추가하면 Ctrl 혹은 Shift 키를 이용해서
   2개 이상의 파일을 선택할 수 있다. -->
   첨부파일 : <input type="file" name="ofile" multiple /> <br />
   <input type="submit" value="전송하기" />
   </form>
</body>
</html>