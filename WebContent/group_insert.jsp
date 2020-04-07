<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./css/bootstrap_3-3-2.css">
</head>
<body>

	<form>
		<div class="form-group">
			<label for="groupName">그룹 명칭</label> <input type="text"
				class="form-control" id="groupName" placeholder="모임명을 입력하세요">
		</div>
		<select class="form-control" >
			<c:set 
			<c:forEach ></c:forEach>
			<option>1</option>
		</select>

		<div class="checkbox">
			<label> <input type="checkbox"> 입력을 기억합니다
			</label>
		</div>
		<button type="submit" class="btn btn-default">제출</button>

	</form>


</body>
</html>