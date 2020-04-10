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

<div class="container" style="padding-top: 20px">
	
			<h1>회원가입</h1>
	
		<table class="table table-bordered table-hover">
			
			<!-- 이름 -->
			<tr class="form-inline">
				<td>이름</td>
				<td><input type="text" class="form-control"></td>
			</tr>
			
			<!-- 생년월일 -->
			<tr class="form-inline">
				<td>생년월일</td>
				<td><select class="form-control">
					<option value="">년도</option>
					<option value="">1995</option>
					<option value="">1994</option>
					<option value="">1993</option>
					<option value="">1992</option>
					<option value="">1991</option>
					<option value="">1990</option>
					<option value="">1989</option>
				</select>
				-
				<select class="form-control">
					<option value="">월</option>
					<option value="">01</option>
					<option value="">02</option>
					<option value="">03</option>
					<option value="">04</option>
					<option value="">05</option>
					<option value="">06</option>
					<option value="">07</option>
					<option value="">08</option>
					<option value="">09</option>
					<option value="">10</option>
					<option value="">11</option>
					<option value="">12</option>
				</select>
				-
				<select class="form-control">
					<option value="">일</option>
					<option value="">01</option>
					<option value="">02</option>
					<option value="">03</option>
					<option value="">04</option>
					<option value="">05</option>
					<option value="">06</option>
					<option value="">07</option>
					<option value="">08</option>
					<option value="">09</option>
					<option value="">10</option>
					<option value="">11</option>
					<option value="">12</option>
					<option value="">13</option>
					<option value="">14</option>
					<option value="">15</option>
					<option value="">16</option>
					<option value="">17</option>
					<option value="">18</option>
					<option value="">19</option>
					<option value="">20</option>
					<option value="">21</option>
					<option value="">22</option>
					<option value="">23</option>
					<option value="">24</option>
					<option value="">25</option>
					<option value="">26</option>
					<option value="">27</option>
					<option value="">28</option>
					<option value="">29</option>
					<option value="">30</option>
					<option value="">31</option>
				</select></td>
			</tr>
			
			<!-- 성별 -->
			<tr class="form-inline">
				<td>성별</td>
				<td>
				<label><input type="radio" name="male" value="남자">남자</label>
				<label><input type="radio" name="male" value="여자">여자</label>
				</td>
			</tr>
			
			<!-- 아이디 -->
			<tr class="form-inline">
				<td>아이디</td>
				<td>
					<input type="text" class="form-control">
					<button class="btn btn-info">중복확인</button>
				</td>
			</tr>
			
			<!-- 비밀번호 -->
			<tr class="form-inline">
				<td>비밀번호</td>
				<td>
					<input type="password" class="form-control">
				</td>
			</tr>
			
			<!-- 비밀번호 확인 -->
			<tr class="form-inline">
				<td>비밀번호 확인</td>
				<td>
					<input type="password" class="form-control">
				</td>
			</tr>
			
			<!-- 휴대전화 번호 -->
			<tr class="form-inline">
				<td>휴대전화 번호</td>
				<td>
					<select class="form-control">
					<option>SKT</option>
					<option>KT</option>
					<option>LG</option>
				</select>
				
				<select class="form-control">
					<option>010</option>
					<option>011</option>
					<option>017</option>
					<option>018</option>
				</select>
				-
					<input type="text" class="form-control" size="4">
				-
					<input type="text" class="form-control" size="4">
				</td>
			</tr>
			
			<!-- sms수신여부 -->
			<tr class="form-inline">
				<td>sms수신여부</td>
				<td>
					<input type="radio" id="dong" name="agree">
						<label for="dong">동의</label>
					<input type="radio" id="bdong" name="agree">
						<label for="bdong">거부</label>
				</td>
			</tr>
			
			<!-- 이메일 주소 -->
			<tr class="form-inline">
				<td>이메일 주소</td>
				<td>
					<input type="text" class="form-control">
					@
					<input type="text" class="form-control" id="emailInput">
				
				<select class="form-control" id="select1">
					<option value="직접입력">직접입력</option>
					<option value="naver.com">naver.com</option>
					<option value="hanmail.net">hanmail.net</option>
					<option value="gmail.com">gmail.com</option>
				</select>
				</td>
			</tr>
			
			<!-- 이메일 수신여부 -->
			<tr class="form-inline">
				<td>이메일 수신여부</td>
				<td>
					<input type="radio" id="dong" name="agree">
						<label for="dong">동의</label>
					<input type="radio" id="bdong" name="agree">
						<label for="bdong">거부</label>
				</td>
			</tr>
			
			<!-- 우편번호 --> 
			<tr class="form-inline">
				<td>우편번호</td>
				<td>
					<input type="text" class="form-control">
					<button class="btn btn-info">우편 번호 찾기</button>
				</td>
			</tr>
			
			<!-- 나머지 주소 --> 
			<tr class="form-inline">
				<td>나머지 주소</td>
				<td>
					<input type="text" class="form-control" style = width:500px;>
				</td>
			</tr>
			
			<!-- 관심 분야 --> 
			<tr class="form-inline">
				<td>관심 분야</td>
				<td>
					<label><input type="checkbox">JAVA</label>
					<label><input type="checkbox">JAVASCRIPT</label>
					<label><input type="checkbox">CSS3</label>
					<label><input type="checkbox">HTML5</label>
					<label><input type="checkbox">JQUERY</label>
				</td>
			</tr>
		</table>
		
		<div>
			<button class="btn btn-success">확인</button>
			<button class="btn btn-danger">취소</button>
		</div>
		
		
		
		
	</div>
</body>

</body>
</html>