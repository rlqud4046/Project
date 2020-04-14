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
	<form>
	<table class="table">
		<c:set value="${listCont }" var="cont"/>
		<c:set value="${page_no }" var="page_no"/>
		
		
		<tr>
		
			<th><c:if test="${page_no ==1}">보낸사람</c:if>
				<c:if test="${page_no ==2}">받는사람</c:if></th>
				<td>${cont.getNickname() }(${cont.getId() })</td>
		</tr>
		<tr>
			<th>보낸시각</th><td>${cont.getPost_date() }</td>
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea rows="10" cols="45" style="resize: none;" readonly>${cont.getPost_cont() }</textarea> </td>
		</tr>
		<tr>
			<td colspan="2" align="center"> 
				<input type="hidden" value="${cont.getReceiver() }" name="receiver">
				<input type="hidden" value="${cont.getSender() }" name="sender">
				<input type="hidden" value="${cont.getPost_no() }" name="post_no">
				<c:if test="${page_no==1 }">
					<input type="submit" value="답장" onclick="javascript: form.action='post_reply.do';">
					<input type="submit" value="삭제" onclick="javascript: form.action='post_rec_del.do';">
				</c:if>
				<c:if test="${page_no==2 }">
					<input type="submit" value="삭제" onclick="javascript: form.action='post_send_del.do';">
				</c:if>
				<input type="button" value="뒤로가기" onclick="history.back()"> 
			</td>
		</tr>
		
		
	
	</table>
	</form>
</body>
</html>