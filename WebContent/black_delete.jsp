<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
table {
	border: 1px solid #000;
	border-collapse: collapse; /* cellspacing 0 */
	width: 350px;
}

th, td {
	border: 1px solid #000;
	border-collapse: collapse;
	text-align: center;
}
</style>
</head>
<body>
	<div align="center">
		<hr width="50%" color="skyblue">
		<h3></h3>
		<hr width="50%" color="skyblue">
		<form action="<%=request.getContextPath() %>/black_delete_ok.do"
			method="post">
			<input type="hidden" name="no" value="${no }">
			<input type="hidden" name="no" value="${no }">
			<table>
				<tr>
					<th>삭제할 비밀번호</th>
					<td><input type="password" name="pwd"></td>
				</tr>
				<tr>
					<td colspan="2">
					<input type="submit" value="글삭제">&nbsp;&nbsp;
					<input type="reset" value="취소">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>