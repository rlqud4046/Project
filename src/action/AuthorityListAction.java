package action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.AuthorityDAO;
import model.AuthorityDTO;

public class AuthorityListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		int group_no = Integer.parseInt(request.getParameter("group_no"));
		
		AuthorityDAO dao = AuthorityDAO.getInstance();
		AuthorityDTO dto = dao.getAuthorityList(group_no);
		
		request.setAttribute("DTO", dto);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("GroupBoardAuthorityManagement.do");

		return forward;
	}

}
