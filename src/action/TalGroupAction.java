package action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.MembershipDAO;

public class TalGroupAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		int mem_no = Integer.parseInt(request.getParameter("mem_no"));
		int group_no = Integer.parseInt(request.getParameter("group_no"));
		
		MembershipDAO dao = MembershipDAO.getInstance();
		int res = dao.groupTal(mem_no, group_no);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("mypage.do");
				
		return forward;
	}

}
