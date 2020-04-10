package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.AuthorityDAO;
import model.AuthorityDTO;

public class AuthorityUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		int group_no = Integer.parseInt(request.getParameter("group_no"));
		int r_notice = Integer.parseInt(request.getParameter("r_notice"));
		int w_notice = Integer.parseInt(request.getParameter("w_notice"));
		int r_hi = Integer.parseInt(request.getParameter("r_hi"));
		int w_hi = Integer.parseInt(request.getParameter("w_hi"));
		int r_meet = Integer.parseInt(request.getParameter("r_meet"));
		int w_meet = Integer.parseInt(request.getParameter("w_meet"));
		int r_free = Integer.parseInt(request.getParameter("r_free"));
		int w_free = Integer.parseInt(request.getParameter("w_free"));
		int r_photo = Integer.parseInt(request.getParameter("r_photo"));
		int w_photo = Integer.parseInt(request.getParameter("w_photo"));
		
		AuthorityDAO dao = AuthorityDAO.getInstance();
		int res = dao.authorityUpdate(group_no,r_notice,w_notice,r_hi,w_hi,r_meet,w_meet,r_free,w_free,r_photo,w_photo);
		AuthorityDTO dto = dao.getAuthorityList(group_no);
		
		request.setAttribute("DTO", dto);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("GroupBoardAuthorityManagement.do");

		return forward;
	}

}
