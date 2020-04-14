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

	<form class="form-inline" action="find_id_email.do" method="post">
		
	<table width="200" height="150">
		<tr>
			<td>
				<div class="form-group">
  					<input type="text" class="form-control" name="name" placeholder="이름" >
	    		</div>
	    	</td>
	    </tr>
	    
	 	<tr>
	 		<td>
 				<div class="form-group">
    				<input type="email" class="form-control" name="email" placeholder="이메일">
 	 			</div>
 	 		 </td>
 	 	</tr>
 	 	
 	 	<tr>
 	 		<td>
 	  			<button type="submit" class="btn btn-default btn-block">아이디찾기</button>
 	   		</td>
 	   </tr>
 	   </table>
 	   
 	   <a href="<%= request.getContextPath() %>/login.do">로그인 하기</a>
  </form>
</div>

</body>
</html>