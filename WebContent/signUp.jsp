<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
	$(function(){
		$("#user_email3").change(function(){
			$("#user_email2").val($("#user_email3 option:selected").val());
		});
	});
	function PostCode(){
		new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                let addr = ''; // 주소 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }
                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('postCode').value = data.zonecode;
                document.getElementById("address").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("detailAddress").focus();
            }
        }).open();
	}
</script>
</head>
<body>
  <div class="container">
    <h2 class="">회원가입</h2>
		<!-- 아이디/비밀번호/이메일 -->
    <form class="form-horizontal" method="post" action="signUp">
	  <div class="panel panel-default">
		<div class="panel-heading">
		  <h3 class="panel-title">필수정보 입력</h3>
		</div>
		<div class="panel-body">
		
		  <div class="form-group"><!-- 아이디 -->
            <label for="user_name" class="col-xs-2 control-label">이 름</label>
            <div class="col-xs-10">
              <input type="text" class="form-control" id="user_name" name="user_name" placeholder="숫자/특수문자 입력 불가">
          </div>
		  </div>
          <div class="form-group"><!-- 아이디 -->
            <label for="user_id" class="col-xs-2 control-label">아이디</label>
              <div class="col-xs-10">
                <input type="text" class="form-control" id="user_id" name="user_id" placeholder="ID">
              </div>
		  </div>
		  <div class="form-group"><!-- 비밀번호 -->
		    <label for="user_pwd" class="col-xs-2 control-label">비밀번호</label>
		    <div class="col-xs-10">
			  <input type="password" class="form-control" id="user_pwd" name="user_pwd" placeholder="PASSWORD">
		    </div>
		  </div>
		  <div class="form-group"><!-- 비밀번호 확인 -->
		    <label for="user_pwdchk" class="col-xs-2 control-label">비밀번호 확인</label>
			<div class="col-xs-10">
			  <input type="password" class="form-control" id="user_pwdchk" name="user_pwdchk" placeholder="PASSWORD">
			</div>
		  </div>
		  <div class="form-group"><!-- 이메일 -->
		    <label for="user_email1" class="col-xs-2 control-label">이메일</label>
		    <div class="col-xs-10 form-inline">
		      <input type="text" class="form-control" id="user_email1" name="user_email">&nbsp;@
		      <input type="text" class="form-control" id="user_email2" name="user_email">
		      <select class="form-control" id="user_email3">
		        <option value="">선택</option>
		        <option value="직접입력">직접입력</option>
		        <option value="gmail.com">gmail.com</option>
		        <option value="naver.com">naver.com</option>
		      </select>
		    </div>
		  </div><!-- 이메일 -->
		  <div class="form-group"><!-- 연락처 -->
		    <label for="user_phone" class="col-xs-2 control-label">연락처</label>
		    <div class="col-xs-10 form-inline">
		      <select class="form-control" name="user_phone">
		      	<option value="">선택</option>
		      	<option value="010">010</option>
		      	<option value="011">011</option>
		      </select>&nbsp;-
		      <input type="text" class="form-control" id="user_phone" placeholder="1234-1234">
		    </div>
		  </div>
		</div>	<!-- panel body -->
	  </div>	<!-- panel-default -->
	  <div class="panel panel-default">
	    <div class="panel-heading">
	      <h3 class="panel-title">선택 입력 사항</h3>
	    </div>
	    <div class="panel-body">
	      <div class="form-group"><!-- 생년월일 -->
	        <label for="user_birth" class="col-xs-2 control-label">생년월일</label>
	        <div class="col-xs-10">
	          <input type="date" class="form-control" name="user_birth">
	        </div>
	      </div><!-- 생년월일 -->
	      <div class="form-group"><!-- 주소입력 -->
	        <label class="col-xs-2 control-label">우편번호</label>
	        <div class="col-xs-10 form-inline">
	          <input type="text" class="form-control" id="postCode" name="postCode" readonly>
	          <button type="button" class="btn btn-default" onclick="PostCode()">우편번호 검색</button>
	        </div>
	      </div>
	      <div class="form-group"><!-- 주소입력 -->
	        <label class="col-xs-2 control-label">집주소</label>
	        <div class="col-xs-10">
	          <input type="text" class="form-control" id="address" name="address" readonly>
	        </div>
	      </div>
	      <div class="form-group"><!-- 주소입력 -->
	        <label class="col-xs-2 control-label">상세주소</label>
	        <div class="col-xs-10">
	          <input type="text" class="form-control" id="detailAddress">
	        </div>
	      </div>
	      <div class="form-group">
	        <label class="col-xs-2 control-label">SMS안내</label>
	        <div class="col-xs-10">
	          <label class="radio-inline"><input type="radio" name="sms" value="yes">받습니다.</label>
	          <label class="radio-inline"><input type="radio" name="sms" value="no">받지 않습니다.</label>
	        </div>
	      </div>
	    </div>
	  </div>
	  <div class="col-xs-3">
	    <button type="submit" class="btn btn-default">회원가입</button>
	    <button type="reset" class="btn btn-default">다시작성</button>	
	  </div> 
    </form>
  </div>
</body>
</html>