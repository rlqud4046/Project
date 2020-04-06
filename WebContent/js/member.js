
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
	if ($.trim($("#pwdchk").val()) == "") {
		alert("비밀번호확인을 입력하세요!");
		$("#pwdchk").val("").focus();
		return false;
	}
	if ($.trim($("#pwd").val()) != $.trim($("#pwdchk").val())) {
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
	if ($.trim($("#town1").val()) == "") {
		alert("지역을 하나 이상 선택 해야합니다!");
		$("#city").val("").focus();
		return false;
	}
	if ($.trim($("input[type=checkbox][name=s_category]:checked").val()) == "") {
		alert("관심사를 하나 이상 선택해야 합니다!");
		return false;
	}	
}

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