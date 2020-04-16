$(document).ready(function() {

	var _width = $(window).width();

	if(0 != _width) {
		$('body').css('height', $(window).height() - 24);
		$('#contents').css('width', $(window).width() - 32);
		$('#iframeEx').css('height', $(window).height() - 100);

		$(window).resize(function() {  //창크기를 늘이거나 줄일경우도 포함해줘야만 한다.
			$('body').css('height', $(window).height() - 24);
			$('#contents').css('width', $(window).width() - 32);
			$('#iframeEx').css('height', $(window).height() - 100);
		});
	}
});