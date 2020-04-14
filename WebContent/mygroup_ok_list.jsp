<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

<script type="text/javascript">

	function weim(){
		let weimOk = confirm("위임하러 가시겠습니까?");
		if(weimOk){
			location.href = ""
		}
	}
	
	function tal(group_no){
		let talOk = confirm("탈퇴하시겠습니까?");
		if(talOk){
			location.href = "tal.do?mem_no=2&group_no="+group_no;
		}
	}

</script>

</head>
<body>

	<div align="center" style="margin-top: 200px;">	
	
	<table class="table table-bordered table-hover">
		<tr class="form-inline">
			<th>모임명</th> <th>회원등급</th> <th>관리</th>
	    </tr>
	    <c:set var="list" value="${List }" />
	    <c:if test="${!empty list }">
	    	<c:forEach items="${list }" var="dto">
	    		<tr class="form-inline">
	    			<td>${dto.getGroup_name() }</td>
	    			<td>${dto.getRating() }</td>
	    			<c:if test="${dto.getRating() == 5 }">
	    			<td><a onclick= "weim()" class="btn btn-primary btn-lg" role="button">위임</a></td>
	    			</c:if>
	    			<c:if test="${dto.getRating() < 5 }">
	    			<td><a onclick= "tal(${dto.getGroup_no()})" class="btn btn-primary btn-lg" role="button">탈퇴</a></td> 
	    			</c:if>
	    		</tr>
	    	</c:forEach>
	    </c:if>
	    
	    <c:if test="${empty list }">
	    	<tr class="form-inline">
	    		<td colspan="3" align="center">
	    			<h3>가입한 모임이 없습니다.</h3>
	    		</td>
	    	</tr>
	    </c:if>
	   
 	   </table>
 	   </div>
 

</body>
</html>