<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
</head>
<body>

<div class="container">
<div class="jumbotron">
  <h2 class="text-center">프리미엄으로 전환 하시겠습니까??</h2>
  <p class="text-center">결제금액 5천만원입니다. 최대 모임 인원수가 400명으로 늘어납니다. </p>
  <p class="text-center"><a class="btn btn-danger btn-lg" href="chang_premium_ok.do?group_no=${no }" role="button">전환하기</a>
   						 <a class="btn btn-default btn-lg" href="GroupManagement.do" role="button">취소</a></p>
</div>
</div>

</body>
</html>