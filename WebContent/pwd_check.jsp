<%@page import="model.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%	
	// 회원정보 변경시 비밀번호를 검증하기 위한 페이지 
	// 세션에서 아이디를 가져온다고 가정.
	// String id = (String)session.getAttribute("id");
	String id = "yks1111";
	String pwd = request.getParameter("pwd").trim();
	MemberDAO dao = MemberDAO.getInstance();
	int res = dao.pwdCheck(id, pwd);
	
	out.println(res);
	

%>