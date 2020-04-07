<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
</style>
<link rel="stylesheet" href="./css/bootstrap_3-3-2.css">

</head>
<body>

	<jsp:include page="include/header.jsp"></jsp:include>

	<table class="table">
		<tr>
			<th>글번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회수</th>
			<th>좋아요</th>
		</tr>

		<c:set var="list" value="${List }" />
		<c:if test="${!empty list }">
			<c:forEach items="${list}" var="dto">
				<tr>
					<td>${dto.getBoard_no() }</td>
					<td>
						<a href="upload_cont.do?num=${dto.getMgn_no() }">${dto.getBoard_title() }</a>
					</td>
					<td>${dto.getBoard_hit() }</td>
					<td>${dto.getBoard_date().substring(0,10) }</td>
					<td>${dto.getBoard_hit() }</td>
					<td>${dto.getBoard_like()}</td>
				</tr>
			</c:forEach>
		</c:if>

	</table>


</body>
</html>