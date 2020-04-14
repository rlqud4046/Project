<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

</head>
<body>

	<div align="center" style="padding-top: 330px">

	<form class="form-inline" action="<%= request.getContextPath() %>/Login_Ok.do" method="post">
		
	<table width="200" height="150">
		<tr>
			<td>
  				<div class="form-group">
    				<input type="text" class="form-control" placeholder="아이디" name="id">
	   	 		</div>
	    	</td>
	    </tr>
	    
	 	<tr>
	 		<td>
 				<div class="form-group">
    				<input type="password" class="form-control" placeholder="비밀번호" name="pwd">
 	 			</div>
 	 		 </td>
 	 	</tr>
 	 	
 	 	<tr>
 	 		<td>
 	  			<button type="submit" class="btn btn-default btn-block">로그인</button>
 	   		</td>
 	   </tr>
 	   </table>
 	   
 	   <a href="<%= request.getContextPath() %>/find_id.do">아이디 찾기</a> &nbsp;&nbsp;
 	   <a href="<%= request.getContextPath() %>/find_pwd.do">비밀번호 찾기</a>&nbsp;&nbsp;
 	   <a href="https://www.nate.com/?f=autorefresh">회원가입</a>
  </form>
</div>
		
</body>
</html>