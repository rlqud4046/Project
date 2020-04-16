<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>


<link rel="stylesheet" type="text/css" href="//cdn.datatables.net/1.10.10/css/jquery.dataTables.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="//code.jquery.com/jquery-1.12.0.min.js"></script>
<script src="//cdn.datatables.net/1.10.10/js/jquery.dataTables.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>



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


</head>
<body>
	<c:set value="${receive_list }" var="receive_list"></c:set>
	<table class="table" id="table_list">
		<thead>
			<tr>
				<th></th>
				<th>보낸사람</th>
				<th>쪽지내용</th>
				<th>보낸날짜</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${receive_list }" var="list">
				<tr>
					<td>
						<input type="checkbox" name="checkPost" value="${list.getPost_no() }">
					</td>
					<td>${list.getNickname() }(${list.getId() })</td>
					<td>
						<a href="post_cont.do?post_no=${list.getPost_no() }&page_no=1">${list.getPost_cont() }</a>
					</td>
					<td>${list.getPost_date() }</td>
				</tr>
			</c:forEach>
		</tbody>
			<tr>

			<td colspan="4" align="center">
				<input type="button" onclick="total_d()" value="삭제!"> 
			</td>
		</tr>

		
	</table>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#table_list').DataTable({

				"lengthChange" : false,
				"info" : false,
				"order" : [ [ 0, "desc" ] ],
				"paingType" : "full_numbers",
				"language" : {
					"infoEmpty" : "받은 메세지가 없습니다.",
					"search" : "검색 : ",
					"paginate" : {
						"next" : "다음",
						"previous" : "이전"
					}
				}
			});
		});

		function total_d(){
		      var total_check = new Array();
		      var checkbox = $('input[name="checkPost"]:checked');
		      //alert(checkbox.length);
		      for(var i=0; i<checkbox.length; i++){
		         total_check.push(checkbox[i].value);
		      }
		      location.href='post_check_del.do?total_check='+encodeURI(total_check)+'&post_page=1';
		      
		      
		      };
	</script>


</body>
</html>