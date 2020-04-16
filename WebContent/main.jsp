<%@page import="model.GroupDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<!-- <link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous"> -->
	
	<link rel="stylesheet" href="css/bootstrap-4-3-1.css">

<style>
.carousel-inner>.carousel-item>img {
	/*  width: 640px; height: 720px;  */
	display: block;
	max-width: 100%;
	max-height: 100%;
}

.container0{
    width: 100%;
    margin: 10px auto;
    display: flex;
}

.container1{
    flex:1;
    width:33%;
    box-sizing: border-box;
}

.container2{
    flex:1;
    margin: 0px 5%;
    width:33%;
    box-sizing: border-box;
}

.container3{
    flex:1;
    width:33%;
    box-sizing: border-box;
}
</style>
</head>
<body>
	<!-- <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script> 
	 <script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		crossorigin="anonymous"></script> 
 	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script> 
		 -->
		
	<script type="text/javascript">
		$('.carousel').carousel({
			interval : 1000
		});
	</script>
	
	<%
      GroupDAO dao = GroupDAO.getInstance();
      String[][] rand= dao.random();
      
      String[][] new_g =dao.newgroup();
      
      String[][] big = dao.big();
   %>
   
   

	<br/><br/>
	<div align="center">
	<a href="group_insert.do"><img src="images\exception-index.gif" alt="모임개설이미지"></a>
	</div>
	
	<br/>
	<div class="container0">
		<div class="container1">
			<c:set value="${sessionScope.mem_no }" var="mem_no"/>
			<div id="random1" class="carousel slide" data-ride="carousel">

				<!-- Wrapper for slides -->
				<div class="carousel-inner">
					<!-- 로그인 안했을 때 랜덤으로 뜨는 모임↓ -->
					<c:if test="${empty mem_no}">
               <div id="left1" class="carousel-item active">
                  <img class="w-100 p-3" src="<%=rand[0][1]%>" height="400px"
                     alt="first" onclick="location.href='main.do?group_no=<%=rand[0][0]%>'">
               </div>
               <div class="carousel-item">
                  <img class="w-100 p-3" src="<%=rand[1][1]%>" height="400px"
                     alt="second" onclick="location.href='main.do?group_no=<%=rand[1][0]%>'">
               </div>
               <div class="carousel-item">
                  <img class="w-100 p-3" src="<%=rand[2][1]%>" height="400px"
                     alt="third" onclick="location.href='main.do?group_no=<%=rand[2][0]%>'">
               </div>
               </c:if>
					
					
					<!-- 로그인했을때: 관심사를 알 때 소개할 모임 ↓ -->
					 <c:if test="${!empty mem_no}">
               <% String interests = dao.MyInterests((int)session.getAttribute("mem_no"));
                  String str =interests.trim().substring(1,interests.trim().length()-1);
                  
                  String[][] rec = dao.rec_interest(str);
                  
               %>
               <div class="carousel-item active">
                  <img class="w-100 p-3" src="<%=rec[0][1]%>" height="400px"
                     alt="first" onclick="location.href='main.do?group_no=<%=rec[0][0]%>'">
               </div>
               <div class="carousel-item">
                  <img class="w-100 p-3" src="<%=rec[1][1]%>" height="400px"
                     alt="second" onclick="location.href='main.do?group_no=<%=rec[1][0]%>'">
               </div>
               <div class="carousel-item">
                  <img class="w-100 p-3" src="<%=rec[2][1]%>" height="400px"
                     alt="third" onclick="location.href='main.do?group_no=<%=rec[2][0]%>'">
               </div>
               </c:if>
            </div>
				

				<!-- Indicators -->
				<ol class="carousel-indicators">
					<li data-target="#random1" data-slide-to="0" class="active"></li>
					<li data-target="#random1" data-slide-to="1"></li>
					<li data-target="#random1" data-slide-to="2"></li>
				</ol>

				<!-- 화살표 버튼 -->
				<a class="left carousel-control" href="#random1" role="button"
					data-slide="prev"> <span
					class="glyphicon glyphicon-chevron-left" aria-hidden="false"></span>
					<span class="sr-only">Previous</span>
				</a> <a class="right carousel-control" href="#random1" role="button"
					data-slide="next"> <span
					class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
					<span class="sr-only">Next</span>
				</a>
		
				
			</div>
			
		</div>

		<!-- 인기있는 소모임 띄우기 -->
		<div class="container2">
			<div id="random2" class="carousel slide" data-ride="carousel">

				<!-- Wrapper for slides -->
				<div class="carousel-inner">
               <div class="carousel-item active">
                  <img class="w-100 p-3" src="<%=big[0][1]%>" height="400px"
                     alt="first" onclick="location.href='main.do?group_no=<%=big[0][0]%>'">
               </div>
               <div class="carousel-item">
                  <img class="w-100 p-3" src="<%=big[1][1]%>" height="400px"
                     alt="second" onclick="location.href='main.do?group_no=<%=big[1][0]%>'">
               </div>
               <div class="carousel-item">
                  <img class="w-100 p-3" src="<%=big[2][1]%>" height="400px"
                     alt="third" onclick="location.href='main.do?group_no=<%=big[2][0]%>'">
               </div>
            </div>

				<!-- Indicators -->
				<ol class="carousel-indicators">
					<li data-target="#random2" data-slide-to="0" class="active"></li>
					<li data-target="#random2" data-slide-to="1"></li>
					<li data-target="#random2" data-slide-to="2"></li>
				</ol>

				<!-- 화살표 버튼 -->
				<a class="left carousel-control" href="#random2" role="button"
					data-slide="prev"> <span
					class="glyphicon glyphicon-chevron-left" aria-hidden="false"></span>
					<span class="sr-only">Previous</span>
				</a> <a class="right carousel-control" href="#random2" role="button"
					data-slide="next"> <span
					class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
					<span class="sr-only">Next</span>
				</a>
		
				
			</div>
		</div>
		
		<!-- 신규소모임띄우기 -->
		<div class="container3">
			<div id="random3" class="carousel slide" data-ride="carousel">

				<!-- Wrapper for slides -->
				<div class="carousel-inner">
               <div class="carousel-item active">
                  <img class="w-100 p-3" src="<%=new_g[0][1]%>" height="400px"
                     alt="first" onclick="location.href='main.do?group_no=<%=new_g[0][0]%>'">
               </div>
               <div class="carousel-item">
                  <img class="w-100 p-3" src="<%=new_g[1][1]%>" height="400px"
                     alt="second" onclick="location.href='main.do?group_no=<%=new_g[1][0]%>'">
               </div>
               <div class="carousel-item">
                  <img class="w-100 p-3" src="<%=new_g[2][1]%>" height="400px"
                     alt="third" onclick="location.href='main.do?group_no=<%=new_g[2][0]%>'">
               </div>
            </div>

				<!-- Indicators -->
				<ol class="carousel-indicators">
					<li data-target="#random3" data-slide-to="0" class="active"></li>
					<li data-target="#random3" data-slide-to="1"></li>
					<li data-target="#random3" data-slide-to="2"></li>
				</ol>

				<!-- 화살표 버튼 -->
				<a class="left carousel-control" href="#random3" role="button"
					data-slide="prev"> <span
					class="glyphicon glyphicon-chevron-left" aria-hidden="false"></span>
					<span class="sr-only">Previous</span>
				</a> <a class="right carousel-control" href="#random3" role="button"
					data-slide="next"> <span
					class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
					<span class="sr-only">Next</span>
				</a>
		
				
			</div>
		</div>
	</div>
</body>
</html>