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
	<form action="post_reply_ok.do" method="get">
		<c:set value="${senderInfo }" var="senderInfo"/>
		<table class="table">
			<tr>
				<th>받는사람</th><td>${senderInfo.getNickname() } (${senderInfo.getId() })</td>
			</tr>
			<tr>
				<th>내용</th><td><textarea rows="10" cols="45" name="post"></textarea> </td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="hidden" value="${sender }" name="sender">
					<input type="hidden" value="${receiver }" name="receiver">
					<input type="submit" value="보내기">
					<input type="button" value="뒤로가기" onclick="history.back()"> 
				</td>
			</tr>
		</table>
		</form>
</body>
</html>