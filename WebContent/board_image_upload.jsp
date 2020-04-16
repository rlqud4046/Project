<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="java.util.Enumeration"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%
	
	String uploadPath = "C:/ncs/workspace(jsp)/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/Project/upload/";

	int size = 10 * 1024 * 1024; // 업로드 사이즈 제한 10M 이하

	String fileName = ""; // 파일명

	try {
		// 파일업로드 및 업로드 후 파일명 가져옴
		MultipartRequest multi = new MultipartRequest(request, uploadPath, size, "utf-8",
				new DefaultFileRenamePolicy());
		Enumeration files = multi.getFileNames();
		String file = (String) files.nextElement();
		fileName = multi.getFilesystemName(file);

	} catch (Exception e) {
		e.printStackTrace();
	}

	// 업로드된 경로와 파일명을 통해 이미지의 경로를 생성
	//String uploadPath2 += "/upload/"+fileName;
	//uploadPath += "/" + fileName;
	String localPath = uploadPath+fileName;
	String uploadPath2 = "http://localhost:8282/Project/upload/" + fileName;
	//http://localhost:8282/00_somoim/upload/2.jpg

	// 생성된 경로를 JSON 형식으로 보내주기 위한 설정
	JSONObject jobj = new JSONObject();
	jobj.put("url", uploadPath2);
	jobj.put("localPath", localPath);

	response.setContentType("application/json"); // 데이터 타입을 json으로 설정하기 위한 세팅
	out.print(jobj.toJSONString());
%>