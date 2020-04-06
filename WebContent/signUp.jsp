<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script>
	$(function() {
	    $("#profile_img").on('change', function(){
	        readURL(this);
	    });
	});
	function readURL(input) {
	    if (input.files && input.files[0]) {
	    var reader = new FileReader();
	
	    reader.onload = function (e) {
	            $('#profile').attr('src', e.target.result);
	        }
	      reader.readAsDataURL(input.files[0]);
	    }
	}
</script>
<style>
	#interest td{border:0; width: 1em;}
</style>
</head>
<body>
  <div class="container">
    <h2 class="">회원가입</h2>
		<!-- 아이디/비밀번호/이메일 -->
	<div class="col-md-8 col-md-offset-2">
	<div class="row">
    <form class="form-horizontal" method="post" action="signUp">
	  <div class="panel panel-default">
		<div class="panel-heading">
		  <h3 class="panel-title">회원정보 입력</h3>
		</div>
		<div class="panel-body">
          <div class="form-group"><!-- 프로필사진 -->
            <label for="id" class="col-md-2 control-label">프로필사진</label>
              <div class="col-md-1" style="margin-bottom: 5px">
              	<img id="profile" src="https://cdn.pixabay.com/photo/2017/06/13/12/54/profile-2398783_960_720.png" alt="sss" class="img-circle" width="50px" height="50px">
              </div>
              <div class="col-md-8 col-md-offset-2">
                <input type="file" class="form-control" id="profile_img" name="profile_img">
              </div>
		  </div><!-- 프로필사진 end -->
          <div class="form-group"><!-- 아이디 -->
            <label for="id" class="col-md-2 control-label">아이디</label>
              <div class="col-md-8">
                <input type="text" class="form-control" id="id" name="id" placeholder="ID">
              </div>
              <div class="col-md-2">
                <input type="button" class="btn btn-default" value="중복확인" onclick="">
              </div>
		  </div>
		  <div class="form-group"><!-- 비밀번호 -->
		    <label for="pwd" class="col-md-2 control-label">비밀번호</label>
		    <div class="col-md-8">
			  <input type="password" class="form-control" id="pwd" name="pwd" placeholder="PASSWORD">
		    </div>
		  </div>
		  <div class="form-group"><!-- 비밀번호 확인 -->
		    <label for="pwdchk" class="col-md-2 control-label">비밀번호 확인</label>
			<div class="col-md-8">
			  <input type="password" class="form-control" id="pwdchk" name="pwdchk" placeholder="PASSWORD">
			</div>
		  </div>
		  <div class="form-group"><!-- 이름 -->
            <label for="mem_name" class="col-md-2 control-label">이 름</label>
            <div class="col-md-8">
              <input type="text" class="form-control" id="mem_name" name="mem_name" placeholder="숫자/특수문자 입력 불가">
          	</div>
		  </div>
		  <div class="form-group"><!-- 닉네임 -->
            <label for="nickname" class="col-md-2 control-label">닉네임</label>
            <div class="col-md-8">
              <input type="text" class="form-control" id="nickname" name="nickname" placeholder="별명 입력">
          	</div>
          	<div class="col-md-2">
              <input type="button" class="btn btn-default" value="중복확인" onclick="">
          	</div>
		  </div>
		  <div class="form-group"><!-- 생년월일 -->
	        <label for="user_birth" class="col-md-2 control-label">생년월일</label>
	        <div class="col-md-8">
	          <input type="date" class="form-control" name="user_birth">
	        </div>
	      </div><!-- 생년월일 -->
		  <div class="form-group"><!-- 이메일 -->
		    <label for="e_mail" class="col-md-2 control-label">이메일</label>
		    <div class="col-md-8">
		      <input type="text" class="form-control" id="e_mail" name="e_mail">
		    </div>
		  </div><!-- 이메일 -->
		  <div class="form-group"><!-- 연락처 -->
		    <label for="phone" class="col-md-2 control-label">연락처</label>
		    <div class="col-md-5">
		      <input type="text" class="form-control" id="phone" name="phone" placeholder="1234-1234">
		    </div>
		    <div class="col-md-3">
		      <input class="form-control" type="text" placeholder="인증번호를 입력하세요">
		    </div>
		    <div class="col-md-2">
		      <input class="btn btn-default" type="button" value="본인인증" onclick="">
		    </div>
		  </div>
		  <div class="form-group"><!-- 비밀번호찾기질문 -->
		    <label for="check_q" class="col-md-2 control-label">비밀번호 질문</label>
		    <div class="col-md-8">
		      <select class="form-control" name="check_q">
		      	<option value="질문1">질문1</option>
		      	<option value="질문2">질문2</option>
		      </select>
		    </div>
		  </div><!-- 비밀번호찾기질문 end-->
		  <div class="form-group"><!-- 비밀번호찾기답변 -->
		    <label for="check_a" class="col-md-2 control-label">비밀번호 답변</label>
		    <div class="col-md-8">
		      <input type="text" class="form-control" name="check_a">
		    </div>
		  </div><!-- 비밀번호찾기답변 end-->
		</div>	<!-- panel body -->
	  </div>	<!-- panel-default -->
	  <div class="panel panel-default">
	    <div class="panel-heading">
	      <h3 class="panel-title">관심사/지역</h3>
	    </div>
	    <div class="panel-body">
	      <div class="form-group"><!-- 지역선택 -->
	        <label for="city" class="col-md-2 control-label">지역선택</label>
	        <div class="col-md-8">
	          <select class="form-control" name="city">
	          	<option value="">시/도</option>
	          	<option value="서울특별시">서울특별시</option>
	          	<option value="경기도">경기도</option>
	          </select>
	          <select class="form-control" name="town1">
	          	<option value="">구/군(1)</option>
	          	<option value="구1">구1</option>
	          	<option value="구2">구2</option>
	          </select>
	          <select class="form-control" name="town2">
	          	<option value="">구/군(2)</option>
	          	<option value="구1">구1</option>
	          	<option value="구2">구2</option>
	          </select>
	          <select class="form-control" name="town3">
	          	<option value="">구/군(3)</option>
	          	<option value="구1">구1</option>
	          	<option value="구2">구2</option>
	          </select>
	        </div>
	      </div><!-- 지역선택 -->
	      <div class="form-group"><!-- 관심사 -->
	        <label for="l_category" class="col-md-2 control-label">관심사</label>
	        <div class="col-md-10">
	          <table class="table" id="interest">
	          	<caption class="h4">스포츠</caption>
	          	<tr>
          		  <td>
          		  	<label class="checkbox-inline">
	          		  <input type="checkbox" value="자전거" name="s_cagegory">자전거
	          		</label>
          		  </td>
          		  <td>
          		  	<label class="checkbox-inline">
	                  <input type="checkbox" value="배드민턴" name="s_cagegory">배드민턴
	                </label>
          		  </td>
          		  <td>
          		  	<label class="checkbox-inline">
	          	      <input type="checkbox" value="볼링" name="s_cagegory">볼링
	                </label>
          		  </td>
          		  <td>
          		  	<label class="checkbox-inline">
	          	      <input type="checkbox" value="수영" name="s_cagegory">수영
	                </label>
          		  </td>
	          	</tr>
	          	<tr>
          		  <td>
          		  	<label class="checkbox-inline">
	          	      <input type="checkbox" value="자전거" name="s_cagegory">테니스
	                </label>
          		  </td>
          		  <td>
          		  	<label class="checkbox-inline">
	          	      <input type="checkbox" value="헬스" name="s_cagegory">헬스
	                </label>
          		  </td>
          		  <td>
          		  	<label class="checkbox-inline">
	          	      <input type="checkbox" value="요가" name="s_cagegory">요가
	                </label>
          		  </td>
	          	</tr>
	          </table>  
	          <table class="table" id="interest">
	          	<caption class="h4">오락</caption>
	          	<tr>
          		  <td>
          		  	<label class="checkbox-inline">
	          		  <input type="checkbox" value="자전거" name="s_cagegory">자전거
	          		</label>
          		  </td>
          		  <td>
          		  	<label class="checkbox-inline">
	                  <input type="checkbox" value="배드민턴" name="s_cagegory">배드민턴
	                </label>
          		  </td>
          		  <td>
          		  	<label class="checkbox-inline">
	          	      <input type="checkbox" value="볼링" name="s_cagegory">볼링
	                </label>
          		  </td>
          		  <td>
          		  	<label class="checkbox-inline">
	          	      <input type="checkbox" value="수영" name="s_cagegory">수영
	                </label>
          		  </td>
	          	</tr>
	          	<tr>
          		  <td>
          		  	<label class="checkbox-inline">
	          	      <input type="checkbox" value="자전거" name="s_cagegory">테니스
	                </label>
          		  </td>
          		  <td>
          		  	<label class="checkbox-inline">
	          	      <input type="checkbox" value="헬스" name="s_cagegory">헬스
	                </label>
          		  </td>
          		  <td>
          		  	<label class="checkbox-inline">
	          	      <input type="checkbox" value="요가" name="s_cagegory">요가
	                </label>
          		  </td>
	          	</tr>
	          </table>
	        </div>
	      </div><!-- 관심사 end -->
	    </div>
	    </div>
	  <div class="col-md-4 col-md-offset-4 form-inline">
	    <button type="submit" class="btn btn-default">회원가입</button>&nbsp;&nbsp;
	    <button type="reset" class="btn btn-default">다시작성</button>	
	  </div>
    </form>
    </div>
    </div>
  </div>
</body>
</html>