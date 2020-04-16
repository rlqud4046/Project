<%@page import="model.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="utf-8">
<head>
<meta charset="uft-8" />
<title>summernote</title>
<style type="text/css">
#summernote_body {
   margin: 0 auto;
   width: 1000px;
}

/* 에디터 css */
#title {
   width: 1000px;
   align: center;
}

#main {
   width: 1000px;
   margin: 0 auto;
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

<meta http-equiv="X-UA-Compatible" content="IE=chrome">
<meta name="viewport"
   content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<!-- include jquery -->
<!-- include libs stylesheets -->

<link rel="stylesheet" href="examples/example.css">


<!--    /* 부트스트랩과 제이쿼리 라이브러리를 사용하기 위한 선언 */ -->
<link
   href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css"
   rel="stylesheet">
<script
   src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.4/jquery.js"></script>
<script
   src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script>

<!--    /* summernote의 스타일시트와 자바스크립트을 사용하기 위한 선언 */ -->
<link href="./summernote-master/dist/summernote.css" rel="stylesheet">
<script src="./summernote-master/dist/summernote.js"></script>

</head>
<body id="main">
   <form action="<%=request.getContextPath()%>/board_write.do?group_no=${group_No}&board_category=${board_category}" method="post" enctype="multipart/form-data">
      <input type="hidden" id="thumb" name="thumb" value="">
      
      
      <hr style="border: 2px solid skyblue;">
      <!--    <td><textarea class="summernote" rows="10" cols="100" name="tekst"></textarea></td> -->

      <div>
         <input name="title" type="text" class="form-control" id="title"
            placeholder="제목"> <br>
      </div>
      <input type="hidden" name="context">
      <textarea class="summernote" id="summernote" name="cont"></textarea>
      <script>
         $(document).ready(function() {
            $('#summernote').summernote({
               width : 1000,
               height : 600,
               callbacks : { 
                           onImageUpload : function(files, editor, welEditable) {
                                          sendFile(files[0], editor, welEditable);
                                       }
               }
            });

            function sendFile(file, editor, welEditable) {
               data = new FormData();
               data.append("uploadFile", file);
               $.ajax({
                  data : data,
                  type : "POST",
                  url : "board_image_upload.jsp",
                  cache : false,
                  contentType : false,
                  processData : false,
                  success : function(data) {
                     $(editor).summernote('editor.insertImage', data.url);
                     $('.note-editable').append('<img src="'+data.url+'" width="480" height="auto"/>');
                     
                     if($('#thumb').val() == ""){
                        $('#thumb').val(data.localPath);
                     }

                  }
                });
            }
         });
      </script>

      <label for="chk1"> <input type="checkbox" id="chk1" name="imp"
         value="1" onclick="check()" style="font-size: 30px" />&nbsp; 상단에
         고정하기 <script>
            function check() {
               var chk = $('[name=imp]').prop('checked');
            }
         </script>
      </label> 
      <input class="btn btn-info btn-sm" type="submit" value="글쓰기">&nbsp;&nbsp;
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
      <input type="text" class="upload_text" readonly="readonly"> <br>
      <br>    
   </form>
   <!-- 본문 내용 담아서 넘겨주기 (이미지, 글) -->
   <script type="text/javascript">
         $("input:submit").click(function() {
            $("input[name=context]").val($('.note-editable').html())
         })
   </script>
</body>
</html>