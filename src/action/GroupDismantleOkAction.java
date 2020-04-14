package action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.GroupDAO;

public class GroupDismantleOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		int group_no = Integer.parseInt(request.getParameter("group_no"));
	
		
		GroupDAO dao = GroupDAO.getInstance();
		int res = dao.deleteGroup(group_no);
	
		if(res > 0) {
			
			System.out.println("해체 성공");
			
			ActionForward forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("GroupManagement.do");        // 고쳐야함 일단 마이페이지로 넘겨둠.

			return forward;
			
		}else {
		
			System.out.println("해체 실패");
			
			ActionForward forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("group_dismantle.do");        // 고쳐야함 일단 마이페이지로 넘겨둠.

			return forward;
			
		}
		
	}

}
