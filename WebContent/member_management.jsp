<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <%int rating3 = (int)request.getAttribute("rating"); %> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.container {
	display: block;
	margin-left: auto;
	margin-right: auto;
}

.btn222 {
	-moz-box-shadow: inset 0px 1px 0px 0px #ffffff;
	-webkit-box-shadow: inset 0px 1px 0px 0px #ffffff;
	box-shadow: inset 0px 1px 0px 0px #ffffff;
	background: -webkit-gradient(linear, left top, left bottom, color-stop(0.05, #ffffff), color-stop(1, #f6f6f6));
	background: -moz-linear-gradient(center top, #ffffff 5%, #f6f6f6 100%);
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#ffffff', endColorstr='#f6f6f6');
	background-color: #ffffff;
	-webkit-border-top-left-radius: 15px;
	-moz-border-radius-topleft: 15px;
	border-top-left-radius: 15px;
	-webkit-border-top-right-radius: 15px;
	-moz-border-radius-topright: 15px;
	border-top-right-radius: 15px;
	-webkit-border-bottom-right-radius: 15px;
	-moz-border-radius-bottomright: 15px;
	border-bottom-right-radius: 15px;
	-webkit-border-bottom-left-radius: 15px;
	-moz-border-radius-bottomleft: 15px;
	border-bottom-left-radius: 15px;
	text-indent: 0;
	border: 1px solid #dcdcdc;
	display: inline-block;
	color: #666666;
	font-family: Comic Sans MS;
	font-size: 15px;
	font-weight: bold;
	font-style: normal;
	height: 50px;
	line-height: 50px;
	width: 140px;
	text-decoration: none;
	text-align: center;
	text-shadow: 0px 1px 0px #ffffff;
}

.btn222:hover {
	background: -webkit-gradient(linear, left top, left bottom, color-stop(0.05, #f6f6f6), color-stop(1, #ffffff));
	background: -moz-linear-gradient(center top, #f6f6f6 5%, #ffffff 100%);
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#f6f6f6', endColorstr='#ffffff');
	background-color: #f6f6f6;
}

.btn222:active {
	position: relative;
	top: 1px;
}

.btn222:focus {
	outline: none;
}
</style>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap-theme.min.css">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<script src="//cdn.datatables.net/1.10.10/js/jquery.dataTables.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="//cdn.datatables.net/1.10.10/css/jquery.dataTables.min.css">
	
<script type="text/javascript">

	 	function weim(mem_no, group_no) {
			let weimOk = confirm("위임하시겠습니까?");
			if (weimOk) {
				location.href = "weim.do?mem_no="+mem_no+"&group_no="+group_no;
			}
		}
	 	
		function apologize(mem_num,group_no,rating) {
			/* alert("mem_num: " +mem_num+ "group_no:"+group_no+ "rating: "+rating); */
			let ok = confirm("용서하시겠습니까?");
			if (ok) {
				location.href = "black_delete.do?mem_num="+mem_num+"&group_no="+group_no+"&rating="+0; 
			}
		}
		
		 function black() {
				document.getElementById("normal").style.display= "none";
				document.getElementById("black").style.display= "block";
		}
	   
		 function normal() {
				document.getElementById("black").style.display= "none";
				document.getElementById("normal").style.display= "block";
		}
		 
</script>
</head>
<body>
   <div class="container" align="left">
      <div class="btn-group" data-toggle="buttons">
         <label class="btn btn-primary active" onclick="normal()"> <input type="radio" name="options" id="option1"
            autocomplete="off" checked>일반회원
         </label> <label class="btn btn-danger option2" onclick="black()"> <input type="radio" name="options" id="option2"
            autocomplete="off">추방회원
         </label>
      </div>
      <br> <br>
      <form action="memmgn_update.do?group_no=1" method="post">
         <c:set var="list" value="${List }" />
         <div id="normal" style="display: block;">
            <div class="panel panel-default">
               <!-- Default panel contents -->
               <div class="panel-heading">일반회원 목록</div>
               <table id = "normal_table" class="table table-bordered table-dark table-hover">
                  
                  <thead>
                  <tr>
                     <th>회원등급</th>
                     <th>회원명</th>
                     <th>닉네임(아이디)</th>
                  </tr>
                  </thead>
                  <tbody>
                  <tr>
                     <c:if test="${!empty list}">
                        <c:forEach items="${list }" var="dto">
                           <c:if test="${dto.getRating() > 0 && dto.getRating() < 6 }">
                              <tr>
                                 <td>
                                    <div>
                                       <select class="form-control" id="select1" name="rating" style="margin-top: 7px">
                                          <c:if test="${dto.getRating() == 1 }">
                                             <option value="1">준회원</option>
                                             <option value="1">준회원</option>
                                             <option value="2">정회원</option>
                                             <option value="3">우수회원</option>
                                             <option value="4">운영진</option>
                                          </c:if>
                                          <c:if test="${dto.getRating() == 2 }">
                                             <option value="2">정회원</option>
                                             <option value="1">준회원</option>
                                             <option value="2">정회원</option>
                                             <option value="3">우수회원</option>
                                           <option value="4">운영진</option>
                                          </c:if>
                                          <c:if test="${dto.getRating() == 3 }">
                                             <option value="3">우수회원</option>
                                             <option value="1">준회원</option>
                                             <option value="2">정회원</option>
                                             <option value="3">우수회원</option>
                                             <option value="4">운영진</option>
                                          </c:if>
                                          <c:if test="${dto.getRating() == 4 }">
                                             <option value="4">운영진</option>
                                             <option value="1">준회원</option>
                                             <option value="2">정회원</option>
                                             <option value="3">우수회원</option>
                                             <option value="4">운영진</option>
                                          </c:if>
                                          <c:if test="${dto.getRating() == 5 }">
                                             <option value="5">모임장</option>
                                          </c:if>
                                       </select>
                                    </div>
                                 </td>
                                 <td style="padding-top: 15px; padding-bottom: 15px"><input type="hidden" id="mem_name"
                                    name="mem_name" value="${dto.getMem_name() }" readonly>
                                    <li class="dropdown" style="list-style: none; padding-top: 7px;"><a
                                       class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
                                          ${dto.getMem_name() }</a>
                                       <div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
                                          <a class="dropdown-item" href="InformationActivities.do?group_no=${sessionScope.group_No}&id=${dto.getId()}&rating=${dto.getRating()}" style="margin-left: 20px;">활동정보</a> <br /> <a
                                             class="dropdown-item" href="" style="margin-left: 20px;">쪽지보내기</a> <br />
                                          <c:if test="${rating == 5 }">
                                             <a class="dropdown-item" onclick="weim(${dto.getMem_no()},${sessionScope.group_No})"
                                                style="margin-left: 20px;">모임장 위임</a>
                                          </c:if>
                                       </div></li></td>
                                 <td style="padding-top: 15px; padding-bottom: 15px"><input type="hidden" id="mem_name"
                                    name="nickname_id" value="${dto.getId() }" readonly> <input type="hidden" id="mem_no"
                                    name="mem_no" value="${dto.getMem_no() }" readonly>
                                    <p class="form-control-static">${dto.getNickname() }(${dto.getId() })</p></td>
                              </tr>
                           </c:if>
                        </c:forEach>
                     </c:if>
                  </tr>
                  </tbody>
               </table>
               
               <script type="text/javascript">
			$(document).ready( function () {
			    $('#normal_table').DataTable({
			    	
			    	"lengthChange" : false,
			    	"info" : false,
					"order" : [[0,"desc"]],
					"paingType" : "full_numbers",
					"language": {
						"search" : "검색 : ",
						"paginate" : { 
							"next" : "다음",
							"previous" : "이전"
						}
					}
				});
			} );	
</script>



            </div>
            <div align="center">
               <input class="btn222" type="submit" value="저장">&nbsp;&nbsp;&nbsp;&nbsp; <input class="btn222" type="reset"
                  value="취소">
            </div>
         </div>
      </form>
      <!-- 추방회원목록관리 -->
      <div id="black" class="panel panel-default" style="display: none;">
         <!-- Default panel contents -->
         <div class="panel-heading">추방회원 목록</div>
         <table id ="banned_table" class="table table-bordered table-dark table-hover">
            <colgroup>
               <col width="33.3%" />
               <col width="33.3%" />
               <col width="33.4%" />
            </colgroup>
            <thead>
            
            <tr>
               <th>목록삭제</th>
               <th>회원명</th>
               <th>닉네임(아이디)</th>
            </tr>
            </thead>
            <tbody>
            <tr>
               <c:if test="${!empty list }">
                  <c:forEach items="${list }" var="dto">
                     <tr>
                        <c:if test="${dto.getRating() == 0 }">
                           <td>
                              <%-- <a onclick="apologize(${dto.getMem_no()},${dto.getGroup_no()},${dto.getRating() })" class="btn btn-danger btn-block" role="button">삭제</a> --%>
                              <button name="confirm" class="btn btn-danger btn-block"
                                 onclick="apologize(${dto.getMem_no()},${sessionScope.group_No},${dto.getRating() })">삭제</button>
                           </td>
                           <td style="padding-top: 15px; padding-bottom: 15px">${dto.getMem_name() }</td>
                           <input type="hidden" id="mem_id" name="mem_id" value="${dto.getId() }" readonly>
                           <td style="padding-top: 15px; padding-bottom: 15px">${dto.getNickname() }(${dto.getId() })</td>
                           <input type="hidden" id="mem_num" name="mem_num" value="${dto.getMem_no() }" readonly>
                        </c:if>
                     </tr>
                  </c:forEach>
               </c:if>
            </tr>
            </tbody>
         </table>
         
         <script type="text/javascript">
			$(document).ready( function () {
			    $('#banned_table').DataTable({
			    	
			    	"lengthChange" : false,
			    	"info" : false,
					"order" : [[0,"desc"]],
					"paingType" : "full_numbers",
					"language": {
						"search" : "검색 : ",
						"paginate" : { 
							"next" : "다음",
							"previous" : "이전"
						}
					}
				});
			} )	
</script>
         
         
      </div>
   </div>
</body>
</html>
