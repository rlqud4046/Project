<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

<style type="text/css">

	.mar {
		margin-top: 100px;
		margin-left: 450px;
	}
	.btn {
		width: 40%;
		margin-left: 250px;
	}
	.thumbnail{
		background-color: #d5d5d5;
		border-radius: 15px;
	}
	
	
</style>

</head>
<body>



<div class=mar>

<div class="row">

  <div class="col-sm-6 col-md-4">
    <div class="thumbnail">
      <div class="caption">
        <h3>회원정보</h3>
        <p>설명설명~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~</p>
        <p><a href="#" class="btn btn-primary btn-lg" role="button">수정</a></p>
      </div>
    </div>
  </div>
  
  <div class="col-sm-6 col-md-4">
    <div class="thumbnail">
      <div class="caption">
        <h3>좋아요 한 글 목록</h3>
        <p>설명설명~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~</p>
        <p><a href="like_list.do?mem_no=${sessionScope.mem_no }" class="btn btn-primary btn-lg" role="button">보기</a></p>
        
        
        
      </div>
    </div>
  </div>
  
</div>

<div class="row">

  <div class="col-sm-6 col-md-4">
    <div class="thumbnail">
      <div class="caption">
        <h3>스케쥴&캘린더</h3>
        <p>설명설명~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~</p>
        <p><a href="#" class="btn btn-primary btn-lg" role="button">관리</a></p>
      </div>
    </div>
  </div>
  
  <div class="col-sm-6 col-md-4">
    <div class="thumbnail">
      <div class="caption">
        <h3>내가 가입한 모임</h3>
        <p>설명설명~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~</p>
        <p><a href="mygroup_list.do?mem_no=${sessionScope.mem_no }" class="btn btn-primary btn-lg" role="button">관리</a></p>
      </div>
    </div>
  </div>
  
</div>

</div>

</body>
</html>