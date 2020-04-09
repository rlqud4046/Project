<%@page import="model.AreaDTO"%>
<%@page import="model.AreaDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	List<String> ListTown = new ArrayList<String>();

	
	// 내가 선택한 대분류
	String selectBox3 = request.getParameter("city");
	System.out.println(selectBox3);

	AreaDAO dao = AreaDAO.getInstance();
	// 전체 대분류
	List<AreaDTO> town = dao.selectTown();

	for (int i = 0; i < town.size(); i++) {
		if (selectBox3.equals(town.get(i).getCity())) {
			
			ListTown.add(town.get(i).getTown());
		}
	}
	System.out.println(ListTown);
	
	out.println(ListTown);

%>
    