<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
#service-center{
   width: 900px;
   margin:0 auto;
}
</style>
<link rel="stylesheet" href="./css/font.css">
<link rel="stylesheet" href="./css/style.css">
<link rel="stylesheet"
   href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet"
   href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap-theme.min.css">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script
   src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
<div id=service-center>
   <div class="neon" align="center" style="font-size: 70px">FAQ</div><br>
   <div class="panel-group" id="accordion" role="tablist"
      aria-multiselectable="true">
      <div class="panel panel-default">
         <div class="panel-heading" role="tab" id="headingOne">
            <h4 class="panel-title">
               <a data-toggle="collapse" data-parent="#accordion"
                  href="#collapseOne" aria-expanded="true"
                  aria-controls="collapseOne"> <b style="font-size: 40px;">Q.</b>
                  프리미엄 모임권은 무엇입니까?
               </a>
            </h4>
         </div>
         <div id="collapseOne" class="panel-collapse collapse in"
            role="tabpanel" aria-labelledby="headingOne">
            <div class="panel-body">
               <b style="font-size: 30px; color: tomato">A.</b> 프리미엄 모임권은 구독하는
               유료서비스로 매월/ 매년 중 선택한 기간마다 자동 선결제됩니다. <br>모임 멤버중 한분만 프리미엄 스폰서로
               프리미엄 모임권을 구독하시면 프리미엄 모임이 됩니다. <br>프리미엄 모임권은 한 모임만 선택하여 적용가능하며
               적용 후 모임 변경은 불가합니다.
            </div>
         </div>
      </div>
      <div class="panel panel-default">
         <div class="panel-heading" role="tab" id="headingTwo">
            <h4 class="panel-title">
               <a class="collapsed" data-toggle="collapse"
                  data-parent="#accordion" href="#collapseTwo" aria-expanded="true"
                  aria-controls="collapseTwo"> <b style="font-size: 40px;">Q.</b>
                  모임장은 모임탈퇴를 어떻게 하나요?
               </a>
            </h4>
         </div>
         <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel"
            aria-labelledby="headingTwo">
            <div class="panel-body">
               <b style="font-size: 30px; color: tomato">A.</b> 모임장이 모임을 나가기 위해서는
               모임장에 대한 위임절차가 우선시 진행이 되어야 합니다. <br>모임장이 모임장에 대한 권한을 다른사람에게
               위임후에는 정상적으로 탈퇴가 가능합니다.
            </div>
         </div>
      </div>
      <div class="panel panel-default">
         <div class="panel-heading" role="tab" id="headingThree">
            <h4 class="panel-title">
               <a class="collapsed" data-toggle="collapse"
                  data-parent="#accordion" href="#collapseThree"
                  aria-expanded="true" aria-controls="collapseThree"> <b
                  style="font-size: 40px;">Q.</b> 모임에서 멤버관리는 어떻게 하나요?(임명/ 강퇴)
               </a>
            </h4>
         </div>
         <div id="collapseThree" class="panel-collapse collapse"
            role="tabpanel" aria-labelledby="headingThree">
            <div class="panel-body">
               <b style="font-size: 30px; color: tomato">A.</b> 모임장은 모임관리 페이지를 통해
               멤버들을 관리할 수 있습니다. <br>모임장은 운영진을 임명/해제 하실 수 있으며 모임장은 다른 멤버에게 양도가
               가능합니다.<br> 멤버를 추방하게 되면 모임에 다신 가입할 수 없게 되며, 다시 추방회원 목록에서 삭제를
               하시면 재가입이 가능한 상태가 됩니다.
            </div>
         </div>
      </div>
      <div class="panel panel-default">
         <div class="panel-heading" role="tab" id="headingFour">
            <h4 class="panel-title">
               <a data-toggle="collapse" data-parent="#accordion"
                  href="#collapseFour" aria-expanded="true"
                  aria-controls="collapseFour"> <b style="font-size: 40px;">Q.</b>
                  모임의 정보는 어떻게 수정할 수 있나요?
               </a>
            </h4>
         </div>
         <div id="collapseFour" class="panel-collapse collapse"
            role="tabpanel" aria-labelledby="headingFour">
            <div class="panel-body">
               <b style="font-size: 30px; color: tomato">A.</b> 모임관리 -> 모임페이지를 통해
               모임정보 수정이 가능합니다.
            </div>
         </div>
      </div>
      <div class="panel panel-default">
         <div class="panel-heading" role="tab" id="headingFive">
            <h4 class="panel-title">
               <a data-toggle="collapse" data-parent="#accordion"
                  href="#collapseFive" aria-expanded="true"
                  aria-controls="collapseFive"> <b style="font-size: 40px;">Q.</b>
                  모임은 어떻게 탈퇴하나요?
               </a>
            </h4>
         </div>
         <div id="collapseFive" class="panel-collapse collapse"
            role="tabpanel" aria-labelledby="headingFive">
            <div class="panel-body">
               <b style="font-size: 30px; color: tomato">A.</b> 마이페이지 -> 내가 가입한
               모임을 통해 가입한 모임에 대해서 탈퇴가 가능합니다.
            </div>
         </div>
      </div>
      <div class="panel panel-default">
         <div class="panel-heading" role="tab" id="headingSix">
            <h4 class="panel-title">
               <a data-toggle="collapse" data-parent="#accordion"
                  href="#collapseSix" aria-expanded="true"
                  aria-controls="collapseSix"> <b style="font-size: 40px;">Q.</b>
                  모임이 검색 및 노출되지 않아요.
               </a>
            </h4>
         </div>
         <div id="collapseSix" class="panel-collapse collapse" role="tabpanel"
            aria-labelledby="headingSix">
            <div class="panel-body">
               <b style="font-size: 30px; color: tomato">A.</b> 모임의 검색과 노출은 모임의
               활성도, 정모 참여도 등 다양한 정보를 고려한 내부 알고리즘에 의하여 자동 리스팅 됩니다.<br>회원님이 잘
               맞는 모임을 찾으실 수 있도록 개인별로 최적화된 모임리스트를 제공하고 있기 때문에 회원님마다 보여지는 모임리스트가
               일치하지 않을 수 있습니다.
            </div>
         </div>
      </div>
   </div>
</div>
</body>
</html>