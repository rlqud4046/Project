<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--
Copyright (c) 2003-2020, CKSource - Frederico Knabben. All rights reserved.
For licensing, see LICENSE.md or https://ckeditor.com/legal/ckeditor-oss-license
-->
<html>
<head>
<meta charset="utf-8">
<title>게시판글쓰기</title>
<link rel="stylesheet"
   href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet"
   href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap-theme.min.css">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script
   src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<style type="text/css">
/* 에디터 css */
#title {
   width: 1000px;
   align: center;
}

#main {
   width: 1000px;
   margin: 0 auto;
}

#hr-1 {
   width: 1000px;
}

/* 파일업로드 css */
* {
   font-size: 12px;
   font-family: dotum, '돋움';
}

input.upload_text { /*읽기전용 인풋텍스트*/
   float: right;
   width: 230px; /* 버튼 포함 전체 가로 길이*/
   height: 19px;
   line-height: 19px;
   padding: 0 3px;
   border: 1px solid #bbb;
}

div.upload-btn_wrap input.input_file { /*파일찾기 폼 투명하게*/
   position: absolute;
   top: 0;
   right: 0;
   cursor: pointer;
   opacity: 0;
   filter: alpha(opacity = 0);
   -ms-filter: "alpha(opacity=0)";
   -moz-opacity: 0;
}

div.upload-btn_wrap { /*버튼테두리 감싼 div*/
   overflow: hidden;
   position: relative;
   float: right;
   width: 70px; /*width, height 값은 button(찾아보기)값과 같아야함 */
   height: 21px;
   padding-left: 3px;
}

div.upload-btn_wrap button { /*버튼 div*/
   width: 70px;
   height: 21px;
   font-weight: bold;
   background: #333;
   border: 1px solid #333;
   color: #fff;
}
</style>

<script src="./ckeditor/ckeditor.js"></script>
<script src="./ckeditor/js/sample.js"></script>
<link rel="stylesheet" href="./ckeditor/samples/css/samples.css">
<link rel="stylesheet"
   href="./ckeditor/toolbarconfigurator/lib/codemirror/neo.css">
<meta name="viewport" content="width=device-width,initial-scale=1">
<meta name="description"
   content="Try the latest sample of CKEditor 4 and learn more about customizing your WYSIWYG editor with endless possibilities.">
</head>

<body id="main">
   <form action="<%=request.getContextPath()%>/board_write.do"
      method="post" enctype="multipart/form-data">
      <main>
      <div class="grid-container">
         <div class="content grid-width-100">
            <h1 align="left">자유게시판</h1>
         </div>
      </div>

      <!--    <div class="adjoined-bottom">
      <div class="grid-container">
         <div class="grid-width-100">
            <textarea id="editor" placeholder="이미지나 링크를 첨부하시고, 상세 모집 내용을 적어주세요.">
            <script type="text/javascript">
               CKEDITOR.replace('p_content', {
                  height : 500,
                  width : 1000
               });
            </script>   
            </textarea>
         </div>
      </div>
   </div> -->

      <div align="center" class="row justify-content-md-center">
         <div>
            <hr id="hr-1">
            <input name="title" type="text" class="form-control" id="title"
               placeholder="제목">
         </div>
         <br>
         <div class="col_c" style="margin-bottom: 30px">
            <div class="input-group">
               <textarea class="form-control" id="p_content"
                  placeholder="내용을 입력하세요." name="cont"></textarea>
               <script src="ckeditor/config.js"></script>
               <script>
                  CKEDITOR.replace('p_content', {
                     height : 550,
                     width : 1000,
                  });
               </script>
            </div>
         </div>
      </div>
      </main>
      <script>
         initSample();
      </script>

      <!-- 에디터 하단 -->
      <div align="left">
         <label for="chk1"> <input type="checkbox" id="chk1"
            name="imp" value="1" onclick="check()" style="font-size: 30px" />&nbsp;
            상단에 고정하기 <script>
               function check() {
                  var chk = $('[name=imp]').prop('checked');
               }
            </script>

         </label> &nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
         &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
         &nbsp; &nbsp; &nbsp; &nbsp;
         &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input
            class="btn btn-info btn-sm" type="submit" value="글쓰기">&nbsp;&nbsp;
         <input class="btn btn-info btn-sm" type="reset" value="취소">

         <!--button-->
         <div class="upload-btn_wrap">
            <button type="button" title="파일찾기">
               <span>파일찾기</span>
            </button>
            <input type="file" class="input_file" title="파일찾기" name="file">

            <script type="text/javascript">
               $(function() {
                  $('.upload_text').val('파일을 첨부하세요');
                  $('.input_file').change(function() {
                     var i = $(this).val();
                     $('.upload_text').val(i);
                  });
               });
            </script>
         </div>
         <!--file box-->
         <input type="text" class="upload_text" readonly="readonly">
      </div>
      <br> <br>
   </form>
</body>

</html>