<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div align="center">
	<h3>모임정보 변경 테스트</h3>
	<input type="button" value="test" onclick="location.href='<%=request.getContextPath()%>/groupUpdate.do?group_no=5'">
</div>
</body>
</html>