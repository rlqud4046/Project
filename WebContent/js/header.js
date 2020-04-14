/**
 * 
 */

$(function(){
	
    $('#pop').popover({
    	html: true,
    	content: $('#mypop').html()
    });
    
    $('#pop').on('click', function(){
    	
    	$.ajax({
    		type: 'post',
    		url: 'get_mem_info.jsp',
    		datatype: 'JSON',
    		success: function(data){
    			var info = JSON.parse(data);
    			//alert(info.nickname);
    			var profile_popover = info.profile_img;
    			var id = info.id;
    			var nickname = info.nickname;
    			var nickId = nickname+'('+id+')';
    			var mem_name = info.mem_name;
    			var e_mail = info.e_mail;
    			$('#profile_popover').attr('src', 'upload'+profile_popover);
    			$('#nickId_popover').text(nickId);
    			$('#name_popover').text(mem_name);
    			$('#e_mail_popover').text(e_mail);
    			
    		},
    		error: function(){
    			alert('data error!');
    		}
    	});    	
    });
    
});