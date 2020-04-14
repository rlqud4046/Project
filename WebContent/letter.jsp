<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

		
	<frameset rows="7%, *" frameborder=0>
				<frame src="post_button.do?mem_no=${mem_no }" scrolling="no">
				<frame src="post_receive.do?mem_no=${mem_no }" name="post_list" scrolling="no">
	</frameset>
<body>
<c:set value="${mem_no }" var="mem_no"/>

	
</body>
</html>