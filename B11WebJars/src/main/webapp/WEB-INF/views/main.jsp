<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SpringBoot</title>
<!-- 부트스트랩 사용을 위한 css, js 파일 링크 -->
<link rel="stylesheet" href="/webjars/bootstrap/5.3.7/css/bootstrap.min.css" />
<script src="/webjars/bootstrap/5.3.7/js/bootstrap.bundle.js"></script>
<!-- jQuery 사용을 위한 코어파일 링크 -->
<script src="/webjars/jquery/3.7.1/jquery.js"></script>

</head>
<body>
	<h2>Spring boot Project 세팅하기</h2>
	<ul>
		<li><a href="/">루트</a></li>
		<li><a href="/json.do">simple-json 라이브러리 사용하기</a></li>
		<li><a href="/jsonquiz.do">simple-json 라이브러리</a></li>
	</ul>
	
	<h2>Webjars - 부트스트랩</h2>
	<button type="button" class="btn">Basic</button>
	<button type="button" class="btn btn-primary">Primary</button>
	<button type="button" class="btn btn-secondary">Secondary</button>
	<button type="button" class="btn btn-success">Success</button>
	<button type="button" class="btn btn-info">Info</button>
	<button type="button" class="btn btn-warning">Warning</button>
	<button type="button" class="btn btn-danger">Danger</button>
	<button type="button" class="btn btn-dark">Dark</button>
	<button type="button" class="btn btn-light">Light</button>
	<button type="button" class="btn btn-link">Link</button>
	
	<h2>Webjars - jQuery</h2>
	<button type="button" id="myBtn" class="btn btn-warning">
		클릭하세요
	</button>
	<script>
	//jQuery의 엔트리 포인트
	$(function(){
		//HTML문서의 로드가 완료된 후 이 부분이 실행된다.
		alert('jquery 로드 완료');
		
		/* 특정 엘리먼트에 접근할때는 CSS의 선택자를 사용하면된다.
		id='myBtn'인 엘리먼트를 클릭하면 내부의 익명함수가 동작하는 방식이다.*/
		$('#myBtn').click(function(){
			alert("jQuery 동작하나요?");
		});
	});
	</script>
	
	 <button type="button" class="btn btn-danger"
	  data-bs-toggle="modal" data-bs-target="#myModal">
    모달창 열기
  </button>
</body>
</html>

<!-- The Modal -->
<div class="modal fade" id="myModal">
  <div class="modal-dialog">
    <div class="modal-content">

      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title">Model타이틀</h4>
        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
      </div>

      <!-- Modal body -->
      <div class="modal-body">
        Modal창으로 댓글 기능 구현하기
      </div>

      <!-- Modal footer -->
      <div class="modal-footer">
        <button type="button" class="btn btn-danger"
         data-bs-dismiss="modal">닫기</button>
      </div>
    </div>
  </div>
</div>
