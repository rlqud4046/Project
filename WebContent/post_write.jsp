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

	<form method="post">
		<c:set value="${mem_no }" var="mem_no"/>
		<table class="table">
			<tr>
				<th>받는사람</th>
				<td><input type="text" name="receiverId" placeholder="아이디를 입력해주세요"> 
					<input type="hidden" name="mem_no" value="${mem_no }"> </td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea rows="10" cols="45" style="resize: none;" name="cont"></textarea>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="보내기" onclick="javascript: form.action='post_write_ok.do';"> 
					<input type="reset" value="다시작성"> 
					<input type="submit" value="뒤로가기" onclick="javascript: form.action='post_send.do';"> 
				</td>
			</tr>
		
		</table>
	</form>
	
</body>
</html>