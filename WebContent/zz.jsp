<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
.pagination {
	margin-top: 32px;
	height: 40px;
	padding-top: 16px;
	text-align: center;
}

.dataTables_filter {
	display: none;
}

.dataTables_length {
	display: none;
}

.dataTables_info {
	display: none;
}
</style>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

</head>
<body>


	<table class="table" id="table_list">
		<thead>
			<tr>
				<th>글번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>조회수</th>
				<th>좋아요</th>
			</tr>
		</thead>
		<tbody>
			<c:set var="list" value="${List }" />
			<c:if test="${!empty list }">
				<c:forEach items="${list}" var="dto">
					<tr>
						<td>${dto.getBoard_no() }</td>
						<td><a href="board_cont.do?no=${dto.getMgn_no() }">${dto.getBoard_title() }</a>
						</td>
						<td>${dto.getBoard_writer() }</td>
						<td>${dto.getBoard_date().substring(2,10) }</td>
						<td>${dto.getBoard_hit() }</td>
						<td>${dto.getBoard_like()}</td>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
		<c:if test="${empty list} ">
			<tr>
				<td colspan="6">데이터 없음</td>
			</tr>

		</c:if>
		<tr>
			<td colspan="6" align="right"><input type="button" value="글쓰기"
				onclick="location.href='<%=request.getContextPath()%>/board_write.do'">
			</td>
		</tr>

	</table>
	<script type="text/javascript">
$(document).ready( function () {
    $('#table_list').DataTable();
} );   
</script>

	<%-- <div align="center">
            <ul class="pagination">
               <c:if test="${page>block }">
                  <li class="paginate_button previous"><a href="board_list.do?group_no=${group_no }&board_category=${board_category}&page=1">◀◀</a></li>
                  첫번째 페이지로
                  <li><a href="board_list.do?group_no=${group_no }&board_category=${board_category}&page=${startBlock-1 }">◀</a></li>
                  이전 블록의 마지막 페이지로
               </c:if>
               <c:forEach begin="${startBlock }" end="${endBlock }" var="i">
                  <c:if test="${i == page }">
                     현재 페이지를 클릭하려 할 경우
                     <li class="active"><a href="board_list.do?group_no=${group_no }&board_category=${board_category}&page=${i }">${i }</a></li>
                  </c:if>

                  <c:if test="${i != page }">
                     <li><a href="board_list.do?group_no=${group_no }&board_category=${board_category}&page=${i }">${i }</a></li>
                  </c:if>



               </c:forEach>

               <c:if test="${endBlock < allPage }">
                  <li><a href="board_list.do?group_no=${group_no }&board_category=${board_category}&page=${endBlock+1 }">▶</a></li>
                  이전 블록의 마지막 페이지로
                  <li class="paginate_button next"><a href="board_list.do?group_no=${group_no }&board_category=${board_category}&page=${allPage }">▶▶</a></li>
                  첫번째 페이지로
               </c:if>
            </ul>
         </div> --%>
	<hr width="100%">
	<div align="center">
		<form method="post"
			action="<%=request.getContextPath()%>/board_search.do">

			<select name="find_field">
				<option value="title">글제목</option>
				<option value="cont">글내용</option>
				<option value="title_cont">제목+내용</option>
				<option value="writer">작성자</option>
			</select> <input type="text" name="find_name"> <input type="submit"
				value="검색">
		</form>
	</div>



</body>
</html>