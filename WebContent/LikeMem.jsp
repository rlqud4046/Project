<%@page import="model.LikeDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="model.LikeDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
   // mgn_no에 해당하는 mem_no를 모두 받아오기
   List<String> LikeMem = new ArrayList<String>();

   
   int mgn_no = Integer.parseInt(request.getParameter("mgn_no"));
   System.out.println(mgn_no);

   LikeDAO dao =LikeDAO.getInstance();
   // 전체 대분류
   List<LikeDTO> likemem = dao.LikeMemList(mgn_no);

   for (int i = 0; i < likemem.size(); i++) {
         
         LikeMem.add(likemem.get(i).getMem_no()+"");
   }
   System.out.println(LikeMem);
   
   out.println(LikeMem);


%>