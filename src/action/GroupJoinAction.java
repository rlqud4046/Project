package action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.MembershipDAO;

public class GroupJoinAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		int mem_no = Integer.parseInt(request.getParameter("mem_no"));
		int group_no = Integer.parseInt(request.getParameter("group_no"));
		
		MembershipDAO dao = MembershipDAO.getInstance();
		dao.group_join(mem_no, group_no);
		
		
		String path = "group_main.jsp";
		request.setAttribute("include", path);
		// view page로 포워딩
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("samplePage.jsp"); // ㄴ므ㅔ
		return forward; 
	}

}
