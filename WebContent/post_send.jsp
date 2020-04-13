<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
	
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
	<table class="table">
		<thead>
			<tr>
				<th>받은사람</th>
				<th>쪽지내용</th>
				<th>보낸날짜</th>
				<th>읽음상태</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${send_list }" var="list">
				<tr>
					<td>${list.getNickname() }(${list.getId() })</td>
					<td><a href="post_cont.do?post_no=${list.getPost_no() }&page_no=2">${list.getPost_cont() }</a></td>
					<td>${list.getPost_date() }</td>
					<td align="center">
						<c:if test="${list.getPost_read()==1 }"> x </c:if>
						<c:if test="${list.getPost_read()==0 }"> o </c:if>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#table_list').DataTable();
		});
	</script>

</body>
</html>