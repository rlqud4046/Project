package action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.GroupDAO;

public class GroupPremiumAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		int group_no = Integer.parseInt(request.getParameter("group_no"));
		
		GroupDAO dao = GroupDAO.getInstance();
		int count = dao.getpremium_no(group_no);
		
		request.setAttribute("no", group_no);
		
		if(count == 0) {
			
			ActionForward forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("chang_premium.do");

			return forward;
			
		}else if(count == 1) {
			
			ActionForward forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("release_premium.do");

			return forward;
		}
		
		return null;
	}

}
