<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="//code.jquery.com/jquery-1.12.0.min.js"></script>
<script src="//cdn.datatables.net/1.10.10/js/jquery.dataTables.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="//cdn.datatables.net/1.10.10/css/jquery.dataTables.min.css">

<style type="text/css">



</style>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
</head>
<body>
<div align="center">
	<table class="table table-bordered table-hover" id="table_list" width="700">
		<thead>
			<tr class="form-inline">
				<td>모임이름</td><td>게시판이름</td> <td>글제목</td> <td>작성자</td>
				<td>작성일</td> <td>조회수</td> <td>좋아요</td>
			</tr>
		</thead>
			
		<tbody>
			<c:set var="list" value="${List }" />
			<c:if test="${!empty list }">
				<c:forEach items="${list }" var="dto">
					<tr class="form-inline">
						<td> ${dto.getGroup_name() }</td>
							<c:if test="${dto.getBoard_category() == 1}">
							<td> 자유게시판 </td>
							</c:if>
							<c:if test="${dto.getBoard_category() == 2}">
							<td> 공지사항 </td>
							</c:if>
							<c:if test="${dto.getBoard_category() == 3}">
							<td> 가입인사 </td>
							</c:if>
							<c:if test="${dto.getBoard_category() == 4}">
							<td> 정모게시판 </td>
							</c:if>
							<c:if test="${dto.getBoard_category() == 5}">
							<td> 자유게시판 </td>
							</c:if>
							<c:if test="${dto.getBoard_category() == 6}">
							<td> 사진첩 </td>
							</c:if>
						<td> ${dto.getBoard_title() } </td>
						<td> ${dto.getBoard_writer() } </td>
						<td> ${dto.getBoard_date().substring(0,10) } </td>
						<td> ${dto.getBoard_hit() } </td>
						<td> ${dto.getBoard_like() } </td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${empty list }">
				<tr class="form-inline">
					<td colspan="7">
						<h3>검색된 레코드가 없습니다.</h3>
					</td>	
				</tr>
			</c:if>
			</tbody>
			
		</table>
		
		<script type="text/javascript">
$(document).ready( function () {
    $('#table_list').DataTable();
} );   
</script>
		
	<%-- 	<div>
			<ul class="pagination">
					<c:if test="${page > block }">
						<li class="paginate_button previous">
							<a href="like_list.do?page=1">◀◀</a></li>
						<li> <a href="like_list.do?page=${startBlock - 1 }">◀</a></li>
					</c:if>
					
					<c:forEach begin="${startBlock }" end="${endBlock }" var="i">
						<c:if test="${i == page }">
							<li class="active"> <a href="like_list.do?page=${i }">${i }</a></li>
						</c:if>
						
						<c:if test="${i != page }">
							<li> <a href="like_list.do?page=${i }">${i }</a></li>
						</c:if>
					</c:forEach>
					
					<c:if test="${endBlock < allPage }">
						<li> <a href="like_list.do?page=${endBlock + 1 }">▶</a></li>
						<li class="paginate_button next">
							<a href="like_list.do?page=${allPage }">▶▶</a></li>
					</c:if>
			</ul>
		</div> --%>
		
	<%-- 	
		<hr width="50%" color="red">

		<br/>
		
		<form method="post" action="<%= request.getContextPath() %>/likeBoard_search.do?mem_no=2">
		
			<select name="find_field">
				<option value="title">제목</option>		
				<option value="writer">작성자</option>
			</select>
			
			<input type="text" name="find_name" />
			<input type="submit" value="검색" />
		</form> --%>
		
</div>
</body>
</html>