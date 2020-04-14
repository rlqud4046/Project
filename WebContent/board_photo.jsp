<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사진첩 게시판</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script type="text/javascript" src="js/jquery-3.4.1.js"></script>
<script>	
	function boardSearch(){
		var keyword = $('#searchBar').val();
		var category = 6;
		location.href='boardSeaerch.do?keyword='+keyword+'&category='+category;
	}	
</script>
</head>
<body>
  <div class="container">
    <div class="row col-sm-10 col-sm-offset-1">
    
      <%-- 필터 영역 --%>
      <div class="row">
        <div class="col-sm-12">
      	  <div class="pull-right">
      	    <span id="sort_date">작성일</span>
      	    <span id="sort_hit">조회수</span>
      	    <span id="sort_like">좋아요</span>
      	  </div>
        </div>
      </div>
      <%-- 필터 영역 end --%>
      
      <c:if test="${list.size() < 4 }">
      	<c:set var="row1Size" value="${list.size() }"/>
      </c:if>
      <c:if test="${list.size() > 4 }">
      	<c:set var="row1Size" value="4"/>
      </c:if>
      <%-- 사진첩 게시물 목록 상단(1~4번) --%>
      <div class="row">
      <c:forEach var="i" begin="0" end="${row1Size-1 }">
        <div class="col-sm-6 col-md-3">
          <div class="thumbnail">
            <img class="img-responsive" alt="100%x200" src="<%=request.getContextPath()%>${list[i].getBoard_photo()}">
            <div class="caption">
              <input type="hidden" id="mgn_no" name="mgn_no" value="${list[i].getMgn_no() }">
              <input type="hidden" id="board_title" name="board_title" value="${list[i].getBoard_title() }">
              <input type="hidden" id="board_writer" name="board_writer" value="${list[i].getBoard_writer() }">
              <input type="hidden" id="board_hit" name="board_hit" value="${list[i].getBoard_hit() }">
              <input type="hidden" id="board_like" name="board_like" value="${list[i].getBoard_like() }">
              <input type="hidden" id="nickname" name="nickname" value="${list[i].getNickname() }">
              <h4>${list[i].getBoard_title() }</h4>            
              <p>${list[i].getNickname() }(${list[i].getBoard_writer() })</p>
              <p>${list[i].getBoard_date().substring(0,10) }</p>
              <span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span>
              <span id="like">${list[i].getBoard_like() }</span>
              &nbsp;&nbsp;
              <span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>
              <span id="hit">${list[i].getBoard_hit() }</span>
            </div>
          </div>
        </div>
      </c:forEach>
      </div>
      <%-- 사진첩 게시물 목록 상단 end --%>
      
      <%-- 사진첩 게시물 목록 하단(5~8번 --%>
      <div class="row">
      <c:forEach var="i" begin="4" end="${list.size()-1 }">
        <div class="col-sm-6 col-md-3">
          <div class="thumbnail">
            <img class="img-responsive" alt="100%x200" src="<%=request.getContextPath()%>${list[i].getBoard_photo()}">
            <div class="caption">
              <input type="hidden" id="mgn_no" name="mgn_no" value="${list[i].getMgn_no() }">
              <input type="hidden" id="board_title" name="board_title" value="${list[i].getBoard_title() }">
              <input type="hidden" id="board_writer" name="board_writer" value="${list[i].getBoard_writer() }">
              <input type="hidden" id="board_hit" name="board_hit" value="${list[i].getBoard_hit() }">
              <input type="hidden" id="board_like" name="board_like" value="${list[i].getBoard_like() }">
              <input type="hidden" id="nickname" name="nickname" value="닉네임">
              <h4>${list[i].getBoard_title() }</h4>            
              <p>닉네임(아이디)</p>
              <p>${list[i].getBoard_date().substring(0,10) }</p>
              <span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span>
              <span id="like">${list[i].getBoard_like() }</span>
              &nbsp;&nbsp;
              <span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>
              <span id="hit">${list[i].getBoard_hit() }</span>
            </div>
          </div>
        </div>
      </c:forEach>
      </div>
      <%-- 사진첩 게시물 목록 하단 end --%>
      
    </div>
    <%-- 사진첩+필터영역 end --%>  

    <%-- 사진첩 페이지 처리 --%>
	  <div align="center">
	    <ul class="pagination">
	      <li>
	        <a href="getGalleryList.do?page=1">
	          <span aria-hidden="true">&laquo;&laquo;</span>
	        </a>
	      </li>
	    <c:if	test="${startBlock == 1}">
	      <li>
	        <a href="getGalleryList.do?page=1" aria-label="Previous">
	          <span aria-hidden="true">&laquo;</span>
	        </a>
	      </li>
	    </c:if>
	    <c:if	test="${startBlock > 1}">
	      <li>
	        <a href="getGalleryList.do?page=${startBlcok-1}" aria-label="Previous">
	          <span aria-hidden="true">&laquo;</span>
	        </a>
	      </li>
	    </c:if>	      
	  <c:forEach var="i" begin="${startBlock }" end="${endBlock }">
	    <c:if test="${i==page}">
	      <li class="active"><a href="getGalleryList.do?page=${i}">${i}</a></li>
	    </c:if>
	    <c:if test="${i!=page}">
	      <li><a href="getGalleryList.do?page=${i}">${i}</a></li>
	    </c:if>	      
	  </c:forEach>
	    <c:if test="${endBlock == allPage }">
	      <li>
	        <a href="getGalleryList.do?page=${allPage}" aria-label="Next">
	          <span aria-hidden="true">&raquo;</span>
	        </a>
	      </li>
	    </c:if>
	    <c:if test="${endBlock > allPage }">
	      <li>
	        <a href="getGalleryList.do?page=${endBlock+1}" aria-label="Next">
	          <span aria-hidden="true">&raquo;</span>
	        </a>
	      </li>
	    </c:if>
	      <li>
	        <a href="getGalleryList.do?page=${allPage}">
	          <span aria-hidden="true">&raquo;&raquo;</span>
	        </a>
	      </li>
	    </ul>
	  </div>
      <%-- 사진첩 페이지 처리 end --%>
      
      <%-- 검색 영역 --%>
      <div class="row inline-block">
        <div class="col-sm-5 col-sm-offset-3">
          <input type="text" class="form-control" id="searchBar" name="searchBar" placeholder="닉네임 or 아이디 or 글제목">&nbsp;
        </div>
        <div class="col-sm-4 col-md-4">
          <input type="button" class="btn btn-default" id="searchBtn" name="searchBtn" value="검색" onclick="boardSearch()">
          <input type="button" class="btn btn-default" id="writeBtn" name="writeBtn" value="글작성" onclick="location.href='board_write.jsp?group_no=${group_no}&category=6'">
        </div>
      </div>
      <%-- 검색 영역 end --%>
               
  </div>
</body>
</html>