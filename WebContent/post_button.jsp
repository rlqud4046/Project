<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div align="left">
	<c:set value="${mem_no }" var="mem_no"/>
	<a href="post_receive.do?mem_no=${mem_no }" target="post_list">받은쪽지함</a>
	<a href="post_send.do?mem_no=${mem_no }" target="post_list">보낸쪽지함</a>

	<a href="post_write.do?mem_no=${mem_no }" target="post_list">쪽지쓰기</a>
</div>
</body>
</html>