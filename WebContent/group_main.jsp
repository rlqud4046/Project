<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
	integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
	crossorigin="anonymous"></script>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

<style type="text/css">
.box {
	text-align: center;
}

.box ul {
	display: inline-block;
	display: zoom:1;
}

.box ul li {
	float: left;
	margin-left: -1px;
	z-index: 1;
}
</style>


</head>
<body>

	<div class="box">
		<c:set value="${group_no }" var="group_no"></c:set>
		<ul class="nav nav-tabs" role="tablist" id="myTab">
			<li role="presentation" class="active"><a href="#home"
				aria-controls="home" role="tab" data-toggle="tab"> <img alt="홈"
					src="images/home.png" width="30px"></a></li>
			<li role="presentation"><a href="#notice" aria-controls="home"
				role="tab" data-toggle="tab">공지사항</a></li>
			<li role="presentation"><a href="#hi_board"
				aria-controls="profile" role="tab" data-toggle="tab">가입인사</a></li>
			<li role="presentation"><a href="#meet_board"
				aria-controls="messages" role="tab" data-toggle="tab">정모게시판</a></li>
			<li role="presentation"><a href="#free_board"
				aria-controls="settings" role="tab" data-toggle="tab">자유게시판</a></li>
			<li role="presentation"><a href="#photo_board"
				aria-controls="settings" role="tab" data-toggle="tab">사진첩</a></li>
			<li role="presentation"><a href="#management"
				aria-controls="settings" role="tab" data-toggle="tab">모임관리</a></li>
		</ul>


		<div class="tab-content">
			<div role="tabpanel" class="tab-pane active" id="home">
				<jsp:include page="group_main_home.jsp"></jsp:include>
			</div>
			<div role="tabpanel" class="tab-pane" id="notice">공지사항 게시판</div>
			<div role="tabpanel" class="tab-pane" id="hi_board">가입인삳게시판</div>
			<div role="tabpanel" class="tab-pane" id="meet_board">정모게시판게시판</div>
			<div role="tabpanel" class="tab-pane" id="free_board">자유게시판게시판</div>
			<div role="tabpanel" class="tab-pane" id="photo_board">사진첩</div>
			<div role="tabpanel" class="tab-pane" id="management">모임관리</div>
		</div>


	</div>

</body>
</html>