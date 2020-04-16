<%@page import="action.ActionForward"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	session.removeAttribute("group_No");
	response.sendRedirect("samplePage.jsp");
%>