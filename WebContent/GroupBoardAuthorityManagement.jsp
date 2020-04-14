<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

</head>
<body>

	<form action="group_authority_update.do?group_no=1" method="post">
	
	<table class="table table-bordered table-hover">
		<c:set value="${DTO }" var="dto"/>
		<tr class="form-inline">
			<th>게시판 이름</th><th>읽기권한</th><th>쓰기권한</th>
		</tr>
		
		<tr class="form-inline">
			<th>공지사항</th>
			<td>
				<select class="form-control" name="r_notice">
					<c:if test="${dto.getR_notice() == 1 }">
						<option value="1">준회원</option>
					</c:if>
					<c:if test="${dto.getR_notice() == 2 }">
						<option value="2">정회원</option>
					</c:if>
					<c:if test="${dto.getR_notice() == 3 }">
						<option value="3">우수회원</option>
					</c:if>
					<c:if test="${dto.getR_notice() == 4 }">
						<option value="4">운영진</option>
					</c:if>
					<c:if test="${dto.getR_notice() == 5 }">
						<option value="5">모임장</option>
					</c:if>
					<option value="1">준회원</option>
					<option value="2">정회원</option>
					<option value="3">우수회원</option>
					<option value="4">운영진</option>
					<option value="5">모임장</option>
				</select>
			</td>
			<td>
				<select class="form-control" name="w_notice">
					<c:if test="${dto.getW_notice() == 1 }">
						<option value="1">준회원</option>
					</c:if>
					<c:if test="${dto.getW_notice() == 2 }">
						<option value="2">정회원</option>
					</c:if>
					<c:if test="${dto.getW_notice() == 3 }">
						<option value="3">우수회원</option>
					</c:if>
					<c:if test="${dto.getW_notice() == 4 }">
						<option value="4">운영진</option>
					</c:if>
					<c:if test="${dto.getW_notice() == 5 }">
						<option value="5">모임장</option>
					</c:if>
					<option value="1">준회원</option>
					<option value="2">정회원</option>
					<option value="3">우수회원</option>
					<option value="4">운영진</option>
					<option value="5">모임장</option>
				</select>
			</td>
		</tr>
		
		<tr class="form-inline">
			<th>가입인사</th>
			<td>
				<select class="form-control" name="r_hi">
					<c:if test="${dto.getR_hi() == 1 }">
						<option value="1">준회원</option>
					</c:if>
					<c:if test="${dto.getR_hi() == 2 }">
						<option value="2">정회원</option>
					</c:if>
					<c:if test="${dto.getR_hi() == 3 }">
						<option value="3">우수회원</option>
					</c:if>
					<c:if test="${dto.getR_hi() == 4 }">
						<option value="4">운영진</option>
					</c:if>
					<c:if test="${dto.getR_hi() == 5 }">
						<option value="5">모임장</option>
					</c:if>
					<option value="1">준회원</option>
					<option value="2">정회원</option>
					<option value="3">우수회원</option>
					<option value="4">운영진</option>
					<option value="5">모임장</option>
				</select>
			</td>
			<td>
				<select class="form-control" name="w_hi">
					<c:if test="${dto.getW_hi() == 1 }">
						<option value="1">준회원</option>
					</c:if>
					<c:if test="${dto.getW_hi() == 2 }">
						<option value="2">정회원</option>
					</c:if>
					<c:if test="${dto.getW_hi() == 3 }">
						<option value="3">우수회원</option>
					</c:if>
					<c:if test="${dto.getW_hi() == 4 }">
						<option value="4">운영진</option>
					</c:if>
					<c:if test="${dto.getW_hi() == 5 }">
						<option value="5">모임장</option>
					</c:if>
					<option value="1">준회원</option>
					<option value="2">정회원</option>
					<option value="3">우수회원</option>
					<option value="4">운영진</option>
					<option value="5">모임장</option>
				</select>
			</td>
		</tr>
		
		<tr class="form-inline">
			<th>정모게시판</th>
			<td>
				<select class="form-control" name="r_meet">
					<c:if test="${dto.getR_meet() == 1 }">
						<option value="1">준회원</option>
					</c:if>
					<c:if test="${dto.getR_meet() == 2 }">
						<option value="2">정회원</option>
					</c:if>
					<c:if test="${dto.getR_meet() == 3 }">
						<option value="3">우수회원</option>
					</c:if>
					<c:if test="${dto.getR_meet() == 4 }">
						<option value="4">운영진</option>
					</c:if>
					<c:if test="${dto.getR_meet() == 5 }">
						<option value="5">모임장</option>
					</c:if>
					<option value="1">준회원</option>
					<option value="2">정회원</option>
					<option value="3">우수회원</option>
					<option value="4">운영진</option>
					<option value="5">모임장</option>
				</select>
			</td>
			<td>
				<select class="form-control" name="w_meet">
					<c:if test="${dto.getW_meet() == 1 }">
						<option value="1">준회원</option>
					</c:if>
					<c:if test="${dto.getW_meet() == 2 }">
						<option value="2">정회원</option>
					</c:if>
					<c:if test="${dto.getW_meet() == 3 }">
						<option value="3">우수회원</option>
					</c:if>
					<c:if test="${dto.getW_meet() == 4 }">
						<option value="4">운영진</option>
					</c:if>
					<c:if test="${dto.getW_meet() == 5 }">
						<option value="5">모임장</option>
					</c:if>
					<option value="1">준회원</option>
					<option value="2">정회원</option>
					<option value="3">우수회원</option>
					<option value="4">운영진</option>
					<option value="5">모임장</option>
				</select>
			</td>
		</tr>
		
		<tr class="form-inline">
			<th>자유게시판</th>
			<td>
				<select class="form-control" name="r_free">
					<c:if test="${dto.getR_free() == 1 }">
						<option value="1">준회원</option>
					</c:if>
					<c:if test="${dto.getR_free() == 2 }">
						<option value="2">정회원</option>
					</c:if>
					<c:if test="${dto.getR_free() == 3 }">
						<option value="3">우수회원</option>
					</c:if>
					<c:if test="${dto.getR_free() == 4 }">
						<option value="4">운영진</option>
					</c:if>
					<c:if test="${dto.getR_free() == 5 }">
						<option value="5">모임장</option>
					</c:if>
					<option value="1">준회원</option>
					<option value="2">정회원</option>
					<option value="3">우수회원</option>
					<option value="4">운영진</option>
					<option value="5">모임장</option>
				</select>
			</td>
			<td>
				<select class="form-control" name="w_free">
					<c:if test="${dto.getW_free() == 1 }">
						<option value="1">준회원</option>
					</c:if>
					<c:if test="${dto.getW_free() == 2 }">
						<option value="2">정회원</option>
					</c:if>
					<c:if test="${dto.getW_free() == 3 }">
						<option value="3">우수회원</option>
					</c:if>
					<c:if test="${dto.getW_free() == 4 }">
						<option value="4">운영진</option>
					</c:if>
					<c:if test="${dto.getW_free() == 5 }">
						<option value="5">모임장</option>
					</c:if>
					<option value="1">준회원</option>
					<option value="2">정회원</option>
					<option value="3">우수회원</option>
					<option value="4">운영진</option>
					<option value="5">모임장</option>
				</select>
			</td>
		</tr>
		
		<tr class="form-inline">
			<th>사진첩</th>
			<td>
				<select class="form-control" name="r_photo">
					<c:if test="${dto.getR_photo() == 1 }">
						<option value="1">준회원</option>
					</c:if>
					<c:if test="${dto.getR_photo() == 2 }">
						<option value="2">정회원</option>
					</c:if>
					<c:if test="${dto.getR_photo() == 3 }">
						<option value="3">우수회원</option>
					</c:if>
					<c:if test="${dto.getR_photo() == 4 }">
						<option value="4">운영진</option>
					</c:if>
					<c:if test="${dto.getR_photo() == 5 }">
						<option value="5">모임장</option>
					</c:if>
					<option value="1">준회원</option>
					<option value="2">정회원</option>
					<option value="3">우수회원</option>
					<option value="4">운영진</option>
					<option value="5">모임장</option>
				</select>
			</td>
			<td>
				<select class="form-control" name="w_photo">
					<c:if test="${dto.getW_photo() == 1 }">
						<option value="1">준회원</option>
					</c:if>
					<c:if test="${dto.getW_photo() == 2 }">
						<option value="2">정회원</option>
					</c:if>
					<c:if test="${dto.getW_photo() == 3 }">
						<option value="3">우수회원</option>
					</c:if>
					<c:if test="${dto.getW_photo() == 4 }">
						<option value="4">운영진</option>
					</c:if>
					<c:if test="${dto.getW_photo() == 5 }">
						<option value="5">모임장</option>
					</c:if>
					<option value="1">준회원</option>
					<option value="2">정회원</option>
					<option value="3">우수회원</option>
					<option value="4">운영진</option>
					<option value="5">모임장</option>
				</select>
			</td>
		</tr>
		
	</table>
	
	<input type="submit" value="저장">
	<input type="button" value="취소" onclick="location.href='GroupManagement.do'">

</form>
</body>
</html>