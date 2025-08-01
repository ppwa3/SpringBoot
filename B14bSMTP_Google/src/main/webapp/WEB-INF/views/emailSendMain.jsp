<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SMTP 이메일 전송</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script>
$(function(){
	//보내는 사람과 받는 사람의 이메일 주소 설정
	let naMail = "ppwa3@naver.com";//보내는 사람
	let goMail = "hyx.lim2@gmail.com";//받는 사람
	
	//문서 로드시 <input>태그에 값 설정
	//val()함수는 jQuery에서 input태그의 value를 설정하기 위해 제공되는 함수.
 	$('input:text[name="from"]').val(goMail);
	$('input:text[name="to"]').val(naMail);	
});
</script>
</head>
<body>
<h2>이메일 전송하기</h2>
<form method="post" action="emailSendProcess.do">
<table border=1>
    <tr>    
        <td>
            보내는 사람 : <input type="text" name="from" />
        </td>
    </tr>
    <tr>    
        <td>
            받는 사람 : <input type="text" name="to" />
        </td>
    </tr>
    <tr>    
        <td>
            제목 : <input type="text" name="subject" size="50" 
            	value="제목은 제목일뿐" />
        </td>
    </tr>
    <tr>    
        <td>
            형식 :
            <input type="radio" name="format" 
            		value="text" checked />Text
            <input type="radio" name="format" 
            		value="html" />HTML
        </td>
    </tr>
    <tr>
        <td>
            <textarea name="content" cols="60" 
            		rows="10">내용은 내용일뿐</textarea>
        </td>
    </tr>
    <tr>
        <td>
            <button type="submit">전송하기</button>
        </td>
    </tr>
</table>
</form>
</body>
</html>

