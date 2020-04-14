package action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.GroupDAO;

public class GroupDismantlePwdOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
	
		int mem_no = Integer.parseInt(request.getParameter("mem_no"));
		int group_no = Integer.parseInt(request.getParameter("group_no"));
		String pwd = request.getParameter("pwd");
		
		GroupDAO dao = GroupDAO.getInstance();
		int res = dao.pwdcheck(pwd,mem_no);
		
		if(res > 0) {
			
			request.setAttribute("group_no", group_no);
			
			ActionForward forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("group_dismantle_guide.do");        // 안내 페이지로 이동

			return forward;
			
		}else if(res == -1) {
			
			ActionForward forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("group_dismantle.do"); // 비밀번호 다시 입력할 수 있게 이동

			return forward;
			
		}
		
		return null;
	}

}
