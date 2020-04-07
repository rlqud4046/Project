<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./css/bootstrap_3-3-2.css">


<style type="text/css">
header {
	position: relative;
	margin: 0 auto;
	width: 100%;
	background-color: #fff;
	border-top: 1px solid #fff;
	z-index: 30;
}

header #notice {
	position: absolute;
	left: 12px;
	top: 30px;
	width: 470px;
	height: 15px;
	padding: 0 0 0 18px;
	font-size: 12px;
	text-align: left;
	letter-spacing: 0;
}

header #notice {
	display: block;
	position: relative;
	float: left;
	width: 400px;
	height: 15px;
	list-style: none;
}

.marquee {
	height: 25px;
	width: 420px;
	overflow: hidden;
	position: relative;
}

.marquee li {
	display: block;
	width: 200%;
	height: 30px;
	position: absolute;
	overflow: hidden;
	animation: marquee 5s linear infinite;
}

.marquee li {
	float: left;
	width: 50%;
}

@keyframes marquee {
  0% { top: 0; }
  100% { top: -100%; }
}
#logo {
	position: fixed;
	left: 855px;
	top: 30px;
}

#top_login {
	position: fixed;
	left: 1650px;
	top: 30px;
}

ul {
	list-style: none;
	padding-left: 0px;
}
</style>
</head>
<body>
	<header class="container">
		<div id="notice">
			<span >NOTICE</span>
			<ul id="marquee2" class="marquee">
				<li><a href="">글 제목이 나오는 공간</a></li>
				<!-- <li><a href="">112321323</a></li> -->
			</ul>
		</div>
	<div id="logo">
			<a href="./main.jsp"><img src="./images/sist.jpg" width="200" border="0"></a>
		</div>
		<div id="top_login">
			<a href="./main.jsp">홈</a>
			<c:if test="${!empty name }">
				<a href="<%=request.getContextPath()%>/logout.do">로그아웃</a>
			</c:if>
			<c:if test="${empty name }">
				<a href="<%=request.getContextPath()%>/login.do">로그인</a>
			</c:if>
			<c:if test="${!empty name }">
				<a href="">${name }님</a>
			</c:if>
			<c:if test="${empty name }">
				<a href="<%=request.getContextPath()%>/join.do">회원가입</a>
			</c:if>
			<a href=""><img src="./images/letter.jpg" width="30"></a> 
		</div>
		<br> <br> <br><br>
		<hr>
		<div align="center">
		<c:set var="g_no" value="null" />
		<%-- <c:if test="${!empty g_no }"> 비어있지 않으면 해당 그룹 메인화면으로 가는 이미지
			<a href="main.do?group_no=<%=%>"><img src="./images/sist.jpg" width="200" border="0"></a>
		</c:if> --%>
		
		<%-- <c:if test="${empty g_no }"> 비어있으면 메인화면으로 가는 이미지 --%>
			<a href="location.href='main.jsp'"><img src="./images/sist.jpg" width="200" border="0"></a>
		<%-- </c:if> --%>
		</div>
		<hr>
	</header>


</body>
</html>