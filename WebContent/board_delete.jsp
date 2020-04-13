<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</head>
<body>

	<div>
		<form action="board_delete_ok.do" method="post">
			<input type="hidden" name="no" value="${no }">
			<table>
				<tr>
					<th colspan="3">비밀번호를 입력하세요.</th>
				</tr>
				<tr>
					<td>
						<input type="password" name="pwd">
					</td>
					<td>
						<input type="submit" value="확인">
					</td>
					<td>
						
						<input type="reset" value="취소">
					</td>
				
				</tr>


			</table>
		</form>
	</div>


</body>
</html>