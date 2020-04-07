<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

<style type="text/css">
table {
	border-collapse: separate;
	border-spacing: 2px;
}

td {
	display: table-cell;
}

.title {
	float: left;
}

.t_box {
	clear: both;
	overflow: hidden;
}

.date {
	float: right;
}

.writer {
	float: left;
}
</style>



</head>
<body>

	<div class="container" style="border: 1px; color: gray;">



		<c:set var="dto" value="${cont }" />
		<c:set var="cdto" value="${comment }" />
		<c:set var="count" value="${count }" />

		<c:if test="${!empty dto }">
			<div class="t_box">
				<div class="title">
					<table role="presentation" cellspacing="0" cellpadding="0" border="0">
						<tr valign="top">
							<td>
								<span>${dto.getBoard_title() }</span>
							</td>
						</tr>
					</table>
				</div>

				<div class="date">
					<table role="presentation" cellspacing="0" cellpadding="0" border="0">
						<tr>
							<th></th>
							<td>
								<span>${dto.getBoard_date() }</span>
							</td>
						</tr>
					</table>
				</div>
			</div>


			<div class="writer">
				<table role="presentation" cellspacing="0" cellpadding="0" border="0">
					<tr>
						<th></th>
						<td>${dto.getBoard_writer() }</td>
					</tr>
				</table>
			</div>

			<div class="file">
				<a href="">${dto.getBoard_file() }</a>
			</div>





			<div class="cont" style="width: 744px; padding-left: 43px; padding-right: 43px; margin-right: 0px;">
				<table>
					<tr>
						<th></th>
						<td>
							<textarea rows="7" cols="40" readonly style="resize: none;">${dto.getBoard_cont() }</textarea>
						</td>
					</tr>
				</table>
			</div>

			<div style="height: 35px;"></div>
			<div>
				<table role="presentation" cellspacing="0" cellpadding="0" border="0">
					<tr style="vertical-align: top;">
						<td>
							<span>댓글 </span><span>${count}  </span>
						</td>
						<td>
							<span>조회수</span> <span>${dto.getBoard_hit() }</span>
						</td>
						
						<td><span>좋아요</span> <span>${dto.getBoard_like() }</span></td>
					</tr>
				</table>
			</div>

			<div>
				<table>
					<c:if test="${empty dto}">
						<tr>
							<td colspan="2" align="center">
								<h3>검색된 레코드가 없습니다</h3>
							</td>
						</tr>
					</c:if>
				</table>
			</div>
		</c:if>

		<div>
			<table>
				<tr>
					<td colspan="2" align="center">
						<input type="button" value="글수정" onclick="location.href='board_edit.do?no=${dto.getBoard_no()}&page=${page }'"> <input type="button" value="글삭제" onclick="location.href='board_del.do?no=${dto.getBoard_no()}&page=${page }'">

						<c:if test="${search!=1 }">
							<input type="button" value="목록" onclick="location.href='board_list.do?page=${page}'">
						</c:if>
						<c:if test="${search==1 }">
							<input type="button" value="목록" onclick="location.href='board_search.do?page=${page}&find_field=${find_field }&find_name=${find_name }'">
						</c:if>
					</td>
				</tr>
			</table>
		</div>


	</div>




</body>
</html>