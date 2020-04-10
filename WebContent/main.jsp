<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>   
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style>
footer {
   width:100%;
   height:200px;
   bottom:0;
   background:#D5D5D5;
   text-align: center;
   color: white;
}
</style>
</head>
<body>
<header>
<jsp:include page="include/header.jsp"></jsp:include>
</header>
<section>
<article><jsp:include page="include/faq.jsp"></jsp:include></article>
</section>
<aside><jsp:include page="include/sidebar.jsp"></jsp:include></aside>

<footer><jsp:include page="include/foot.jsp"></jsp:include></footer>

</body>
</html>