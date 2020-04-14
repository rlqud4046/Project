package action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.PostDAO;
import model.PostDTO;

public class PostContAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// post_no에 해당하는 쪽지 상세보기

		ActionForward forward = new ActionForward();
		int post_no = Integer.parseInt(request.getParameter("post_no"));
		int page_no = Integer.parseInt(request.getParameter("page_no"));
		PostDAO dao = PostDAO.getInstance();
		
		request.setAttribute("page_no", page_no);
		
		if (page_no == 1) {
			
			PostDTO list = dao.postRCont(post_no);

			request.setAttribute("listCont", list);

			forward.setRedirect(false);
			forward.setPath("post_cont.jsp");
			
			
		} else if (page_no == 2) {
			PostDTO list = dao.postSCont(post_no);
			request.setAttribute("listCont", list);

			forward.setRedirect(false);
			forward.setPath("post_cont.jsp");
			

		}
		return forward;
	}

}
