<%@page import="com.model.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String nickname = request.getParameter("nickname");
	MemberDAO dao = MemberDAO.getInstance();
	int res = dao.checkNickname(nickname);
	out.println(res);	
%>