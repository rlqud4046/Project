<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<script src="js/jquery-1.12.0.js"></script>
<script src="//cdn.datatables.net/1.10.10/js/jquery.dataTables.min.js"></script>


<style type="text/css">
footer {
	width: 100%;
	height: 200px;
	bottom: 0;
	background: #d5d5d5;
	text-align: center;
	color: white;
}
</style>
</head>
<body>

	<jsp:include page="include/header.jsp"></jsp:include>

	<section class="container">
		<!-- 비어있으면 index 페이지로 가게  -->
		<c:if test="${!empty page }">
			<article><jsp:include page="${page }"></jsp:include></article>
		</c:if>
		<c:if test="${empty page }">
			<article><jsp:include page="gotoboardlist.jsp"></jsp:include></article>
		</c:if>
	</section>

	<aside><jsp:include page="include/side.jsp"></jsp:include></aside>
	<footer><jsp:include page="include/foot.jsp"></jsp:include></footer>
 


</body>
</html>