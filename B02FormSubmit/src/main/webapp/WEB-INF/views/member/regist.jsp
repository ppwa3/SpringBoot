<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./css/Reg.css" />
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="./js/memberValidate.js"></script>
</head>
<body>
	<form name="myform" action="/regpro.do" method="post"
		onsubmit="return formValidate(this);">
		<table class="userTable">
			<colgroup>
				<col width="130px" />
				<col width="540px" />
			</colgroup>
			<tr>
				<td class="userTit"><span class="c_imp">*</span> 아이디</td>
				<td class="userVal"><input type="text" name="id" value=""
					maxlength="15" class="userInput" style="width: 120px;" />
					<button type="button" class="btn_search"
						onClick="idCheck(this.form);">중복확인</button> <span
					style="margin-left: 10px;"></span> <span style="color: #888888;">+
						4~15자, 첫 영문자, 영문자와 숫자 조합</span></td>
			</tr>
			<tr>
				<td class="userTit"><span class="c_imp">*</span> 비밀번호</td>
				<td class="userVal"><input type="password" name="pass1"
					value="" style="width: 100px;" class="userInput" maxlength="25" />
				</td>
			</tr>
			<tr>
				<td class="userTit"><span class="c_imp">*</span> 비밀번호 확인</td>
				<td class="userVal"><input type="password" name="pass2"
					value="" style="width: 100px;" class="userInput" maxlength="25" />
					<span style="margin: 0 0 0 3px; color: #888888;">(확인을 위해 다시
						입력해 주세요.)</span></td>
			</tr>
			<tr>
				<td class="userTit"><span class="c_imp">*</span> 이름</td>
				<td class="userVal"><input type="text" name="name" value=""
					style="width: 120px;" class="userInput" maxlength="10" /></td>
			</tr>
			<tr>
				<td class="userTit"><span style="margin-left: 10px;"></span>
					생년월일</td>
				<td class="userVal"><label><input type="radio"
						name="sex" value="male" />남자</label> <label><input type="radio"
						name="sex" value="female" />여자</label> <span style="margin-left: 20px;"></span>
					<input type="text" name="birth1" value="" class="userInput w40"
					maxlength="4" /><span style="margin: 0 5px 0 1px;">년</span> <input
					type="text" name="birth2" value="" class="userInput w20"
					maxlength="2" /><span style="margin: 0 5px 0 1px;">월</span> <input
					type="text" name="birth3" value="" class="userInput w20"
					maxlength="2" /><span style="margin: 0 15px 0 1px;">일</span> <span
					style="margin: 0 0 0 3px; color: #888888;">(예 : 2000년 01월
						31일)</span></td>
			</tr>
			<tr>
				<td class="userTit" rowspan="2"><span class="c_imp">*</span>
					이메일</td>
				<td class="userVal"><input type="text" name="email1" value=""
					class="userInput w100" /> <span style="font-size: 16px;">＠</span>
					<input type="text" name="email2" value="" class="userInput w100" />
					<select name="email_domain" onchange="inputEmail(this.form);"
					class="userSelect w100">
						<option value="">-- 선택 --</option>
						<option value="naver.com">naver.com</option>
						<option value="nate.com">nate.com</option>
						<option value="gmail.com">gmail.com</option>
						<option value="daum.net">daum.net</option>
						<option value="hanmail.net">hanmail.net</option>
						<option value="직접입력" selected>직접입력</option>
				</select> <label><input type="radio" name="mailing" value="yes" />수신허용</label>
					<label><input type="radio" name="mailing" value="no" />수신불가</label>
				</td>
			</tr>
			<tr>
				<td class="userVal" style="height: 25px; color: #888888;">※
					hanmail.net은 메일 수신이 어려울 수 있으니 가급적 다른 메일을 이용하시기 바랍니다.</td>
			</tr>
			<tr>
				<td class="userTit" rowspan="3"><span
					style="margin-left: 10px;"></span> 주소</td>
				<td class="userVal">
					<button type="button" class="btn_search" onClick="postOpen();">주소찾기</button>
					<input type="text" name="zipcode" maxlength="10" value=""
					class="userInput w50" /> (우편번호)
				</td>
			</tr>
			<tr>
				<td class="userVal"><input type="text" name="addr1"
					class="userInput w510" maxlength="100" value="" /></td>
			</tr>
			<tr>
				<td class="userVal"><input type="text" name="addr2"
					class="userInput w410" maxlength="100" value="" /> <span
					style="margin-left: 10px;"></span> + 나머지 주소</td>
			</tr>
			<tr>
				<td class="userTit"><span style="margin-left: 10px;"></span>
					전화번호</td>
				<td class="userVal"><input type="text" name="phone1" value=""
					class="userInput w30" maxlength="3"
					onkeyup="focusMove(this,'phone2',3);" /> - <input type="text"
					name="phone2" value="" class="userInput w40" maxlength="4"
					onkeyup="focusMove(this,'phone3',4);" /> - <input type="text"
					name="phone3" value="" class="userInput w40" maxlength="4"
					onkeyup="focusMove(this,'mobile1',4);" /></td>
			</tr>
			<tr>
				<td class="userTit"><span class="c_imp">*</span> 휴대전화</td>
				<td class="userVal"><input type="text" name="mobile1" value=""
					class="userInput w30" onkeyup="focusMove(this,'mobile2',3);" /> - <input
					type="text" name="mobile2" value="" class="userInput w40"
					maxlength="4" onkeyup="focusMove(this,'mobile3',4);" /> - <input
					type="text" name="mobile3" value="" class="userInput w40"
					maxlength="4" onkeyup="focusMove(this,'etc_no1',4);" /> <span
					style="margin-left: 20px;"></span> <label><input
						type="radio" name="sms" value="yes" />SMS 수신허용</label> <label><input
						type="radio" name="sms" value="no" />SMS 수신불가</label></td>
			</tr>
			<tr>
				<td class="userTit"><span class="c_imp">*</span> 관심분야</td>
				<td class="userVal" style="padding: 3px 0 3px 10px;"><select
					name="etc_no1" class="userSelect w300">
						<option value="">:: 관심분야 선택 ::</option>
						<option value="1">책을 출판하기 위해서</option>
						<option value="2">인쇄/제본이 필요해서</option>
						<option value="3">1인출판사 지원서비스를 이용하기 위해서</option>
						<option value="4">도서를 구입하기 위해서</option>
						<option value="5">나만의 책만들기</option>
						<option value="6">기타</option>
				</select></td>
			</tr>
			<tr>
				<td class="userTit"><span class="c_imp">*</span> 가입경로</td>
				<td class="userVal" style="padding: 3px 0 3px 10px;"><select
					name="etc_no2" class="userSelect w300">
						<option value="">:: 가입경로 선택 ::</option>
						<option value="91">네이버 검색</option>
						<option value="92">다음(Daum) 검색</option>
						<option value="93">기타 인터넷 검색</option>
						<option value="94">아는 사람 소개</option>
						<option value="95">북랩의 책을 보고</option>
						<option value="96">기타</option>
				</select></td>
			</tr>
		</table>
		<table style="width: 670px; margin-top: 20px;">
			<tr>
				<td align="center"><input type="submit" value="등록하기"
					class="btn_submit" /> &nbsp;&nbsp; <input type="reset"
					value="새로쓰기" class="btn_cancel" /></td>
			</tr>
		</table>
	</form>
	<div id="id_loading">
		<img src="../images/loading.gif" style="width: 40%;" />
	</div>
</body>
</html>