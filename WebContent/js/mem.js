// 아이디 중복체크 클릭 시
function checkId(){	
	$('#idChkBtn').click(function(){
		var userId = $('#id').val();
		
		$.ajax({
			type: "post",
			url: "idcheck.jsp",
			data: {"userId": userId},
			datatype: "jsp",
			success: function(data){
				if(data == 1){
					var warn = "중복된 아이디입니다.";
					$('#idError').text('');
					$('#idError').append(warn);
					$('#divId').attr('class', 'col-md-8 has-error');
					$('#idBox').val('').focus;
				}else{
					var warn = "사용 가능한 아이디입니다.";
					$('#idError').text('');
					$('#idError').append(warn);
					$('#divId').attr('class', 'col-md-8 has-success');
					$('#pwd').val('').focus;
				}
			},
			error: function(){
				alert("data error");
			}
		});
	});
}

// 닉네임 중복체크 클릭 시
function checkNick(){	
		var nickname = $('#nickname').val();
		
		$.ajax({
			type: "post",
			url: "nicknameChk.jsp",
			data: {"nickname": nickname},
			datatype: "jsp",
			success: function(data){
				if(data == 1){
					var warn = "사용중인 닉네임입니다.";
					$('#nickError').text(warn);
					$('#divNick').attr('class', 'col-md-8 has-error');
					$('#idBox').focus;
				}else{
					var warn = "사용 가능한 닉네임입니다.";
					$('#nickError').text(warn);
					$('#divNick').attr('class', 'col-md-8 has-success');
				}
			},
			error: function(){
				alert("data error");
			}
		});
}
// 회원가입 버튼 클릭 시 체크 
function mem_check() {
	if ($.trim($("#id").val()) == "") {
		alert("회원아이디를 입력하세요!");
		$("#id").val("").focus();
		return false;
	}
	if ($.trim($("#pwd").val()) == "") {
		alert("비밀번호를 입력하세요!");
		$("#member_pass").val("").focus();
		return false;
	}
	if ($.trim($("#pwdChk").val()) == "") {
		alert("비밀번호확인을 입력하세요!");
		$("#pwdchk").val("").focus();
		return false;
	}
	if ($.trim($("#pwd").val()) != $.trim($("#pwdChk").val())) {
		alert("비밀번호가 일치하지 않습니다!");
		$("#pwd").val("");// 비번 입력창 초기화
		$("#pwdchk").val("");
		$("#pwd").focus();// 비번입력창으로 초기화
		return false;
	}
	if ($.trim($("#mem_name").val()) == "") {
		alert("회원이름을 입력하세요!");
		$("#mem_name").val("").focus();
		return false;
	}
	if ($.trim($("#nickname").val()) == "") {
		alert("회원닉네임을 입력하세요!");
		$("#nickname").val("").focus();
		return false;
	}
	if ($.trim($("#birth").val()) == "") {
		alert("생년월일을 입력하세요!");
		$("#birth").val("").focus();
		return false;
	}
	if ($.trim($("#check_q").val()) == "") {
		alert("비밀번호 확인 질문을 입력하세요!");
		$("#check_q").val("").focus();
		return false;
	}
	if ($.trim($("#check_a").val()) == "") {
		alert("비밀번호 확인 답변을 입력하세요!");
		$("#check_a").val("").focus();
		return false;
	}
	if ($.trim($("#town1").val()) == "" && $.trim($("#town2").val()) == "" && $.trim($("#town3").val()) == "") {
		alert("지역을 하나 이상 선택 해야합니다!");
		$("#city").val("").focus();
		$('#city').nextAll().children().remove();
		$('#city').nextAll().append('<option value="">구/군</option>');
		return false;
	}
	if ($.trim($("input[type=checkbox][name=s_category]:checked").val()) == "") {
		alert("관심사를 하나 이상 선택해야 합니다!");
		return false;
	}	
}


$(function() {
	
	// 프로필 이미지 변경
    $("#profile_img").on('change', function(){
        readURL(this);
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
    
 // 아이디 입력 확인
	$('#id').focusout(function(){
		if($('#id').val()==""){
			var warn = "아이디를 입력해야 합니다.";
			$('#idError').text('');
			$('#idError').append(warn);
			$('#divId').attr('class', 'col-md-8 has-error');
			$('#idBox').val('').focus;
		}else{
			var warn = "중복여부를 확인 해 주세요.";
			$('#idError').text('');
			$('#idError').append(warn);
			$('#divId').attr('class', 'col-md-8');
		}		
	});
	
	// 비밀번호 입력 확인
	$('#pwd').focusout(function(){
		if($('#pwd').val()==""){
			var warn = "비밀번호를 입력해야 합니다.";
			$('#pwdError').text('');
			$('#pwdError').append(warn);
			$('#divPwd').attr('class', 'col-md-8 has-error');
		}else{
			$('#pwdError').text('');
			$('#divPwd').attr('class', 'col-md-8 has-success');
		}
	});
	
	// 비밀번호확인 입력
	$('#pwdChk').focusout(function(){
		if($.trim($('#pwd').val()) == ""){
			$('#pwdChkError').text('');
			$('#divPwdChk').attr('class', 'col-md-8');
		}else if($.trim($('#pwd').val()) != $.trim($('#pwdChk').val())){
			var warn = "비밀번호가 일치하지 않습니다.";
			$('#pwdChkError').text('');
			$('#pwdChkError').append(warn);
			$('#divPwdChk').attr('class', 'col-md-8 has-error');
		}else if($.trim($('#pwd').val()) == $.trim($('#pwdChk').val())){
			var warn = "비밀번호가 일치합니다.";
			$('#pwdChkError').text('');
			$('#pwdChkError').append(warn);
			$('#divPwdChk').attr('class', 'col-md-8 has-success');
			
		}
	});
	
	// 이름입력 확인
	$('#mem_name').focusout(function(){
		if($.trim($('#mem_name').val()) == ""){
			var warn = "이름을 입력해야 합니다.";
			$('#nameError').text(''); 
			$('#nameError').append(warn);
			$('#divName').attr('class', 'col-md-8 has-error');
		}else{
			$('#nameError').text('');
			$('#divName').attr('class', 'col-md-8 has-success');
		}
	});
	
	// 별명 입력 확인
	$('#nickname').focusout(function(){
		if($.trim($('#nickname').val()) == ""){
			var warn = "별명을 입력해야 합니다";
			$('#nickError').text('');
			$('#nickError').append(warn);
			$('#divNick').attr('class', 'col-md-8 has-error');
			
		}else{
			var warn = "중복여부를 확인 해 주세요 "
			$('#nickError').text(warn);
			$('#divNick').attr('class', 'col-md-8');
		}
	});
	
	// 생년월일 입력 확인
	$('#birth').focusout(function(){
		
		if($('#birth').val() == ""){
			var warn = "생년월일을 입력해야 합니다.";
			$('#birthError').text('');
			$('#birthError').append(warn);
			$('#divBirth').attr('class', 'col-md-8 has-error');
		}else{
			$('#birthError').text("");
			$('#divBirth').attr('class', 'col-md-8 has-success');
		}
	});
	
	// 이메일 입력 확인
	$('#e_mail').focusout(function(){
		
		if($('#e_mail').val() == ""){
			var warn = "이메일을 입력해야 합니다.";
			$('#emailError').text('');
			$('#emailError').append(warn);
			$('#divEmail').attr('class', 'col-md-8 has-error');
		}else{
			$('#emailError').text("");
			$('#divEmail').attr('class', 'col-md-8 has-success');
		}
	});
	
	// 연락처 입력 확인
	$('#phone').focusout(function(){
		
		if($('#phone').val() == ""){
			var warn = "연락처를 입력해야 합니다.";
			$('#phoneError').text('');
			$('#phoneError').append(warn);
			$('#divPhone').attr('class', 'col-md-5 has-error');
		}else{
			var warn = "본인인증이 필요합니다";
			$('#phoneError').text(warn);
			$('#divPhone').attr('class', 'col-md-5 has-success');
		}
	});
	
	// 질문/답변 입력 확인
	$('#check_a').focusout(function(){
		if($('#check_a').val() == ""){
			$('#chkAError').text('비밀번호 찾기 질문/답변은 필수 입력 사항입니다');
			$('#divChkA').attr('class', 'col-md-8 has-error');
		}else{
			$('#chkAError').text('');
			$('#divChkA').attr('class', 'col-md-8 has-success');
		}
	});
	
    // 지역목록 가져오기
	$('#city').change(function(){
    	var city = $('#city').val();
    	
    	if(city != ""){
    		
    		$.ajax({
    			type: "post",
    			url: "selectTown.jsp",
    			data: {"city": city},
    			datatype: "jsp",
    			success: function(data){
    				$('#city').nextAll().children().remove();
    				$('#city').nextAll().append('<option value="">구/군</option>');
    				var str = data.split(',');
    				for(var i in str){
    					$('#city').nextAll().append('<option value="'+str[i].trim()+'">'+str[i].trim()+'</option>');
    				}   				
    			},
    			error: function(){
    				aleat("data error");
    			}
    		});
    	}
    	
    });
    
    
});

