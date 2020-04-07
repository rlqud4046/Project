<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript"
	src="http://code.jquery.com/jquery-latest.min.js"></script>
<style type="text/css">
.tab {
	width: 480px;
	height: auto;
	overflow: hidden;
}

.tab ul {
	padding: 0;
	margin: 0;
	list-style: none;
	width: 100%:
    height:auto;
	overflow: hidden;
}

.tab ul li {
	display: inline-block;
	width: 50%;
	float: left;
	line-height: 40px;
	text-align: center;
	cursor: pointer;
}

.tab ul li:hover, .tab ul li.on {
	background: #ccc;
}

.tab .conBox {
	width: 100%;
	height: auto;
	overflow: hidden;
	min-height: 200px;
	background: #ccc;
	display: none;
	text-align: center;
}

.tab .conBox.on {
	display: block;
}
</style>
</head>
<body>
	<div class="tab">
		<ul>
			<li data-id="con1" class="on">받은쪽지함</li>
			<li data-id="con2">보낸쪽지함</li>
		</ul>
		<div>
			<div id="con1" class="conBox on" align="left">
				<input type="submit" value="삭제">
			</div>
			<div>
				<input type="submit" value="전체삭제">
			</div>
			
		</div>
		<div id="con2" class="conBox">cont2</div>
	</div>

	<script>
    $(function(){
        $(".tab ul li").click(function(){ 
            $(".tab ul li").removeClass('on');
            $(".tab .conBox").removeClass('on');
            $(this).addClass('on');
            $("#"+$(this).data('id')).addClass('on');
        });
    });
</script>
</body>
</html>