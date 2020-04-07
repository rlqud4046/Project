<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>	
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./css/bootstrap_3-3-2.css">
</head>
<body>

<header><jsp:include page="include/header.jsp"></jsp:include></header>


<section class="container">
<a href="<%=request.getContextPath() %>/board_list.do">글 목록 보기</a>
</section>



</body>
</html>