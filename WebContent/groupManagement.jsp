<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title><link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

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
        <h3>회원관리</h3>
        <p>설명설명~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~</p>
        <p><a href="#" class="btn btn-primary btn-lg" role="button">관리</a></p>
      </div>
    </div>
  </div>
  
  <div class="col-sm-6 col-md-4">
    <div class="thumbnail">
      <div class="caption">
        <h3>게시판 권한 설정</h3>
        <p>설명설명~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~</p>
        <p><a href="group_authority.do?group_no=1" class="btn btn-primary btn-lg" role="button">보기</a></p>
      </div>
    </div>
  </div>
  
</div>

<div class="row">

  <div class="col-sm-6 col-md-4">
    <div class="thumbnail">
      <div class="caption">
        <h3>모임 페이지</h3>
        <p>모임 정보 변경</p>
        <p><a href="group_infor.do?group_no=1" class="btn btn-primary btn-lg" role="button">관리</a></p>
      </div>
    </div>
  </div>
  
  <div class="col-sm-6 col-md-4">
    <div class="thumbnail">
      <div class="caption">
        <h3>프리미엄 전환</h3>
        <p>설명설명~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~</p>
        <p><a href="premium.do?group_no=3" class="btn btn-primary btn-lg" role="button">전환/해제</a></p>
      </div>
    </div>
  </div>
  
</div>

<div class="row">

  <div class="col-sm-6 col-md-4">
    <div class="thumbnail">
      <div class="caption">
        <h3>모임 해체</h3>
        <p>설명설명~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~</p>
        <p><a href="group_dismantle.do?group_no=16&mem_no=1" class="btn btn-primary btn-lg" role="button">관리</a></p>
      </div>
    </div>
  </div>
</div>
</div>

</body>
</html>