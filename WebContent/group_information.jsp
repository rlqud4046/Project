<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
</head>
<body>

	<form action="group_information_update.do?group_no=1">
	
		<table class="table">
			
			<tr>
				<th>대문 사진 변경</th> <th>오픈 카톡</th>
			</tr>
				
			<tr>	
				<td>
					<img alt="" src="${dto.getGroup_front_img() }"	>
					<input type="file" name="front_img">
				</td>
				<td>
					<input type="text" name="chatroom" value="${dto.getGroup_chatroom() }">
				</td>
			</tr>
			
			<tr>
				<th>대표 사진 변경</th> <th>인스타그램</th>
			</tr>
			
			<tr>
				<td>
					<img alt="" src="${dto.getGroup_main_img() }">
					<input type="file" name="file">
				</td>
				<td>
					<input type="text" name="insta" value="${dto.getGroup_insta() }">
				</td>
			</tr>
			
			<tr>
				<th>모임명</th> <th>페이스북</th>
			</tr>
			
			<tr>
				<td>
					<input type="text" name="name" value="${dto.getGroup_name() }">
				</td>
				<td>
					<input type="text" name="facebook" value="${dto.getGroup_facebook() }">
				</td>
			</tr>
			
			<tr>
				<th colspan="2">모임 소개글</th>
			</tr>
			<tr>
				<td colspan="2">
					<textarea rows="7" cols="200" name="intro">${dto.getGroup_intro() }</textarea>
				</td>
			</tr>
			
			
		</table>
		
		<input type="submit" value="저장">
		<input type="button" value="취소">
		
	</form>

</body>
</html>