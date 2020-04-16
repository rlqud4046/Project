package action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.MembershipDAO;

public class MemberMgnUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String[] rating = request.getParameterValues("rating");
		String[] nickname_id = request.getParameterValues("nickname_id");
		String[] mem_no = request.getParameterValues("mem_no");
		
		
		int group_no = Integer.parseInt(request.getParameter("group_no"));
		
		System.out.println(group_no);
		
		// 데이터 잘 넘어오는지 TEST
		
		for(String k : rating) {
			System.out.println("rating => " +k);
		}
		
		for(String k : nickname_id) {
			System.out.println("nickname_id => " +k);
		}
		
		
		MembershipDAO dao = MembershipDAO.getInstance();
		int res = dao.updateRationg(rating, mem_no, group_no);
		
		request.setAttribute("group_no", 1);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("member_management.do");
		
		
	
		return forward;
	}

}
