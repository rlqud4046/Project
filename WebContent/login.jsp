<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="./css/bootstrap-3.3.2.css">

</head>
<body>


	<jsp:include page="include/header.jsp"></jsp:include>
	<section class="container">
		<article>


			<div align="center" style="padding-top: 330px">

				<form class="form-inline">

					<table width="200" height="150">
						<tr>
							<td>
								<div class="form-group">
									<input type="text" class="form-control" placeholder="아이디">
								</div>
							</td>
						</tr>

						<tr>
							<td>
								<div class="form-group">
									<input type="email" class="form-control" placeholder="비밀번호">
								</div>
							</td>
						</tr>

						<tr>
							<td>
								<button type="submit" class="btn btn-default btn-block">로그인</button>
							</td>
						</tr>
					</table>

					<a href="https://www.nate.com/?f=autorefresh">아이디 찾기</a> &nbsp;&nbsp; <a href="https://www.nate.com/?f=autorefresh">비밀번호 찾기</a>&nbsp;&nbsp; <a href="https://www.nate.com/?f=autorefresh">회원가입</a>
				</form>
			</div>




		</article>
	</section>
	<aside><jsp:include page="include/side.jsp"></jsp:include></aside>
	<footer><jsp:include page="include/foot.jsp"></jsp:include></footer>

</body>
</html>
