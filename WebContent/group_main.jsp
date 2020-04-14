<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

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
<%-- <frameset rows="10%,*">
		<frame src="group_main_bar.jsp">
		<frame src="main.do?group_no=${group_no }" name="group_view" scrolling="no">
	</frameset> --%>
<body>
	<%-- <c:set value="${group_no }" var="group_no"/> --%>
	

	 <div class="box">
		<c:set value="${group_no }" var="group_no"></c:set>
		<ul class="nav nav-tabs" role="tablist" id="myTab">
			<li role="presentation" class="active"><a href="#home"
				aria-controls="home" role="tab" data-toggle="tab"> <img alt="홈"
					src="images/home.png" width="30px"></a></li>
			<li role="presentation"><a id="noticeBoard" href="#notice" aria-controls="home"
				role="tab" data-toggle="tab">공지사항</a></li>
			<li role="presentation"><a id="hiBoard" href="#hi_board"
				aria-controls="profile" role="tab" data-toggle="tab">가입인사</a></li>
			<li role="presentation"><a id="meetBoard" href="#meet_board"
				aria-controls="messages" role="tab" data-toggle="tab">정모게시판</a></li>
			<li role="presentation"><a id="freeBoard" href="#free_board"
				aria-controls="settings" role="tab" data-toggle="tab">자유게시판</a></li>
			<li role="presentation"><a id="photoBoard" href="#photo_board"
				aria-controls="settings" role="tab" data-toggle="tab">사진첩</a></li>
			<li role="presentation"><a  href="#management"
				aria-controls="settings" role="tab" data-toggle="tab">모임관리</a></li>
		</ul>

		<div class="tab-content">
			<div role="tabpanel" class="tab-pane active" id="home">
				<jsp:include page="group_main_home.jsp"></jsp:include>
			</div>
			<div role="tabpanel" class="tab-pane" id="notice">공지사항 게시판</div>
			<div role="tabpanel" class="tab-pane" id="hi_board"><jsp:include page="${include }"></jsp:include> </div>
			<div role="tabpanel" class="tab-pane" id="meet_board">정모게시판게시판</div>
			<div role="tabpanel" class="tab-pane" id="free_board">자유게시판게시판</div>
			<div role="tabpanel" class="tab-pane" id="photo_board">사진첩</div>
			<div role="tabpanel" class="tab-pane" id="management">모임관리</div>
		</div>


	</div>
	<script type="text/javascript">
	$(function(){
		$('#noticeBoard').on('click', function(){
			location.href='board_list.do?board_category=2&group_no=2';
		});
		$('#hiBoard').on('click', function(){
			location.href='board_list.do?board_category=3&group_no=2';
		});
		$('#meetBoard').on('click', function(){
			location.href='board_list.do?board_category=4&group_no=2';
		});
		$('#freeBoard').on('click', function(){
			location.href='board_list.do?board_category=5&group_no=2';
		});
		$('#photoBoard').on('click', function(){
			location.href='board_list.do?board_category=6&group_no=2';
		});
		
	});
	</script> 

</body>
</html>