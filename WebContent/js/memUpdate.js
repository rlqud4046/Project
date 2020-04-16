/**
 * 
 */
$(function(){
	
	function setArea(area1, area2, area3){
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
    				
    				$('#town1').val(area1).prop("selected", true);
    				$('#town2').val(area2).prop("selected", true);
    				$('#town3').val(area3).prop("selected", true);    				
    				
    				if(area1 == "null") $('#town1').val("").prop("selected", true);
    				if(area2 == "null") $('#town2').val("").prop("selected", true);	
    				if(area3 == "null") $('#town3').val("").prop("selected", true);
    				
    			},
    			error: function(){
    				aleat("data error");
    			}
    		});
    	}
    	
	}
	
	$.ajax({
		type: "POST",
		url: "get_mem_info.jsp",
		datatype: "JSON",
		success: function(data){
			// 받아온 JSON 데이터를 자바 객체로 변환
			var info = JSON.parse(data);
			
			
			var id = info.id;
			var mem_name = info.mem_name;
			var nickname = info.nickname;
			var profile_img = info.profile_img;
			var birth = info.birth;
			var e_mail = info.e_mail;
			var phone = info.phone;
			var check_q = info.check_q;
			var check_a = info.check_a;
			var city = info.city;
			var area1 = info.area1;
			var area2 = info.area2;
			var area3 = info.area3;
			var inter = info.interests;
			
			$('#id').val(id);
			$('#mem_name').val(mem_name);
			$('#nickname').val(nickname);
			if(profile_img != "null"){
				$('#profile').attr("src", "upload/"+profile_img);				
			}
			$('#profile_crnt').val(profile_img);
			$('#birth').val(birth);
			$('#e_mail').val(e_mail);
			$('#phone').val(phone);
			$('#check_q').val(check_q).prop("selected", true);
			$('#check_a').val(check_a);
			$('#city').val(city).prop("selected", true);
			// 저장되어 있는 지역을 선택하기 위한 함수 호출
			setArea(area1, area2, area3);			
			
			// 기존에 선택한 관심사 체크박스를 선택
			
			// 관심사를 문자열 배열로 저장
			var inter = info.interests.split(",");
			
			// 문자열 배열의 길이 저장
			var interSize = inter.length;
			
			// 페이지 안의 모든 체크박스 갯수
			var checkNum = $('input[type="checkbox"]').length;
			
			// 체크박스 선택
			for(var i=0; i<checkNum; i++){
				for(var j=0; j<interSize; j++){
					// i번째 체크박스의 값이 j번째 inter배열의 값과 같다면 i번째 체크박스를 체크
					if($('input[type="checkbox"]:eq('+i+')').val() == inter[j]){
						$('input[type="checkbox"]:eq('+i+')').prop('checked', true);
					}
				}
			}			
		},
		error: function(){
			aleat("data error");
		}
		
	});
});