<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>


<style type="text/css">
header {
	/*    position: relative;
   margin: 0 auto;
   width: 100%; */
	background-color: #fff;
	border-top: 1px solid #fff;
	/* z-index: 30; */
}

header #notice {
	/* position: absolute; */
	/* left: 12px; */
	/*    top: 30px; */
	/* width: 470px; */
	/* height: 15px; */
	/* padding: 0 0 0 18px; */
	font-size: 12px;
	text-align: left;
	letter-spacing: 0;
}

header #notice {
	/* display: block; */
	/* position: relative; */
	/* width: 400px; */
	/* height: 15px; */
	/* list-style: none; */
	
}

.marquee {
	height: 25px;
	width: 420px;
	overflow: hidden;
	position: relative;
}

.marquee li {
	/* display: block; */
	width: 200%;
	/* height: 30px; */
	position: relative;
	overflow: hidden;
	animation: marquee 5s linear infinite;
}

.marquee li {
	/* float: left;
   width: 50%; */
	
}

@
keyframes marquee { 0% {
	top: 0;
}

100%
{
top


















:


















-100%;
}
}
#logo {
	/* position: fixed;
   left: 855px;
   top: 30px; */
	
}

#top_login {
	/*    position: fixed;
   left: 1650px;
   top: 30px; */
	
}

ul {
	list-style: none;
	padding-left: 0px;
}
.popover{
	max-width: 500px;
}
</style>
</head>
<body>
	<!-- 모달창 -->



	<header class="container">
		<div class="row" style="margin-top: 10px;">
			<div class="pull-left" id="notice">
				<span>NOTICE&nbsp;</span>
			</div>
			<div class="pull-left">
				<ul id="marquee2" class="marquee">
					<li><a href="">글 제목이 나오는 공간</a></li>
					<!-- <li><a href="">112321323</a></li> -->
				</ul>
			</div>
			<div class="pull-right" id="top_login">
				<a href="./main.jsp">홈</a>
				<c:if test="${!empty name }">
					<a href="<%=request.getContextPath()%>/logout.do">로그아웃</a>
				</c:if>
				<c:if test="${empty name }">
					<a href="<%=request.getContextPath()%>/login.do">로그인</a>
				</c:if>
				<c:if test="${!empty name }">
					<a href="">${name }님</a>
				</c:if>
				<c:if test="${empty name }">
					<a href="<%=request.getContextPath()%>/join.do">회원가입</a>
				</c:if>


				<a tabindex="0" id="pop" role="button" data-triger="focus"
					data-placement="bottom"> <img src="../images/letter.jpg"
					width="30">
				</a>


				<div id="mypop" class="container hide">
					<div class="row" style="wid">

						<div style="width: 2000px; height: 500px;">
							<ul style="list-style: none; ">
								
									<ul class="nav nav-tabs" role="tablist" id="myTab">
			<li role="presentation" class="active"><a href="#home"
				aria-controls="home" role="tab" data-toggle="tab"> <img alt="홈"
					src="images/home.png" width="30px"></a></li>
			<li role="presentation"><a href="#notice" aria-controls="home"
				role="tab" data-toggle="tab">공지사항</a></li>
			<li role="presentation"><a href="#hi_board"
				aria-controls="profile" role="tab" data-toggle="tab">가입인사</a></li>
			
		</ul>


		<div class="tab-content">
			<div role="tabpanel" class="tab-pane active" id="home">
				ㅎㅎㅎ
			</div>
			<div role="tabpanel" class="tab-pane" id="notice">공지사항 게시판</div>
			<div role="tabpanel" class="tab-pane" id="hi_board">가입인삳게시판</div>
			<div role="tabpanel" class="tab-pane" id="meet_board">정모게시판게시판</div>
			
		</div>

								
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





















				<%-- 
				<button type="button" class="btn btn-primary btn-lg"
					id="openModalBtn">
					<img src="../images/letter.jpg" width="30">
				</button>
				<div id="modalBox" class="modal fade" id="myModal" tabindex="-1"
					role="dialog" aria-labelledby="myModalLabel">
					<div class="modal-dialog" role="document">
						<div class="modal-content">

							<div class="modal-header">


								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">×</span>
								</button>
								<ul class="nav nav-tabs" role="tablist" id="myTab">
									<li role="presentation" class="active"><a href="#reveive"
										aria-controls="settings" role="tab" data-toggle="tab">받은쪽지함</a></li>
									<li role="presentation"><a href="#send"
										aria-controls="settings" role="tab" data-toggle="tab">보낸쪽지함</a></li>

								</ul>

							</div>
							<div class="modal-body">
								<div class="tab-content">
									
									<div role="tabpanel" class="tab-pane active" id="reveive">
										<jsp:include page="../receive_post.jsp"></jsp:include>
									</div>
									<div role="tabpanel" class="tab-pane" id="send">
										<jsp:include page="../send_post.jsp"></jsp:include>
									</div>
								</div>

							</div>
							<div class="modal-footer">
								<!-- <button type="button" class="btn btn-primary">확인</button>
								<button type="button" class="btn btn-default" id="closeModalBtn">취소</button> -->
							</div>
						</div>
					</div>
				</div>
 --%>
				<!-- <script type="text/javascript">
					// 모달 버튼에 이벤트를 건다.
					$('#openModalBtn').on('click', function() {
						$('#modalBox').modal('show');
					});
					// 모달 안의 취소 버튼에 이벤트를 건다.
					$('#closeModalBtn').on('click', function() {
						$('#modalBox').modal('hide');
					});
				</script> -->


				<!-- 							<div class="box">
								<ul class="nav nav-tabs" role="tablist" id="myTab">
									<li role="presentation" class="active"><a href="#home"
										role="tab" data-toggle="tab">받은쪽지함</a></li>
									<li role="presentation"><a href="#notice" role="tab"
										data-toggle="tab">보낸쪽지함</a></li>

								</ul>


								<div class="tab-content">
									<div role="tabpanel" class="tab-pane active" id="home">
										받은쪽지함알맹이</div>
									<div role="tabpanel" class="tab-pane" id="notice">보낸쪽지함알맹이</div>

								</div>


							</div>
 -->
			</div>

		</div>


		<span style="clear: both;"></span>
		<div class="col-md-12" id="logo">
			<a href="../main.jsp"><img src="../images/sist.jpg" width="100%"
				border="0" height="30px"></a>
		</div>
		<br> <br> <br> <br>
		<hr>
		<div align="center">
			<c:set var="g_no" value="null" />
			<%-- <c:if test="${!empty g_no }"> 비어있지 않으면 해당 그룹 메인화면으로 가는 이미지
      <a href="main.do?group_no=<%=%>"><img src="./images/sist.jpg" width="200" border="0"></a>
   </c:if> --%>

			<%-- <c:if test="${empty g_no }"> 비어있으면 메인화면으로 가는 이미지 --%>
			<a href="main.jsp"><img src="./images/sist.jpg" width="200"
				border="0"></a>
			<%-- </c:if> --%>
		</div>
		<hr>
	</header>


</body>
</html>