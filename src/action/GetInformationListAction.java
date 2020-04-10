package action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.GroupDAO;
import model.GroupDTO;

public class GetInformationListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
	
		int group_no = Integer.parseInt(request.getParameter("group_no"));
		
		GroupDAO dao = GroupDAO.getInstance();
		GroupDTO dto = dao.getGroupInfor(group_no);
		
		request.setAttribute("dto", dto);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("group_information.do");

		return forward;
	}

}
