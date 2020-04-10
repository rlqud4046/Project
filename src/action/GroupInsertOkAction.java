package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.AreaDAO;
import model.GroupDAO;
import model.GroupDTO;
import model.InterestDAO;

public class GroupInsertOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ActionForward forward = new ActionForward();
		
		//group_insert.jsp폼에서 보내준값 받기
		String group_name= request.getParameter("group_name");
		String group_intro= request.getParameter("group_intro");
		
			// 선택한 관심사의 대분류,소분류 받기
		String s_lc= request.getParameter("select_l_category");
		String s_sc= request.getParameter("select_s_category");
		
			// 선택한 지역의 대분류,소분류 받기
		String s_city= request.getParameter("select_city");
		String s_town= request.getParameter("select_town");
		
		//DB에 접근해서 선택한 지역,관심사의 번호를 받아와야함!
		InterestDAO dao1 = InterestDAO.getInstance();
		int interest_no = dao1.searchI_no(s_lc,s_sc);
		AreaDAO dao2 = AreaDAO.getInstance();
		int area_no = dao2.searchA_no(s_city,s_town);
		
		System.out.println(interest_no);
		System.out.println(area_no);
		
		//그룹테이블에서 가장큰 group_no에 +1 한 값 받아오기
		GroupDAO dao3 = GroupDAO.getInstance();
		int group_no=dao3.searchG_no();
		
		//DTO에 넣자
		GroupDTO dto = new GroupDTO();
		dto.setGroup_no(group_no);
		dto.setGroup_name(group_name);
		dto.setGroup_intro(group_intro);
		dto.setGroup_area(area_no);
		dto.setGroup_interest(interest_no);
		
		//DB에 ㄱㄱ
		int res = dao3.insertOK(dto);
		
		PrintWriter out = response.getWriter();
		if(res == 1) {
			forward.setRedirect(false); 
			forward.setPath("main.do?group_no="+group_no); //모임페이지가없어서 일단 메인으로 보내놓을게요
			
		}else {
			out.println("<script>");
			out.println("alert('모임개설 실패~')");
			out.println("</script>");
			
			forward=null;
		}
	
		return forward;
		
	}

}
