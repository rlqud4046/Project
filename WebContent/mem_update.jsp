<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보 수정</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script type="text/javascript" src="js/jquery-3.4.1.js"></script>
<%--회원가입시 입력정보 확인 --%>
<script type="text/javascript" src="js/mem.js"></script>
<script type="text/javascript" src="js/memUpdate.js"></script>
<style>
	#interest td{border:0; width: 1em;}
</style>
</head>
<body>
  <div class="container">
		<!-- 아이디/비밀번호/이메일 -->
	<div class="col-md-8 col-md-offset-2">
	<div class="row">
    <form class="form-horizontal" method="post" action="<%=request.getContextPath()%>/member_updateOk.do" enctype="multipart/form-data" onsubmit="return mem_check()">
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
                <input type="hidden" id="profile_crnt" name="profile_crnt">
              </div>
		  </div><!-- 프로필사진 end -->
          <div class="form-group"><!-- 아이디 -->
            <label for="id" class="col-md-2 control-label">아이디</label>
            <div class="col-md-8" id="divId">
              <input type="text" class="form-control" id="id" name="id" placeholder="ID" readonly>
            </div>

		  </div>
		  <div class="form-group"><!-- 비밀번호 -->
		    <label for="pwd" class="col-md-2 control-label" id="pwdBox">비밀번호</label>
		    <div class="col-md-8" id="divPwd">
			  <input type="password" class="form-control" id="pwd" name="pwd" placeholder="PASSWORD">
			  <span class="control-label" id="pwdError"></span>
		    </div>
		  </div>
		  <div class="form-group"><!-- 비밀번호 확인 -->
		    <label for="pwdchk" class="col-md-2 control-label" id="pwdChckBox">비밀번호 확인</label>
			<div class="col-md-8" id="divPwdChk">
			  <input type="password" class="form-control" id="pwdChk" name="pwdChk" placeholder="PASSWORD">
			  <span class="control-label" id="pwdChkError"></span>
			</div>
		  </div>
		  <div class="form-group"><!-- 이름 -->
            <label for="mem_name" class="col-md-2 control-label">이 름</label>
            <div class="col-md-8" id="divName">
              <input type="text" class="form-control" id="mem_name" name="mem_name" placeholder="숫자/특수문자 입력 불가">
              <span class="control-label" id="nameError"></span>
          	</div>
		  </div>
		  <div class="form-group"><!-- 닉네임 -->
            <label for="nickname" class="col-md-2 control-label">닉네임</label>
            <div class="col-md-8" id="divNick">
              <input type="text" class="form-control" id="nickname" name="nickname" placeholder="별명 입력">
              <span class="control-label" id="nickError"></span>
          	</div>
          	<div class="col-md-2">
              <input type="button" class="btn btn-default" id="nickChkBtn" value="중복확인" onclick="checkNick()">
          	</div>
		  </div>
		  <div class="form-group"><!-- 생년월일 -->
	        <label for="birth" class="col-md-2 control-label">생년월일</label>
	        <div class="col-md-8" id="divBirth">
	          <input type="date" class="form-control" id="birth" name="birth">
	          <span class="control-label" id="birthError"></span>
	        </div>
	      </div><!-- 생년월일 -->
		  <div class="form-group"><!-- 이메일 -->
		    <label for="e_mail" class="col-md-2 control-label">이메일</label>
		    <div class="col-md-8" id="divEmail">
		      <input type="text" class="form-control" id="e_mail" name="e_mail">
		      <span class="control-label" id="emailError"></span>
		    </div>
		  </div><!-- 이메일 -->
		  <div class="form-group"><!-- 연락처 -->
		    <label for="phone" class="col-md-2 control-label">연락처</label>
		    <div class="col-md-5" id="divPhone">
		      <input type="text" class="form-control" id="phone" name="phone" placeholder="1234-1234">
		      <span class="control-label" id="phoneError"></span>
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
		      <select class="form-control" id="check_q" name="check_q">
		      	<option value="">선택</option>
		      	<c:forEach var="question" items="${qList }">
		      	<option value="${question }">${question }</option>
		      	</c:forEach>
		      </select>
		    </div>
		  </div><!-- 비밀번호찾기질문 end-->
		  <div class="form-group"><!-- 비밀번호찾기답변 -->
		    <label for="check_a" class="col-md-2 control-label">비밀번호 답변</label>
		    <div class="col-md-8 has-error" id="divChkA">
		      <input type="text" class="form-control" id="check_a" name="check_a">
		      <span class="control-label" id="chkAError">비밀번호 찾기 질문/답변은 필수 입력 사항입니다</span>
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
	          <select class="form-control" id="city" name="city">
	          	<option value="">시/도</option>
	          	<c:forEach var="city" items="${cityList }">
	          	<option value="${city }">${city }</option>
	          	</c:forEach>
	          </select>
	          <select class="form-control" id="town1" name="town1">
	          	<option value="">구/군</option>
	          </select>
	          <select class="form-control" id="town2" name="town2">
	          	<option value="">구/군</option>
	          </select>
	          <select class="form-control" id="town3" name="town3">
	          	<option value="">구/군</option>
	          </select>
	        </div>
	      </div><!-- 지역선택 -->
	      <div class="form-group"><!-- 관심사 -->
	        <label for="l_category" class="col-md-2 control-label">관심사</label>
	        <div class="col-md-10">    
	        <c:forEach var="i" begin="0" end="${lCategorys.size()-1 }">	     
        	  <table class="table" id="interest">
        		<caption class="h4">${lCategorys[i] }</caption>
        		<c:forEach var="j" begin="0" end="${allCate[i].size()-1 }" step="4">
        		  <tr>
        		    <c:if test="${(j+4) > allCate[i].size() }">
        		      <c:forEach var="k" begin="${j }" end="${allCate[i].size()-1 }">
        		      	<c:set var="cate" value="${allCate[i] }" />        		    
        			    <td>
          		  	      <label class="checkbox-inline">
	          		        <input type="checkbox" value="${cate[k].getInterest_no() }" name="s_category">${cate[k].getS_category() }
	          		      </label>
          		        </td>
          		      </c:forEach>	
        		    </c:if>
        		  <c:if test="${(j+4) <= allCate[i].size() }">
        		    <c:forEach var="k" begin="0" end="3">
        		      <c:set var="cate" value="${allCate[i] }" />        		    
        			  <td>
          		  	    <label class="checkbox-inline">
	          		      <input type="checkbox" value="${cate[j+k].getInterest_no() }" name="s_category">${cate[j+k].getS_category() }
	          		    </label>
          		      </td>
          		    </c:forEach>
          		  </c:if>
          		  </tr>
        		</c:forEach>
        	  </table>
	        </c:forEach>
	        </div>
	      </div><!-- 관심사 end -->
	    </div>
	    </div>
	  <div class="col-md-4 col-md-offset-5 form-inline">
	    <button type="submit" class="btn btn-default">저장</button>&nbsp;&nbsp;
	    <button type="button" class="btn btn-default" onclick="history.back()">취소</button>	
	  </div>
    </form>
    </div>
    </div>
  </div>
</body>
</html>