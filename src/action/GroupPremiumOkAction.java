package action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.GroupDAO;

public class GroupPremiumOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

		
		int group_no = Integer.parseInt(request.getParameter("group_no"));
		
		GroupDAO dao = GroupDAO.getInstance();
		int res = dao.changGroupPremium(group_no);
		
		
		if(res > 0) {
			
			System.out.println("프리미엄 성공");
			ActionForward forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("GroupManagement.do");

			return forward;
		}else if(res == 0){
			
			System.out.println("프리미엄 실패");
			ActionForward forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("GroupManagement.do");

			return forward;
		}
		return null;
	}

}
