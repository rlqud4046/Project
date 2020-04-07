<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../css/bootstrap_3-3-2.css">


<style type="text/css">
</style>
</head>
<body>

<%-- 그룹 넘버를 갖고있으면 해당 모임에 들어 가 있는것이므로 메인 이미지가 해당 모임의 메인이미지로 변경되고 클릭하면 해당 모임의 메인화면으로 감
그룸 넘버를 갖고있지 않다면 메인 페이지나 그 근처이므로 전체 메인페이지의 이미지가 떠있고 클릭시 메인 화면으로 간다. --%>


	<div id="header_main">
		<c:set var="g_no" value="null" />
		<%-- <c:if test="${!empty g_no }"> 비어있지 않으면 해당 그룹 메인화면으로 가는 이미지
			<a href="main.do?group_no=<%=%>"><img src="./images/sist.jpg" width="200" border="0"></a>
		</c:if> --%>
		
		<%-- <c:if test="${empty g_no }"> 비어있으면 메인화면으로 가는 이미지 --%>
			<a href="location.href='main.jsp'"><img src="./images/sist.jpg" width="200" border="0"></a>
		<%-- </c:if> --%>

	</div>


</body>
</html>