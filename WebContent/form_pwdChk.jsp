<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<script type="text/javascript" src="js/jquery-3.4.1.js"></script>
<script>
$(function(){
	$('#ok').on('click', function(){
		var pwd = $('#pwdChk').val();
		$.ajax({
			type: "post",
			url: "pwd_check.jsp",
			data: {"pwd": pwd},
			datatype: "JSON",
			success: function(data){
				var result = data.trim();
				if(result == 1){
					location.href='memberUpdate.do';
				}
				if(result == 0){
					alert('비밀번호가 맞지 않습니다.');
				}
			},
			error: function(){
				alert('data error!')
			}
		});
	});  
	$('#cancel').on('click', function(){
		history.back();
	});
});
</script>
<style>
  #form_pwdChk td{
    border: 0px;
  }
</style>
</head>
<body>	
  <div class="container">
    <div class="row">
  	  <div class="col-sm-10 col-sm-offset-1">
  	    <table class="table center-block" id="form_pwdChk" style="width: 300px">
  	      <tr>
  	        <td colspan="2">
  	          <div class="form-group">
  	            <input type="password" class="form-control input-lg" id="pwdChk" name="pwdChk" placeholder="비밀번호를  입력하세요">
  	          </div>
  	        </td>
  	      </tr>
  	      <tr>
  	        <td>
  	          <div>
  	            <input type="button" class="btn btn-default btn-block" value="확인" id="ok" name="ok">
  	          </div>
  	        </td>
  	        <td>
  	          <div>
  	            <input type="button" class="btn btn-default btn-block" value="취소" id="cancel" name="cancel">
  	          </div>
  	        </td>	  	  
  	      </tr>
  	    </table>	
  	  </div>
    </div>
  </div>
</body>
</html>