<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%-- 모임페이지 home화면  --%>
	<div align="center">

		<hr width="70%" color="darkgray" />
		<h3>사진첩</h3>
		<c:set value="${boardPhoto }" var="photo" />
		<c:set value="${boardPhoto1 }" var="photo1" />
		<table>
			<tr>
				<c:forEach begin="0" step="1" end="4" items="${photo }" var="photo">
					<td><a href="board_cont.do?no=${photo.getMgn_no() }"><img alt="사진" src="${photo.getBoard_photo() }"
						width="200px"></a></td>
				</c:forEach>
			</tr>
			<tr>
				<c:forEach begin="0" step="1" end="4" items="${photo1 }"
					var="photo1">
					<td align="center">${photo1.getBoard_title() }</td>
				</c:forEach>
			</tr>
		</table>

		<hr width="70%" color="darkgray" />
		<c:set value="${boardList }" var="total"></c:set>
		<c:set value="${boardNotice }" var="notice"></c:set>
		<c:set value="${boardHello }" var="hello"></c:set>
		<c:set value="${boardFree }" var="free"></c:set>
		<c:set value="${boardMeet }" var="meet"></c:set>
		<table border=0 cellspacing=0>
			<tr>
				<td width="300px" align="center"><h3>공지사항</h3></td>
				<td width="300px" align="center"><h3>가입인사</h3></td>
				<td width="300px" align="center"><h3>전체게시판새글</h3></td>
			</tr>

			<tr>
				<td width="300px" height="150px" align="center"><c:forEach
						items="${notice }" var="dto" begin="0" step="1" end="4">
									<a href="board_cont.do?no=${dto.getMgn_no() }">${dto.getBoard_title() }</a><br />
						<div style="border-bottom: 1px solid lightgray; width: 200px;"></div>
					</c:forEach></td>
				<td width="300px" height="150px" align="center"><c:forEach
						items="${hello }" var="dto" begin="0" step="1" end="4">
									<a href="board_cont.do?no=${dto.getMgn_no() }">${dto.getBoard_title() }</a><br />
						<div style="border-bottom: 1px solid lightgray; width: 200px;"></div>
					</c:forEach></td>
				<td width="300px" height="150px" align="center"><c:forEach
						items="${total }" var="dto" begin="0" step="1" end="4">
									<a href="board_cont.do?no=${dto.getMgn_no() }">${dto.getBoard_title() }</a><br />
						<div style="border-bottom: 1px solid lightgray; width: 200px;"></div>
					</c:forEach></td>
			</tr>
		</table>
		<br />
		<table border=0 cellspacing=0>
			<tr>
				<td width="300px" align="center"><h3>자유게시판</h3></td>
				<td width="300px" align="center"><h3>정모게시판</h3></td>
			</tr>
			<tr>
				<td width="300px" height="150px" align="center"><c:forEach
						items="${free }" var="dto" begin="0" step="1" end="4">
						<a href="board_cont.do?no=${dto.getMgn_no() }">${dto.getBoard_title() }</a>
						<br />
						<div style="border-bottom: 1px solid lightgray; width: 200px;"></div>
					</c:forEach></td>
				<td width="300px" height="150px" align="center"><c:forEach
						items="${meet }" var="dto" begin="0" step="1" end="4">
						<a href="board_cont.do?no=${dto.getMgn_no() }">${dto.getBoard_title() }</a>
						<br />
						<div style="border-bottom: 1px solid lightgray; width: 200px;"></div>
					</c:forEach></td>
			</tr>
		</table>
		<hr width="70%" color="lightgray" />

	</div>
</body>
</html>