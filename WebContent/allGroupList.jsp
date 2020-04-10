<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
</head>
<body>

	<div align="center">
		<div class="form-inline">
		<form method="post" action="searchAllGroupList.do">
		 	<select class="form-control" name="area">
				<option value="">지역선택</option>
					<c:set var="city" value="${City }" />
						<c:if test="${!empty city }">
							<c:forEach items="${city }" var="citys">
								<option value="${citys.getCity() }" >${citys.getCity() }</option>
							</c:forEach>
						</c:if>
			</select>  
			
			<select class="form-control" name="interest">
				<option value="">대분류선택</option>
					<c:set var="inter" value="${Inter }" />
						<c:if test="${!empty inter }">
							<c:forEach items="${inter }" var="inters">
								<option  value="${inters.getL_category() }">${inters.getL_category() }</option>
							</c:forEach>
						</c:if>
			</select>
			<input  class="btn btn-default" type="submit" value="검색">
		</form>
		</div>
	</div>

	<br /><br /><br /><br /><br />

	<table class="table table-bordered table-hover">
		
		<tr>
			<th>모임이미지</th><th>모임명</th><th>카테고리</th><th>지역</th><th>회원수</th><th>모임설명</th>
		</tr>
		
		<c:set var="list" value="${List }" />
			<c:if test="${!empty list }">
				<c:forEach items="${list }" var="dto">
					<tr>
						<td>${dto.getGroup_main_img() }</td>
						<td>${dto.getGroup_name() }</td>
						<td>${dto.getL_category() }</td>
						<td>${dto.getCity() }</td>
						<td>${dto.getGroup_currmem() }</td>
						<td>${dto.getGroup_intro() }</td>
					</tr>
				</c:forEach>
				</c:if>
				<c:if test="${empty list }">
					<tr>
						<td colspan="6">
							<h3>데이터가 없습니다.</h3>
						</td>
					</tr>
				</c:if>
		
	</table>
	
	<div align="center">
			<ul class="pagination">
					<c:if test="${page > block }">
						<li class="paginate_button previous">
							<a href="allGroupList.do?page=1">◀◀</a></li>
						<li> <a href="allGroupList.do?page=${startBlock - 1 }">◀</a></li>
					</c:if>
					
					<c:forEach begin="${startBlock }" end="${endBlock }" var="i">
						<c:if test="${i == page }">
							<li class="active"> <a href="allGroupList.do?page=${i }">${i }</a></li>
						</c:if>
						
						<c:if test="${i != page }">
							<li> <a href="allGroupList.do?page=${i }">${i }</a></li>
						</c:if>
					</c:forEach>
					
					<c:if test="${endBlock < allPage }">
						<li> <a href="allGroupList.do?page=${endBlock + 1 }">▶</a></li>
						<li class="paginate_button next">
							<a href="allGroupList.do?page=${allPage }">▶▶</a></li>
					</c:if>
			</ul>
	</div>



</body>
</html>