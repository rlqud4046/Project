<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script type="text/javascript" src="js/jquery-3.4.1.js"></script>
<script src="js/groupInfo.js"></script>
</head>
<body>
  <div class="container">
  	<div class="row col-sm-10 col-sm-offset-1">
  	  <form method="post" action="<%=request.getContextPath() %>/groupUpdateOk.do" enctype="multipart/form-data">
  	  	<%-- hidden으로 전달할 속성 : 모임번호, 기존 이미지(대문/메인) 경로 --%>
  	  	<input type="hidden" id="group_no" name="group_no" value="그룹 번호">
  	  	<input type="hidden" id="front_img_crnt" name="front_img_crnt" value="${groupInfo.getGroup_front_img() }">
  	  	<input type="hidden" id="main_img_crnt" name="main_img_crnt" value="${groupInfo.getGroup_main_img() }">
  	  	<div class="col-sm-6">
  	  	  <%-- 대문사진 변경 --%>
  	      <div class="panel panel-default">
  	        <div class="panel-heading">
  	          <h3 class="panel-title">대문사진 변경</h3>
  	        </div>
  	        <div class="panel-body">
  	          <div class="form-group col-sm-6">
  	            <img id="front_img" src="https://previews.123rf.com/images/alexcoolok/alexcoolok1510/alexcoolok151000017/47523471-%EB%88%88%EA%B3%BC-%EC%A0%84%EB%82%98%EB%AC%B4-%EB%82%98%EB%AC%B4%EC%99%80-%ED%95%98%EB%8A%98-%EA%B0%80%EB%A1%9C-%EC%99%84%EB%B2%BD-%ED%95%9C-%ED%81%AC%EB%A6%AC%EC%8A%A4%EB%A7%88%EC%8A%A4-%EA%B2%A8%EC%9A%B8-%EC%82%B0-%ED%92%8D%EA%B2%BD%EC%9E%85%EB%8B%88%EB%8B%A4-%EB%B2%A1%ED%84%B0.jpg" alt="" class="img-thumbnail" width="200px">
  	          </div>
  	          <div class="form-group col-sm-6">
  	            <p>파일 업로드<p>
  	            <input type="file" id="group_front_img" name="group_front_img" class="form-control">
  	          </div>
  	        </div>
  	      </div>
  	      <%-- 대문사진 변경 end --%>
  	      
  	      <%-- 대표사진 변경  --%>
  	      <div class="panel panel-default">
  	        <div class="panel-heading">
  	          <h3 class="panel-title">대표사진 변경</h3>
  	        </div>
  	        <div class="panel-body">
  	          <div class="form-group col-sm-6">
  	            <img id="main_img" src="https://previews.123rf.com/images/alexcoolok/alexcoolok1510/alexcoolok151000017/47523471-%EB%88%88%EA%B3%BC-%EC%A0%84%EB%82%98%EB%AC%B4-%EB%82%98%EB%AC%B4%EC%99%80-%ED%95%98%EB%8A%98-%EA%B0%80%EB%A1%9C-%EC%99%84%EB%B2%BD-%ED%95%9C-%ED%81%AC%EB%A6%AC%EC%8A%A4%EB%A7%88%EC%8A%A4-%EA%B2%A8%EC%9A%B8-%EC%82%B0-%ED%92%8D%EA%B2%BD%EC%9E%85%EB%8B%88%EB%8B%A4-%EB%B2%A1%ED%84%B0.jpg" alt="" class="img-thumbnail" width="200px">
  	          </div>
  	          <div class="form-group col-sm-6">
  	            <p>파일 업로드<p>
  	            <input type="file" id="group_main_img" name="group_main_img" class="form-control">
  	          </div>
  	        </div>
  	      </div>
  	      <%-- 대표사진 변경  end--%>
  	      
  	      <%-- 모임 이름--%>
  	      <div class="panel panel-default">
  	        <div class="panel-heading">
  	          <h3 class="panel-title">모임 이름</h3>
  	        </div>
  	        <div class="panel-body">
  	          <div class="form-group">
  	            <input type="text" class="form-control" id="group_name" name="group_name" value="${groupInfo.getGroup_name() }">
  	          </div>
  	        </div>
  	      </div>
  	      <%-- 모임 이름 end--%>
  	      
  	      <%-- 모임 소개글 --%>
  	      <div class="panel panel-default">
  	        <div class="panel-heading">
  	          <h3 class="panel-title">모임 소개글</h3>
  	        </div>
  	        <div class="panel-body">
  	          <div class="form-group">
  	            <textarea class="form-control" id="group_intro" name="group_intro">${groupInfo.getGroup_intro() }</textarea>
  	          </div>
  	        </div>
  	      </div>
  	      <%-- 모임 소개글 end --%>
  	  	</div>
  	    
  	    <div class="col-sm-6">
  	      <%-- sns  --%>
  	      <div class="panel panel-default">
  	        <div class="panel-heading">
  	          <h3 class="panel-title">SNS</h3>
  	        </div>
  	        <div class="panel-body">
  	          <div class="form-group">
  	            <label class="control-label">오픈카카오톡</label>
  	            <input type="text" class="form-control" id="group_chatroom" name="group_chatroom" value="${groupInfo.getGroup_chatroom() }">
  	            <br>
  	          </div>
  	          <div class="form-group">
  	            <label class="control-label">페이스북</label>
  	            <input type="text" class="form-control" id="group_facebook" name="group_facebook" value="${groupInfo.getGroup_facebook() }">
  	            <br>
  	          </div>
  	          <div class="form-group">
  	            <label class="control-label">인스타그램</label>
  	            <input type="text" class="form-control" id="group_insta" name="group_insta" value="${groupInfo.getGroup_insta() }">
  	          </div>
  	        </div>
  	      </div>
  	      <%-- sns end  --%>
  	    </div>
  	    
  	    <div class="col-sm-4 col-sm-offset-4">
  	      <div class="row">
  	        <div class="col-sm-6">
  	    	<input type="submit" class="btn btn-default btn-block" value="저장">
  	        </div>
  	        <div class="col-sm-6">
  	    	<input type="button" class="btn btn-default btn-block" value="취소" onclick="">
  	        </div>
  	      </div>
  	    </div>
  	    
  	  </form>
  	</div>    
  </div>
</body>
</html>