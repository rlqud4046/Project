package action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.tribes.membership.Membership;

import model.MembershipDAO;

public class BlackDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		///* alert("mem_num: " +mem_num+ "group_no:"+group_no+ "rating: "+rating); */
		int mem_num = Integer.parseInt(request.getParameter("mem_num"));
		int group_no = Integer.parseInt(request.getParameter("group_no"));
		String mem_id = request.getParameter("mem_id");
		
		System.out.println("mem_num : " + mem_num + "group_no : " + group_no +"mem_id : " + mem_id);
	
		MembershipDAO dao = MembershipDAO.getInstance();
		
		dao.deleteBlack(mem_num, group_no, mem_id);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("member_management.do");
		
		return forward;
	}

}
