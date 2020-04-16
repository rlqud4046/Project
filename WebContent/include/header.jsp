<%@page import="model.BoardDTO"%>
<%@page import="java.util.List"%>
<%@page import="model.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./css/bootstrap_3-3-2.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script src="./js/header.js"></script>


<style type="text/css">
header {
	/*    position: relative;
   margin: 0 auto;
   width: 100%; */
	background-color: #fff;
	border-top: 1px solid #fff;
	/* z-index: 30; */
}

header #notice {
	/* position: absolute; */
	/* left: 12px; */
	/*    top: 30px; */
	/* width: 470px; */
	/* height: 15px; */
	/* padding: 0 0 0 18px; */
	font-size: 12px;
	text-align: left;
	letter-spacing: 0;
}

header #notice {
	/* display: block; */
	/* position: relative; */
	/* width: 400px; */
	/* height: 15px; */
	/* list-style: none; */
	
}

.marquee {
	height: 25px;
	width: 420px;
	overflow: hidden;
	position: relative;
}

.marquee li {
	/* display: block; */
	width: 200%;
	/* height: 30px; */
	position: relative;
	overflow: hidden;
	animation: marquee 5s linear infinite;
}

.marquee li {
	/* float: left;
   width: 50%; */
	
}

@keyframes marquee {
  0% { top: 0; }
  100% { top: -100%; }
}

#logo {
	/* position: fixed;
   left: 855px;
   top: 30px; */
	
}

#top_login a {
	/*    position: fixed;
   left: 1650px;
   top: 30px; */
	color: #222;
	text-decoration: none;
}

ul {
	list-style: none;
	padding-left: 0px;
}
</style>


<script type="text/javascript">
$(function(){
	$('#homebtn').on('click', function(){
		$.ajax({
			type: "get",
			url: "home.jsp",
			success: function(data){
				
			}
		});
	});
});


</script>

<%-- 
$(function invali() {
	<% session.removeAttribute("group_No");%>
})
 --%>
</head>
<body>
<%
BoardDAO dao = BoardDAO.getInstance();

List<BoardDTO> list = dao.noticeList();
%>



	<header class="container">
		<div class="row" style="margin-top: 10px;">
			<div class="pull-left" id="notice">
				<span>NOTICE&nbsp;</span>
			</div>
			<div class="pull-left">
				<ul id="marquee2" class="marquee">
				<c:forEach items="<%=list %>" var="notice"> 
				
					<li><a href="board_cont.do?no=${notice.getMgn_no() }">${notice.getBoard_title() }</a></li>
				</c:forEach>
					<!-- <li><a href="">112321323</a></li> -->
				</ul>
			</div>

			<div class="pull-right" id="top_login">
				<a href="samplePage.jsp" id="homebtn">홈</a>
				<c:if test="${!empty name }">
					<a href="<%=request.getContextPath()%>/logout.jsp">로그아웃</a>

				</c:if>
				<c:if test="${empty name }">
					<a href="<%=request.getContextPath()%>/login.do">로그인</a>
				</c:if>
				<c:if test="${!empty name }">
					<a tabindex="0" id="pop" role="button" data-trigger="focus" data-placement="bottom">${name }님</a>

				</c:if>
				<c:if test="${empty name }">
					<a href="<%=request.getContextPath()%>/join.do">회원가입</a>
				</c:if>
				<img src="images/letter.jpg" onclick="window.open('letter.do?mem_no=${sessionScope.mem_no}','window_name','width=550,height=700,location=no,status=no,scrollbars=yes');" width="30">

			</div>
		</div>
		<div class="clearfix"></div>

		<div class="row">
			<div id="mypop" class="container hide">
				<div class="row">
					<div class="col-sm-3 col-sm-offset-1">
						<img id="profile_popover" class="img-circle" alt="" src="https://cdn.pixabay.com/photo/2017/06/13/12/54/profile-2398783_960_720.png" width="50px" height="50px">
					</div>
					<div class="col-sm-8">
						<ul style="list-style: none;">
							<li id="nickId_popover">${sessionScope.nickname }(${sessionScope.id })</li>
							<li id="name_popover">${sessionScope.name }</li>
							<li id="e_mail_popover">${sessionScope.email }</li>
						</ul>
					</div>
					<div class="col-sm-12">
						<div class="col-sm-6">
							<button class="btn btn-default btn-sm" onclick="location.href='mypage.do'">마이페이지</button>
						</div>
						<div class="col-sm-6">
							<button class="btn btn-default btn-sm" onclick="location.href='mygroup_list.do?mem_no=${sessionScope.mem_no}'">가입한 모임</button>

						</div>
					</div>
				</div>
			</div>
		</div>


		<span style="clear: both;"></span>
		
		<c:if test="${!empty group_No }">
			<div class="row">
				<div class="col-md-12" align="center" id="logo">
					<a href="main.do?group_no=${group_No }"><img src="./images/group_main/gukbab.png" width="60%" border="0"></a>
					<input type="button" class="btn-success btn-lg" name="a" onclick="location.href='group_join.do?mem_no=${mem_no}&group_no=${group_No }'" value="가입하깅">
				</div>
			</div>
		</c:if>

		<c:if test="${empty group_No }">
			<div class="row">
				<div class="col-md-12" align="center" id="logo">
					<a href="samplePage.jsp"><img src="./images/main.png" width="60%" border="0"></a>
				</div>
			</div>
		</c:if>

		<hr>
	</header>
</body>
</html>