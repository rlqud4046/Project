<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
body {
    position: relative;
    overflow-x: hidden;
}
body,
html { height: 100%;}
.nav .open > a,
.nav .open > a:hover,
.nav .open > a:focus {background-color: transparent;}

/*-------------------------------*/
/*           Wrappers            */
/*-------------------------------*/

#wrapper {
    padding-left: 0;
    -webkit-transition: all 0.5s ease;
    -moz-transition: all 0.5s ease;
    -o-transition: all 0.5s ease;
    transition: all 0.5s ease;
}

#wrapper.toggled {
    padding-left: 220px;
}

#sidebar-wrapper {
   /* z-index는 태그들이 겹칠 때 누가 더 위로 올라가는지를 결정하는 속성, 기본값은 0 */
    z-index: 1000; /* z축 상의 위치를 나타내며, 정수값(음수, 양수). 높은 번호를 가진 레이어는 낮은 번호를 가진 레이어 위에 렌더링된다 */
    left: 220px;
    width: 0;
    height: 100%;
    margin-left: -220px;
    overflow-y: auto; /* 본문에 표시되는 내용에 따라 세로 스크롤이 생긴다. */
    overflow-x: hidden; /* 부모요소의 범위를 넘어가는 자식요소의 부분은 보이지 않도록 처리 */
    background: #1a1a1a;
    -webkit-transition: all 0.5s ease; /* CSS 속성을 변경할 때 애니메이션 속도를 조절하는 방법을 제공 */
    -moz-transition: all 0.5s ease;
    -o-transition: all 0.5s ease;
    transition: all 0.5s ease;
}

#sidebar-wrapper::-webkit-scrollbar {
  display: none; /* 보이지 않음 */
}

#wrapper.toggled #sidebar-wrapper {
    width: 220px;
}

#page-content-wrapper {
    width: 100%;
    padding-top: 70px;
}

#wrapper.toggled #page-content-wrapper {
    position: absolute; /* 가장 가까운 곳에 위치한 조상 엘리먼트에 상대적으로 위치가 지정된다. */
    /* relative가 static인 상태를 기준으로 주어진 픽셀만큼 움직였다면, */
    /* absolute는 position: static 속성을 가지고 있지 않은 부모를 기준으로 움직인다. */
    /* 만약 부모 중에 포지션이 relative, absolute, fixed인 태그가 없다면 가장 위의 태그(body)가 기준이 된다. */
    margin-right: -220px;
}

/*-------------------------------*/
/*     Sidebar nav styles        */
/*-------------------------------*/

.sidebar-nav {
    position: absolute;
    top: 0;
    width: 220px;
    margin: 0;
    padding: 0;
    list-style: none;
}

.sidebar-nav li {
    position: relative;
    line-height: 20px;
    display: inline-block;
    width: 100%;
}

.sidebar-nav li:before {
    content: '';
    position: absolute;
    top: 0;
    left: 0;
    z-index: -1;
    height: 100%;
    width: 3px;
    background-color: #1c1c1c;
    -webkit-transition: width .2s ease-in;
      -moz-transition:  width .2s ease-in;
       -ms-transition:  width .2s ease-in;
            transition: width .2s ease-in;

}
.sidebar-nav li:first-child a {
    color: #fff;
    background-color: #1a1a1a;
}

.sidebar-nav li:before {
    background-color: #D8D8D8;  
}
.sidebar-nav li:hover:before,
.sidebar-nav li.open:hover:before {
    width: 100%;
    -webkit-transition: width .2s ease-in;
      -moz-transition:  width .2s ease-in;
       -ms-transition:  width .2s ease-in;
            transition: width .2s ease-in;

}

.sidebar-nav li a {
    display: block; /* 요소를 block 요소처럼 표시한다. 요소 앞뒤로 줄바꿈 된다. */
    color: #ddd;
    text-decoration: none; /* 선을 만들지 않는다. */
    padding: 10px 15px 10px 30px;    
}

.sidebar-nav li a:hover,
.sidebar-nav li a:active,
.sidebar-nav li a:focus,
.sidebar-nav li.open a:hover,
.sidebar-nav li.open a:active,
.sidebar-nav li.open a:focus{
    color: #fff;
    text-decoration: none;
    background-color: transparent;
}

.sidebar-nav > .sidebar-brand {
    height: 45px;
    font-size: 16px;
    line-height: 24px;
}
.sidebar-nav .dropdown-menu {
    position: relative;
    width: 100%;
    padding: 0;
    margin: 0;
    border-radius: 0;
    border: none;
    background-color: #222;
    box-shadow: none;
}

/*-------------------------------*/
/*       Link2me-Cross         */
/*-------------------------------*/

.link2me {
  position: fixed; /* fixed: 스크롤과 상관없이 항상 문서 최 좌측상단을 기준으로 좌표를 고정 */
  top: 20px;  
  z-index: 999; /* z-index는 태그들이 겹칠 때 누가 더 위로 올라가는지를 결정하는 속성, 기본값은 0 */
  display: block; /* 요소를 block 요소처럼 표시한다. 요소 앞뒤로 줄바꿈 된다. */
  width: 32px;
  height: 32px;
  margin-left: 15px;
  background: transparent;
  border: none;
}
.link2me:hover,
.link2me:focus,
.link2me:active {
  outline: none;
}
.link2me.is-closed:before {
  content: '';
  display: block;
  width: 100px;
  font-size: 14px;
  color: #fff;
  line-height: 32px;
  text-align: center;
  opacity: 0;
  -webkit-transform: translate3d(0,0,0);
  -webkit-transition: all .35s ease-in-out;
}
.link2me.is-closed:hover:before {
  opacity: 1;
  display: block;
  -webkit-transform: translate3d(-100px,0,0);
  -webkit-transition: all .35s ease-in-out;
}

.link2me.is-closed .hamb-top,
.link2me.is-closed .hamb-middle,
.link2me.is-closed .hamb-bottom,
.link2me.is-open .hamb-top,
.link2me.is-open .hamb-middle,
.link2me.is-open .hamb-bottom {
  position: absolute;
  left: 0;
  height: 4px;
  width: 100%;
}
.link2me.is-closed .hamb-top,
.link2me.is-closed .hamb-middle,
.link2me.is-closed .hamb-bottom {
  background-color: #1a1a1a;
}
.link2me.is-closed .hamb-top {
  top: 5px;
  -webkit-transition: all .35s ease-in-out;
}
.link2me.is-closed .hamb-middle {
  top: 50%;
  margin-top: -2px;
}
.link2me.is-closed .hamb-bottom {
  bottom: 5px;  
  -webkit-transition: all .35s ease-in-out;
}

.link2me.is-closed:hover .hamb-top {
  top: 0;
  -webkit-transition: all .35s ease-in-out;
}
.link2me.is-closed:hover .hamb-bottom {
  bottom: 0;
  -webkit-transition: all .35s ease-in-out;
}
.link2me.is-open .hamb-top,
.link2me.is-open .hamb-middle,
.link2me.is-open .hamb-bottom {
  background-color: #1a1a1a;
}
.link2me.is-open .hamb-top,
.link2me.is-open .hamb-bottom {
  top: 50%;
  margin-top: -2px;  
}
.link2me.is-open .hamb-top {
  -webkit-transform: rotate(45deg);
  -webkit-transition: -webkit-transform .2s cubic-bezier(.73,1,.28,.08);
}
.link2me.is-open .hamb-middle { display: none; }
.link2me.is-open .hamb-bottom {
  -webkit-transform: rotate(-45deg);
  -webkit-transition: -webkit-transform .2s cubic-bezier(.73,1,.28,.08);
}
.link2me.is-open:before {
  content: '';
  display: block;
  width: 100px;
  font-size: 14px;
  color: #fff;
  line-height: 32px;
  text-align: center;
  opacity: 0;
  -webkit-transform: translate3d(0,0,0);
  -webkit-transition: all .35s ease-in-out;
}
.link2me.is-open:hover:before {
  opacity: 1;
  display: block;
  -webkit-transform: translate3d(-100px,0,0);
  -webkit-transition: all .35s ease-in-out;
}

/*-------------------------------*/
/*            Overlay            */
/*-------------------------------*/

.overlay {
    position: fixed; /* fixed: 스크롤과 상관없이 항상 문서 최 좌측상단을 기준으로 좌표를 고정 */
    display: none;
    width: 100%;
    height: 100%;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    background-color: rgba(250,250,250,.8);
    z-index: 1;
}
</style>
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<script>
$(document).ready(function () {
  var trigger = $('.link2me'),
      overlay = $('.overlay'),
     isClosed = false;

    trigger.click(function () {
      link2me_cross();      
    });

    function link2me_cross() {

      if (isClosed == true) {          
        overlay.hide();
        trigger.removeClass('is-open');
        trigger.addClass('is-closed');
        isClosed = false;
      } else {   
        overlay.show();
        trigger.removeClass('is-closed');
        trigger.addClass('is-open');
        isClosed = true;
      }
  }
 
  $('[data-toggle="offcanvas"]').click(function () {
        $('#wrapper').toggleClass('toggled');
  });  
});
</script>
</body>
    <div id="wrapper">
        <div class="overlay"></div>
        <%--Sidebar 숨김으로 보였다 보이지 않았다 하는 부분 --%>
        <nav class="navbar navbar-inverse navbar-fixed-top" id="sidebar-wrapper" role="navigation">
            <ul class="nav sidebar-nav">
                <li>
                    <a href="#"><h3>관.종.들.모.여.라</h3></a>
                </li>
                <li>
                    <a href="#">모임전체보기</a>
                </li>
                <li>
                    <a href="#">전체 공지</a>
                </li>
                <li>
                    <a href="#">모임 개설</a>
                </li>
                <li>
                    <a href="#">가입한 모임</a>
                </li>
                <li>
                    <a href="#">캘린더</a>
                </li>
                <li >
                     <a href="https://ko-kr.facebook.com/" target="_blank"><i class="fa fa-facebook"></i>&nbsp;&nbsp;&nbsp;<b>Facebook</b></a>
                </li>
                <li>
                   <a href="https://www.instagram.com/?hl=ko" target="_blank"><i class="fa fa-instagram"></i>&nbsp;&nbsp;&nbsp;<b>Instagram</b></a>
                </li>
            </ul>
        </nav>
        <!-- /#sidebar-wrapper -->

        <!-- Page Content -->
        <div id="page-content-wrapper">
            <button type="button" class="link2me is-closed" data-toggle="offcanvas">
                <span class="hamb-top"></span>
                <span class="hamb-middle"></span>
                <span class="hamb-bottom"></span>
            </button>
       
        <!-- /#page-content-wrapper -->

    </div>
    <!-- /#wrapper -->
</body>
</html>