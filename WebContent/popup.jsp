<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</head>
<body>
   <div class="container">
   <div class="row" style="height: 100px;">   
   </div>
   
   <div class="row">
      <a tabindex="0" id="pop" class="btn btn-default" role="button" data-trigger="focus"
      data-placement="bottom" 
      >test</a>
   </div>
   </div>
   
   <div id="mypop" class="container hide">
   <div class="row">
      <div class="col-sm-3 col-sm-offset-1">
         <img class="img-circle" alt="" src="https://cdn.pixabay.com/photo/2017/06/13/12/54/profile-2398783_960_720.png" width="50px" height="50px">
      </div>
      <div class="col-sm-8">
         <ul style="list-style: none;">
            <li>닉네임(아이디)</li>
            <li>이름</li>
            <li>이메일</li>
         </ul>
      </div>
      <div class="col-sm-12">
         <div class="col-sm-6"><button class="btn btn-default btn-sm">마이페이지</button></div>
         <div class="col-sm-6"><button class="btn btn-default btn-sm">가입한 모임</button></div>            
      </div>
   </div>      
   </div>
<script>


    $('#pop').popover({
       html: true,
       content: $('#mypop').html()
    });

   
   
</script>
</body>
</html>