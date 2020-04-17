package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.MembershipDAO;

public class GroupJoinAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		int mem_no = Integer.parseInt(request.getParameter("mem_no"));
		int group_no = Integer.parseInt(request.getParameter("group_no"));
		
		MembershipDAO dao = MembershipDAO.getInstance();
		
		
		boolean result = dao.group_join(mem_no, group_no);
		if (result) {
			response.setContentType("text/html;charset=euc-kr");
			PrintWriter out = response.getWriter();
			out.println("1");
			out.close();
		}

		return null;
	}

}
