package action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.JoinDTO;
import model.LikeDAO;

public class LikeListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		int mem_no = Integer.parseInt(request.getParameter("mem_no"));
		
		LikeDAO dao = LikeDAO.getInstance();

		List<JoinDTO> list = dao.getLikeBoardList(mem_no);
		
		request.setAttribute("List", list);
		
		// view 페이지로 포워딩
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("likeBoardList.do");
						
		return forward;
	}

}
