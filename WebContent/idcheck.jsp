<%@page import="model.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String id = request.getParameter("userId");
	MemberDAO dao = MemberDAO.getInstance();
	int res = dao.checkId(id);
	out.println(res);
%>