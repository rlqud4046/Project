<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<!-- 팝오버의 활동정보 / 쪽지 보내기에 ~.do 넣어줘야함 -->



<style type="text/css">
#con_box {
	border: 0.5px;
	border-style: solid;
	border-color: rgba(139, 139, 139, 0.3);
}

.line {
	border-bottom-style: dashed;
	border-bottom-width: 1px;
	opacity: 0.3;
}

.h-10 {
	clear: both;
	height: 10px;
	margin: 0;
	font-size: 0;
	overflow: hidden;
}

.h-70 {
	clear: both;
	height: 70px;
	margin: 0;
	font-size: 0;
	overflow: hidden;
}

.vl {
	border-right: 1px solid gray;
}

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
	margin-top: 8px;
	margin-bottom: 2px;
}

.date {
	float: right;
}

.writer {
	float: left;
	margin-top: 5px;
}

.file {
	float: right;
	margin-top: 5px;
	margin-right: 10px;
}

.cont {
	width: 744px;
	padding-left: 43px;
	padding-right: 43px;
	margin-right: 0px;
}

.buts-box {
	padding-top: 8px;
	clear: both;
}

.buts {
	float: right;
	display: inline;
}

.reply_box {
	padding: 11px 26px 16px 24px;
}

#ta {
	resize: none;
	border: 0;
}

textarea:focus {
	outline: none;
}

.cmlist {
	margin: 0;
	padding: 0;
}

ul {
	display: block;
	list-style-type: disc;
	margin-block-start: 1em;
	margin-block-end: 1em;
	margin-inline-start: 0px;
	margin-inline-end: 0px;
	padding-inline-start: 40px;
}

.cmlist li {
	list-style: none;
	margin: 0;
	padding: 0 0 7px;
	height: 1%;
}

.cmlist .board-box-line-dashed {
	height: 1px;
	padding: 0;
	overflow: hidden;
	font: 0/0 arial;
	border-bottom-width: 1px;
	border-bottom-style: dotted;
}

.h {
	height: 20px;
	margin: 0;
}

.pers_nick_area {
	text-align: left;
	width: auto;
	float: left;
}

.p_nick {
	font-weight: bold;
	line-height: 16px;
}

.date {
	float: left;
	font-size: 11px;
	font-family: '돋움', dotum, Helvetica, sans-serif;
	margin: 2px 0 0 1px;
	padding-top: 2px;
	margin-left: 7px;
}

.dsc_comm {
	float: left;
	margin: 3px 0 0 7px;
	padding-left: 10px;
	font-size: 11px;
	font-family: '돋움', Dotum;
	letter-spacing: -1px;
	background: url(https://cafe.pstatic.net/cafe4/bu_arr.png) no-repeat 0 0;
}

div {
	display: block;
}

a {
	text-decoration: none;
	color: #000000;
}

.filter-30 {
	margin-bottom: 5px;
}

.cmlist li {
	margin-bottom: 5px;
}
</style>


<script type="text/javascript">
	var httpRequest = null;

	// httpRequest 객체 생성
	function getXMLHttpRequest() {
		var httpRequest = null;

		if (window.ActiveXObject) {
			try {
				httpRequest = new ActiveXObject("Msxml2.XMLHTTP");
			} catch (e) {
				try {
					httpRequest = new ActiveXObject("Microsoft.XMLHTTP");
				} catch (e2) {
					httpRequest = null;
				}
			}
		} else if (window.XMLHttpRequest) {
			httpRequest = new window.XMLHttpRequest();
		}
		return httpRequest;
	}

	// 댓글 등록
	function writeCmt() {
		var form = document.getElementById("writeCommentForm");

		var board = form.mgn_no.value;
		var id = form.comment_writer.value;
		var content = form.comment_cont.value;

		if (!content) {
			alert("내용을 입력하세요.");
			return false;
		} else {
			var param = "mgn_no=" + board + "&comment_writer=" + id
					+ "&comment_cont=" + content;

			httpRequest = getXMLHttpRequest();
			httpRequest.onreadystatechange = checkFunc;
			httpRequest.open("POST", "comment_write.co", true);
			httpRequest.setRequestHeader('Content-Type',
					'application/x-www-form-urlencoded;charset=EUC-KR');
			httpRequest.send(param);
		}
	}

	// 대댓글 등록

	/* function replyCmt() {
		var form = document.getElementById("replyCommentForm");

		var board = form.mgn_no.value;
		var id = form.reply_writer.value;
		var content = form.reply_cont.value;
		var comment_no = form.comment_no.value

		if (!content) {
			alert("내용을 입력하세요.");
			return false;
		} else {
			var param = "mgn_no=" + board + "&reply_writer=" + id
					+ "&reply_cont=" + content + "&comment_no=" + comment_no;

			httpRequest = getXMLHttpRequest();
			httpRequest.onreadystatechange = checkFunc;
			httpRequest.open("POST", "comment_reply_ok.co", true);
			httpRequest.setRequestHeader('Content-Type',
					'application/x-www-form-urlencoded;charset=EUC-KR');
			httpRequest.send(param);
		}
	} */

	// 좋아요 
	function like() {
		var form = document.getElementById("likeForm");

		var board = form.mgn_no.value;
		var id = form.mem_no.value;
		var like = form.like.value;

		if (id != 2) {
			alert("회원 번호 틀림.");
			return false;
		} else {
			var param = "mgn_no=" + board + "&mem_no=" + id + "&like=" + like;
			alert("회원 번호 맞음")
			httpRequest = getXMLHttpRequest();
			httpRequest.onreadystatechange = checkFunc;
			httpRequest.open("POST", "like.do", true);
			httpRequest.setRequestHeader('Content-Type',
					'application/x-www-form-urlencoded;charset=EUC-KR');
			httpRequest.send(param);
		}
	}

	function checkFunc() {
		if (httpRequest.readyState == 4) {
			// 결과값을 가져온다.
			var resultText = httpRequest.responseText;
			if (resultText == 1) {
				document.location.reload(); // 상세보기 창 새로고침
			}
		}
	}
</script>



</head>
<body>
	<jsp:include page="include/header.jsp"></jsp:include>

	<section class="container">
		<article>

			<div class="container" id="con_box">



				<c:set var="dto" value="${cont }" />
				<c:set var="cdto" value="${comment }" />
				<c:set var="count" value="${count }" />
				<c:set var="lcheck" value="${lCheck }" />

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
					<div class="line"></div>

					<div class="writer">
						<table role="presentation" cellspacing="0" cellpadding="0" border="0">
							<tr>
								<th></th>
								<td>
									<a tabindex="0" id="writer_pop" role="button" data-triger="focus" data-placement="bottom">${dto.getBoard_writer() }</a>
								</td>
							</tr>
						</table>
					</div>

					<div id="w_pop" class="container hide">
						<div class="row">

							<div class="col-sm-12">
								<ul style="list-style: none;">

									<li><span><font size="2"><a href="#">활동 정보</a></font></span></li>
									<li><span><font size="2"><a href="#">쪽지 보내기</a></font></span></li>
								</ul>
							</div>
						</div>
					</div>

					<script>
						$('#writer_pop').popover({
							html : true,
							content : $('#w_pop').html()
						});
					</script>



					<div class="file">
						<div class="row">
							<c:set value="${aList }" var="alist" />
							<c:if test="${!empty alist }">
								<a tabindex="0" id="pop" role="button" data-triger="focus" data-placement="bottom">첨부파일<span>(${fn:length(alist) })</span></a>

							</c:if>


						</div>

					</div>

					<div id="mypop" class="container hide">
						<div class="row">

							<div class="col-sm-12">
								<ul style="list-style: none;">
									<c:forEach items="${alist }" var="attach">
										<li><span><font size="2"><a href="fileDownload.do?file_name=${ attach}">${ attach}</a></font></span></li>
									</c:forEach>
								</ul>
							</div>

						</div>
					</div>
					<script>
						$('#pop').popover({
							html : true,
							content : $('#mypop').html()
						});
					</script>


					<div class="h-10">
						<br>
					</div>



					<div class="cont">
						<textarea id="ta" style="width: 900px; height: 70px; overflow-y: hidden;" readonly>${dto.getBoard_cont() }</textarea>
						<textarea id="xt" style="width: 300px; height: 1px; overflow-y: hidden; position: absolute; top: -9px" disabled></textarea>

					</div>

					<div class="h-70">
						<br>
					</div>
					<div>
						<form id="likeForm">
							<input type="hidden" name="mgn_no" value="${dto.getMgn_no() }"> <input type="hidden" name="mem_no" value="2<%-- ${sessionScope.sessionID } --%>">
							<input type="hidden" name="like" value="${lcheck }">
							
							<table role="presentation" cellspacing="0" cellpadding="0" border="0">
								<tr style="vertical-align: top;">
									<td class="vl">
										<span>댓글 </span><span>${count}&nbsp; </span>
									</td>
									<td class="vl">
										<span>&nbsp;조회수</span> <span>${dto.getBoard_hit() }&nbsp;</span>
									</td>

									<td>
										<a tabindex="0" id="like_pop" role="button" data-triger="focus" data-placement="bottom">
										<span>&nbsp;좋아요</span> <span>${dto.getBoard_like() }</span></a>
									</td>
									<td>
										
										<a href="#" onclick="like()"><img alt="하트사진" src=""></a>
										
										
										<%-- </c:if> --%>


									</td>
								</tr>
							</table>
						</form>
					</div>



					<div id="l_pop" class="container hide">
						<div class="row">

							<div class="col-sm-12">
								<ul style="list-style: none;">

									<li><span><font size="2">좋아요 누른 사람들</font></span></li>
								</ul>
							</div>
						</div>
					</div>

					<script>
						$('#like_pop').popover({
							html : true,
							content : $('#l_pop').html()
						});
					</script>


				</c:if>

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


				<div class="reply_box" style="display: block; background-color: rgb(250, 250, 250);">
					<ul class="cmlist" id="cmt_list">
						<c:set var="comment" value="${comment}" />
						<c:set var="count" value="${count}" />

						<c:if test="${!empty comment }">
							<c:set value="${rdto }" var="reply" />
							<c:forEach items="${comment }" var="cdto">
								<li>

									<div class="comm_cont">
										<c:if test="${ cdto.getComment_parent() != 0}">
											<div style="float: left;">
												<img alt="" src="https://cafe.pstatic.net/cafe4/bu_arr.png" width="13px">&ensp;
											</div>
										</c:if>
										<div class="h">
											<div class="pers_nick_area">
												<table role="presentation" cellspacing="0">
													<tbody>
														<tr>
															<td class="nick">
																<a tabindex="0" id="reply_pop" role="button" data-triger="focus" data-placement="bottom">${cdto.getComment_writer() }</a>
															</td>
														</tr>
													</tbody>
												</table>

											</div>

											<div id="r_pop" class="container hide">
												<div class="row">

													<div class="col-sm-12">
														<ul style="list-style: none;">

															<li><span><font size="2"><a href="#">활동 정보</a></font></span></li>
															<li><span><font size="2"><a href="#">쪽지 보내기</a></font></span></li>
														</ul>
													</div>
												</div>
											</div>

											<script>
												$('#reply_pop').popover(
														{
															html : true,
															content : $(
																	'#r_pop')
																	.html()
														});
											</script>


											<span class="date">${cdto.getComment_date().substring(0,16) }</span> <a class="dsc_comm" onclick="test(this.parentNode.nextElementSibling.nextElementSibling.nextElementSibling)" href="javascript:void(0)">답글 작성</a>
											<script>
												function test(param) {
													/* var test1 = document
															.getElementById('test1') */
													param.style.display = (param.style.display == 'none') ? 'block'
															: 'none';
												}
											</script>



										</div>
										<br>
										<p class="comm">
											<span class="comm_body">${cdto.getComment_cont() }</span>
										</p>

										<div id="test1" style="display: none">

											<div>
												<form id="replyCommentForm">
													<input type="hidden" name="mgn_no" value="${dto.getMgn_no() }"> <input type="hidden" name="reply_writer" value="SessinID">
													<c:if test="${ cdto.getComment_parent() == 0}">
														<input type="hidden" name="comment_no" value="${cdto.getComment_no() }">
													</c:if>

													<c:if test="${ cdto.getComment_parent() != 0}">
														<input type="hidden" name="comment_no" value="${cdto.getComment_parent() }">
													</c:if>
													<table>
														<tbody>
															<tr>
																<td>
																	<div>

																		<textarea name="reply_cont" rows="4" cols="140" class="textarea" maxlength="6000" style="overflow: hidden; line-height: 14px; height: 80px; resize: none;" title="댓글입력"></textarea>
																		&nbsp;
																	</div>
																</td>
																<td>
																	<div>
																		<a href="#" class="btn btn-default" onclick="replyCmt(this.parentNode.parentNode.parentNode.parentNode.parentNode.parentNode)">등록</a>
																		<script>
																			function replyCmt(
																					param) {

																				var board = param.mgn_no.value;
																				var id = param.reply_writer.value;
																				var content = param.reply_cont.value;
																				var comment_no = param.comment_no.value;

																				if (!content) {
																					alert("내용을 입력하세요.");
																					return false;
																				} else {
																					var param = "mgn_no="
																							+ board
																							+ "&reply_writer="
																							+ id
																							+ "&reply_cont="
																							+ content
																							+ "&comment_no="
																							+ comment_no;

																					httpRequest = getXMLHttpRequest();
																					httpRequest.onreadystatechange = checkFunc;
																					httpRequest
																							.open(
																									"POST",
																									"comment_reply_ok.co",
																									true);
																					httpRequest
																							.setRequestHeader(
																									'Content-Type',
																									'application/x-www-form-urlencoded;charset=EUC-KR');
																					httpRequest
																							.send(param);
																				}
																			}
																		</script>
																	</div>
																</td>
														</tbody>

													</table>
												</form>
											</div>
										</div>


									</div>

								</li>
								<li class="filter-30 board-box-line-dashed"></li>

							</c:forEach>
						</c:if>
					</ul>
					<form id="writeCommentForm">
						<input type="hidden" name="mgn_no" value="${dto.getMgn_no() }"> <input type="hidden" name="comment_writer" value="작성자<%-- ${sessionScope.sessionID } --%>">
						<table>
							<tbody>
								<tr>
									<td>
										<div>
											<textarea name="comment_cont" rows="4" cols="140" class="textarea" maxlength="6000" style="overflow: hidden; line-height: 14px; height: 80px; resize: none;" title="댓글입력"></textarea>
											&nbsp;
										</div>
									</td>
									<td>
										<div>
											<a href="#" class="btn btn-default" onclick="writeCmt()">등록</a>
										</div>

									</td>
							</tbody>

						</table>
					</form>




				</div>

				<div class="h-10">
					<br>
				</div>
			</div>
			<div class="buts-box">
				<div class="buts">

					<table>
						<tr>
							<td colspan="2" align="center">
								<input type="button" value="글수정" onclick="location.href='board_edit.do?no=${dto.getMgn_no()}'"> 
								<input type="button" value="글삭제" onclick="location.href='board_delete.do?no=${dto.getMgn_no()}'">
								<input type="button" value="목록" onclick="location.href='board_list.do?board_category=${dto.getBoard_category() }&group_no=${dto.getGroup_no() }'">
								
							</td>
						</tr>
					</table>
				</div>

			</div>
		</article>
	</section>

	<aside><jsp:include page="include/side.jsp"></jsp:include></aside>
	<footer><jsp:include page="include/foot.jsp"></jsp:include></footer>




	<script>
		function xSize(e) {
			var t;

			e.onfocus = function() {
				t = setInterval(function() {
					e.style.height = '1px';
					e.style.height = (e.scrollHeight + 12) + 'px';
				}, 100);
			}

			e.onblur = function() {
				clearInterval(t);
			}
		}
		xSize(document.getElementById('ta'));
	</script>






</body>
</html>