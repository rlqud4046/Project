<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사진첩 테스트 페이지</title>
</head>
<body>
  <div align="center">
    <hr>
	<h3>사진첩 테스트</h3>
	<hr>
    <input type="button" value="test" onclick="location.href='<%=request.getContextPath()%>/getGalleryList.do'">
  </div>
</body>
</html>