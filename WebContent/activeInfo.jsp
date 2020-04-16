<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script type="text/javascript">

// 추방 확인 창
$(function(){
	   $('#btn-chubang').on('click',function(){
	      
	      var id = $('#mem_id').val();
	      var group_no = $('#group_no').val();
	      
	      let ok = confirm("추방하시겠습니까?");
	      if (ok){
	          location.href = "chubang.do?id="+id+"&group_no="+group_no; 
	      }
	   });
	});   

// 추방회원 목록 화면제어
function black() {
     document.getElementById("normal").style.display= "none";
     document.getElementById("black").style.display= "block";
}

// 일반회원 목록 화면제어
function normal() {
     document.getElementById("black").style.display= "none";
     document.getElementById("normal").style.display= "block";
}
 
</script>

<style type="text/css">
</style>

</head>
<body>

   <div class="container" align="left">
   <input type="hidden" id="mem_id" value="${id }">
   <input type="hidden" id="group_no" value="${sessionScope.group_No}">
   <table class="table table-bordered table-hover">
      <c:set var="list" value="${list }"></c:set>
      <tr>
         <td rowspan="3" align="center"><img alt="프로필 이미지" src="${list.getProfile_img() }"
            style="width: 200px; height: auto;"></td>
         <td><h3>${list.getNickname() }(${list.getId()})  <button id="btn-chubang" class="btn btn-danger" onclick="chubang()">추방하기</button></h3></td>
      </tr>

      <tr>
         <c:if test="${rating == 1 }">
            <td><h3>준회원</h3></td>
         </c:if>
         <c:if test="${rating == 2 }">
            <td><h3>정회원</h3></td>
         </c:if>
         <c:if test="${rating == 3 }">
            <td><h3>우수회원</h3></td>
         </c:if>
         <c:if test="${rating == 4 }">
            <td><h3>운영진</h3></td>
         </c:if>
         <c:if test="${rating == 5 }">
            <td><h3>모임장</h3></td>
         </c:if>
      </tr>

      <tr>
         <td><h3>총 게시글 : ${totalBoardNo }개 * 총 댓글 : ${totalReplyNo }개</h3></td>
      </tr>

   </table>
   

   <hr>

   <div class="btn-group" data-toggle="buttons">
   
      <label class="btn btn-primary active" onclick="normal()"> 
      <input type="radio" name="options" id="option1" autocomplete="off" checked>등록한 게시글</label>
      
      <label class="btn btn-success option2" onclick="black()"> 
      <input type="radio" name="options" id="option2" autocomplete="off">댓글단 게시글</label>
      
      
   </div>
   
   <div id="normal" style="display: block;">
            <div class="panel panel-default">
               <!-- Default panel contents -->
               <div class="panel-heading">등록한 게시글 목록</div>
               <table class="table table-bordered table-dark table-hover">
              
                  <tr>
                     <th>제목</th> <th>작성일</th> <th>조회수</th>
                  </tr>
           
                  <c:set var="RBoardList" value="${RBoardList }" />
                  <c:if test="${!empty RBoardList}">
                     <c:forEach var="blist" items="${RBoardList }">
                        <tr>
                           <td>${blist.getBoard_title() }</td>
                           <td>${blist.getBoard_date() }</td>
                           <td>${blist.getBoard_hit() }</td>
                        </tr>
                     </c:forEach>
                  </c:if>
                  <c:if test="${empty RBoardList}">
                        <tr>
                           <td colspan="3"><h3>작성한 게시글이 없습니다.</h3></td>
                        </tr>
                  </c:if>
                
               </table>

            </div>
         </div>
         
         
         <div id="black" class="panel panel-default" style="display: none;">
            <!-- Default panel contents -->
            <div class="panel-heading">댓글단 게시글 목록</div>
            <table class="table table-bordered table-dark table-hover">
            
               <tr>
                  <th>제목</th> <th>작성자</th> <th>작성일</th> <th>조회</th> 
               </tr>
            
               <c:set var="ReplyList" value="${ReplyList }"/>
               <c:if test="${!empty ReplyList }">
                  <c:forEach var="rlist" items="${ReplyList }">
                     <tr>
                        <td>${rlist.getBoard_title() }</td>
                        <td>${rlist.getBoard_writer() }</td>
                        <td>${rlist.getBoard_date() }</td>
                        <td>${rlist.getBoard_hit() }</td>
                     </tr>
                  </c:forEach>
               </c:if>
               <c:if test="${empty ReplyList}">
                  <tr>
                     <td colspan="4"><h3>댓글 작성한 게시글이 없습니다.</h3></td>
                  </tr>
               </c:if>
            
            </table>

      </div>
   </div>
   
</body>
</html>