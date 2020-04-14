<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

<style type="text/css">
    input.submitLink {
    background-color: transparent;
    text-decoration: underline;
    border: none;

    cursor: pointer;
    }
</style>

</head>
<body>

<div align="center" style="padding-top: 330px">

	<form class="form-inline" method="post" action="find_pwd_email.do">
		
	<table width="200" height="150">
		<tr>
			<td>
  				<div class="form-group">
    				<input type="text" class="form-control" name="id" placeholder="아이디">
	   	 		</div>
	    	</td>
	    </tr>
	    
	    <tr>
	 		<td>
 				<div class="form-group">	
    				<select class="form-control" name="check_q">
    					<option>질문을 고르세요</option>
    					<option>아버지 성함은?</option>
    					<option>졸업한 초등학교 이름은?</option>
    					<option>가장 좋아하는 음식은?</option>
    					<option>좋아하는 가수 이름은?</option>
    					<option>나의 고향은?</option>
    				</select>
 	 			</div>
 	 		 </td>
 	 	</tr>
	    
	 	<tr>
	 		<td>
 				<div class="form-group" >
    				<input type="text" class="form-control" name="check_a" placeholder="비밀번호 확인 답변">
 	 			</div>
 	 		 </td>
 	 	</tr>
 	 	
 	   </table>
 	   
 	   <a href="<%= request.getContextPath() %>/find_id.do">아이디 찾으러 가기</a> &nbsp;|&nbsp;
 	   <input type="submit" value="비밀번호 찾기" class="submitLink">
  </form>
</div>

</body>
</html>