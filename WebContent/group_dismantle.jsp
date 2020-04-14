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

	<form class="form-inline" method="post" action="group_dismantle_pwd_ok.do?group_no=16&mem_no=1">
		
	<table width="200" height="150">
	
		<tr>
			<td align="center">
				<h3>비밀번호 입력</h3>
			</td>
		</tr>
		<tr>
			<td align="center">
				<div class="form-group">
  					<input type="password" class="form-control" name="pwd" placeholder="비밀번호를 입력하세요">
	    		</div>
	    	</td>
	    </tr>
	    
 	 	<tr>
 	 		<td align="center">
 	  			<button type="submit" class="btn btn-default" style="width: 45%;">확인</button> &nbsp;&nbsp;
 	  			<button type="button" class="btn btn-default" style="width: 45%;">취소</button>
 	   		</td>
 	   </tr>
 	   </table>
  </form>
</div>


</body>
</html>