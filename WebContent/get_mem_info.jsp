<%@page import="model.MemberDTO"%>
<%@page import="model.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	// 회원정보 수정 페이지에서 ajax로 통신을 하기 위한 페이지
	// 세션에서 아이디를 가져온다고 가정.
	// String id = (String)session.getAttribute("id");
	String id = "ffffff";
	
	// db 연동을 위한 객체 생성
	MemberDAO dao = MemberDAO.getInstance();
	
	// id와 일치하는 정보를 db에서 가져온다
	MemberDTO dto = dao.selectMember(id);
	
	// 가져온 데이터를 JSON 형식 문자열로 작성 후 변수에 저장.
	String result = "{"+
			  "\"id\":\""+dto.getId()+"\","+
			  "\"mem_name\":\""+dto.getMem_name()+"\","+
			  "\"nickname\":\""+dto.getNickname()+"\","+
			  "\"profile_img\":\""+dto.getProfile_img()+"\","+
			  "\"birth\":\""+dto.getBirth()+"\","+
			  "\"e_mail\":\""+dto.getE_mail()+"\","+
			  "\"phone\":\""+dto.getPhone()+"\","+
			  "\"check_q\":\""+dto.getCheck_q()+"\","+
			  "\"check_a\":\""+dto.getCheck_a()+"\","+
			  "\"city\":\""+dto.getCity()+"\","+
			  "\"area1\":\""+dto.getArea1()+"\","+
			  "\"area2\":\""+dto.getArea2()+"\","+
			  "\"area3\":\""+dto.getArea3()+"\","+
			  "\"interests\":\""+dto.getInterests()+"\"}";
			  
	// 저장된 변수를 전송		  
	out.print(result);
%>