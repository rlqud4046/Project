package action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import model.GroupDAO;
import model.JoinDTO;

public class MyGroupListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		int mem_no = Integer.parseInt(request.getParameter("mem_no"));
		
		GroupDAO dao = GroupDAO.getInstance();
		List<JoinDTO> list = dao.getMyGroupList(mem_no);
		
		request.setAttribute("List", list);
		
		// view 페이지로 포워딩
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("mygroup_ok_list.do");
				
		return forward;
	}

}
