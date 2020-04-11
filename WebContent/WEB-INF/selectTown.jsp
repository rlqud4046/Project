<%@page import="java.util.List"%>
<%@page import="com.model.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String city = request.getParameter("city");
	MemberDAO dao = MemberDAO.getInstance();
	List<String> towns = dao.getTown(city);
	String str = "";
	for(int i=0; i<towns.size(); i++){
		if(i==towns.size()-1){
			str+=towns.get(i);
			break;
		}
		str+=towns.get(i)+",";
	}	
	out.println(str.trim());
%>