package action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.MembershipDAO;

public class WeimAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

		int mem_no = Integer.parseInt(request.getParameter("mem_no"));
		int group_no = Integer.parseInt(request.getParameter("group_no"));
		
		int moim = 8;
		
		System.out.println("mem_no => " +mem_no+ "group_no => " + group_no);
		
		MembershipDAO dao = MembershipDAO.getInstance();
		
		// 모임장 등급으로 변경
		int res = dao.weim(mem_no, group_no);
		System.out.println("res => " + res);
		
		// 모임장의 등급 변경 ( 세션으로 모임장 mem_no 받아와서 처리해야 함 )
		int res2 = dao.unyoung(group_no, moim);
		System.out.println("res2 => " +res2);
		
		request.setAttribute("group_no", group_no);
		request.setAttribute("mem_no", moim);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("member_management.do");
		
		return forward;
	}

}
