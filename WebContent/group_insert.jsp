<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>


<link rel="stylesheet" href="./css/bootstrap_3-3-2.css">
<script src="https://code.jquery.com/jquery-3.4.1.slim.js"
	integrity="sha256-BTlTdQO9/fascB1drekrDVkaKd9PkwBymMlHOiG+qLI="
	crossorigin="anonymous"></script>
<script src="js/jquery-3.4.1.js"></script>
<script type="text/javascript">
  
  /* selectBox1에서 받은값으로 selectBox2의 옵션을 바꿔주는 펑션 */
  function selectsc(selectBox1){
	 
	  $.ajax({
	   type: "get",
	   url: "selectLike.jsp",
	   data: {"l_c" : selectBox1},
	   datatype: "jsp",
	   success: function(data){
		   
		  	//selectBox2에 있는 값 초기화
		   $("#selectBox2").find("option").remove();
		    
		    //소분류의 문자열 [ , , ]을 쪼개서 배열로 저장
		    var str = "전체, "+data.trim().substring(1,data.trim().length-1);
	
		   	var st = str.split(', ');
		   	
		   
		    for ( var i in st ) {

		    	
		        $("#selectBox2").append("<option value='"+st[i]+"'>"+st[i]+"</option>");
		      }
		    
		    document.getElementById('select_l_category').setAttribute('value',selectBox1);
		 },
		 error: function () {
		     alert("오류가 발생하였습니다.");
		 }                     
	   });
   }
  
  /* selectBox2에서 선택한 값을 hidden타입의 value속성값으로 넣어주는 펑션 */
   function selectLike(selectBox2){
	   document.getElementById('select_s_category').setAttribute('value',selectBox2);
  } 
   
   function selectcity(selectBox3){
		  $.ajax({
		   type: "get",
		   url: "selectArea.jsp",
		   data: {"city" : selectBox3},
		   datatype: "jsp",
		   success: function(data){
			   
			 //selectBox4에 있는 값 초기화
			   $("#selectBox4").find("option").remove();
			    
			    //소분류의 문자열 [ , , ]을 쪼개서 배열로 저장
			    var str = "전체, "+ data.trim().substring(1,data.trim().length-1);
		
			   	var st = str.split(', ');
			   	
			   	/* $("#selectBox4").append("<option value=''>"전체"</option>"); */
			    for ( var i in st ) {
			        $("#selectBox4").append("<option value='"+st[i]+"'>"+st[i]+"</option>");
			      }
			    
			    document.getElementById('select_city').setAttribute('value',selectBox3);
			 },
			 error: function () {
			     alert("오류가 발생하였습니다.");
			 }                     
		   });
	   }
   
   /* selectBox3에서 선택한 값을 hidden타입의 value속성값으로 넣어주는 펑션 */
   function selectArea(selectBox4){
	   document.getElementById('select_town').setAttribute('value',selectBox4);
  } 

</script>

<style type="text/css">
#groupName {
	width: 40%;
}

#selectBox1,#selectBox2,#selectBox3,#selectBox4 {
	width: 90%;
}
</style>


</head>
<body>

	<form action="group_insert_ok.do" style="margin-left: 15%; margin-right: 15%;">
      <div align="center">
         <c:set var="l_category" value="${l_category }" />
         <c:set var="city" value="${city }" />

         <table class="table">
            <tr>
               <td colspan="2">그룹명칭</td>
            </tr>

            <tr>
               <td colspan="2"><input type="text" class=inline-block
                  placeholder="모임명을 입력하세요" required size="98%;" name="group_name"></td>
            </tr>

            <tr>
               <td colspan="2">모임 관심사</td>
            </tr>
            <tr>
               <td><select class="form-control" id="selectBox1"
                  onchange="selectsc(this.value)">
                     <option value=''>관심사</option>
                     <c:forEach items="${l_category }" var="dto">
                        <option value="${dto.getL_category() }">${dto.getL_category() }</option>
                     </c:forEach>
               </select></td>

               <td><select class="form-control" id="selectBox2"
                  onchange="selectLike(this.value)">
                     <option value=''>전체</option>
               </select></td>
            </tr>

            <tr>
               <td colspan="2">모임 지역</td>
            </tr>

            <tr>
               <td><select class="form-control" id="selectBox3"
                  onchange="selectcity(this.value)">
                     <option value=''>지역</option>
                     <c:forEach items="${city }" var="dto">
                        <option value="${dto.getCity() }">${dto.getCity() }</option>
                     </c:forEach>
               </select></td>

               <td><select class="form-control" id="selectBox4"
                  onchange="selectArea(this.value)">
                     <option value=''>전체</option>
               </select></td>
            </tr>

            <tr>
               <td colspan="2">모임 소개글</td>
            </tr>

            <tr>
               <td colspan="2"><textarea rows="7" cols="100"
                     name="group_intro" style="resize: none;"></textarea></td>
            </tr>

            <tr>
               <td colspan="2"><button type="submit" class="btn btn-default">제출</button>
                  <input type="hidden" value="" name="select_l_category"
                  id="select_l_category"> <input type="hidden" value=""
                  name="select_s_category" id="select_s_category"
                  required="required"> <input type="hidden" value=""
                  name="select_city" id="select_city"> <input type="hidden"
                  value="" name="select_town" id="select_town" required="required">
                  <input type="hidden" value="${mem_no }" name="mem_no">
                  
                  </td>
            </tr>



         </table>




      </div>
   </form>

</body>
</html>