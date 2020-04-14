<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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


<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
</head>
<body>

   <jsp:include page="include/header.jsp"></jsp:include>

   <section class="container">
      <article><jsp:include page="sample.jsp"></jsp:include></article>
   </section>

   <aside><jsp:include page="include/side.jsp"></jsp:include></aside>
   <footer><jsp:include page="include/foot.jsp"></jsp:include></footer>



</body>
</html>