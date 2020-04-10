<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="//code.jquery.com/jquery-1.12.0.min.js"></script>
<script src="//cdn.datatables.net/1.10.10/js/jquery.dataTables.min.js"></script>
<link rel="stylesheet" type="text/css" href="//cdn.datatables.net/1.10.10/css/jquery.dataTables.min.css">

<style type="text/css">
.pagination {
   margin-top: 32px;
   height: 40px;
   padding-top: 16px;
   text-align: center;
}

.dataTables_filter{
display: none;
}
.dataTables_length{
display: none;
}

.dataTables_info{
display: none;
}


</style>

</head>
<body>

	<div align="center">
	<table class="table table-bordered table-hover">
			<tr class="form-inline">
				<td>모임이름</td><td>게시판이름</td> <td>글제목</td> <td>작성자</td>
				<td>작성일</td> <td>조회수</td> <td>좋아요</td>
			</tr>
			
			<c:set var="list" value="${searchList }" />
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
			
		</table>
		
		 <script type="text/javascript">
$(document).ready( function () {
    $('#table_list').DataTable();
} );   
</script>
		
		<%-- <div>
			<ul class="pagination">
					<c:if test="${page > block }">
						<li class="paginate_button previous">
							<a href="likeBoard_search.do?page=1&find_field=${find_field }&find_name=${find_name}">◀◀</a></li>
						<li> <a href="likeBoard_search.do?page=${startBlock - 1 }&find_field=${find_field }&find_name=${find_name}">◀</a></li>
					</c:if>
					
					<c:forEach begin="${startBlock }" end="${endBlock }" var="i">
						<c:if test="${i == page }">
							<li class="active"> <a href="likeBoard_search.do?page=${i }&find_field=${find_field }&find_name=${find_name}">${i }</a></li>
						</c:if>
						
						<c:if test="${i != page }">
							<li> <a href="likeBoard_search.do?page=${i }&find_field=${find_field }&find_name=${find_name}">${i }</a></li>
						</c:if>
					</c:forEach>
					
					<c:if test="${endBlock < allPage }">
						<li> <a href="likeBoard_search.do?page=${endBlock + 1 }&find_field=${find_field }&find_name=${find_name}">▶</a></li>
						<li class="paginate_button next">
							<a href="likeBoard_search.do?page=${allPage }&find_field=${find_field }&find_name=${find_name}">▶▶</a></li>
					</c:if>
			</ul>
		</div> --%>
	</div>
		

</body>
</html>