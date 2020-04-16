/**
 * 
 */

// 모임 정보 수정폼의 이미지 관련 스크립트
$(function(){
	// 대문사진 변경
    $("#group_front_img").on('change', function(){
        readFront(this);
    });
    function readFront(input) {
    	if (input.files && input.files[0]) {
    		var reader = new FileReader();
    		
    		reader.onload = function (e) {
    			$('#front_img').attr('src', e.target.result);
    		}
    		reader.readAsDataURL(input.files[0]);
    	}
    }
    
    // 메인사진 변경
    $("#group_main_img").on('change', function(){
        readMain(this);
    });
    function readMain(input) {
    	if (input.files && input.files[0]) {
    		var reader = new FileReader();
    		
    		reader.onload = function (e) {
    			$('#main_img').attr('src', e.target.result);
    		}
    		reader.readAsDataURL(input.files[0]);
    	}
    }
});