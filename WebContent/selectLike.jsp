<%@page import="model.InterestDTO"%>
<%@page import="model.InterestDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% 
	List<String> ListSC = new ArrayList<String>();

	
	// 내가 선택한 대분류
	String selectBox1 = request.getParameter("l_c");
	System.out.println(selectBox1);

	InterestDAO dao = InterestDAO.getInstance();
	// 전체 대분류
	List<InterestDTO> s_category = dao.selectSC();

	for (int i = 0; i < s_category.size(); i++) {
		if (selectBox1.equals(s_category.get(i).getL_category())) {
			
			ListSC.add(s_category.get(i).getS_category());
		}
	}
	System.out.println(ListSC);
	
	out.println(ListSC);

%>
